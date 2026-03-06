package com.example.Foodzy.ServiceLayer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.Foodzy.Dtos.CartItemResponse;
import com.example.Foodzy.Dtos.CustomerRegistrationDto;
import com.example.Foodzy.Dtos.OrderNeedconsetDto;
import com.example.Foodzy.Dtos.RestaurentInfo;
import com.example.Foodzy.Exceptions.CustomerNotFoundException;
import com.example.Foodzy.Exceptions.ItemNotFoundException;
import com.example.Foodzy.Exceptions.RestaurantException;
import com.example.Foodzy.Exceptions.RestaurantnotFoundException;
import com.example.Foodzy.Exceptions.OrderNotFoundException;
import com.example.Foodzy.Repositary.AddressRepo;
import com.example.Foodzy.Repositary.CartItemRepo;
import com.example.Foodzy.Repositary.CustomerRepo;
import com.example.Foodzy.Repositary.ItemRepo;
import com.example.Foodzy.Repositary.OrdersRepo;
import com.example.Foodzy.Repositary.RestaurantRepo;
import com.example.Foodzy.Response.ResponseStructure;
import com.example.Foodzy.entity.Address;
import com.example.Foodzy.entity.CartItem;
import com.example.Foodzy.entity.Customer;
import com.example.Foodzy.entity.Item;
import com.example.Foodzy.entity.Orders;
import com.example.Foodzy.entity.Restaurant;

@Service
public class CustomerService {
	@Autowired
	CustomerRepo cr;

	@Autowired
	RestaurantRepo restaurantRepo;

	@Autowired
	ItemRepo ir;

	@Autowired
	CartItemRepo cartItemRepo;
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	OrdersRepo orderRepo;
	@Autowired
	AddressRepo ar;
	public ResponseStructure<CustomerRegistrationDto> registerCustomer(CustomerRegistrationDto cdto) {
		Customer c = new Customer();
		c.setName(cdto.getName());
		c.setMobileNo(cdto.getMobileNo());
		c.setMail(cdto.getMail());
		c.setGender(cdto.getGender());
		cr.save(c);
		ResponseStructure<CustomerRegistrationDto> resp = new ResponseStructure<CustomerRegistrationDto>();
		resp.setMessage("customer has registred successfully");
		resp.setstatuscode(HttpStatus.CREATED.value());
		resp.setData(cdto);
		return resp;

	}
	public ResponseStructure<Customer> find(long mobileNumber) {
		Customer cs = cr.findByMobileNumber(mobileNumber);
		ResponseStructure<Customer> resp = new ResponseStructure<Customer>();
		if (cs != null) {
			resp.setMessage("customer found successfully");
			resp.setstatuscode(HttpStatus.FOUND.value());
			resp.setData(cs);
		} else {
			resp.setMessage("customer not found");
			resp.setstatuscode(HttpStatus.NOT_FOUND.value());
		}
		return resp;
	}

	public ResponseStructure<Customer> delete(long mobileNumber) {
		Customer cs = cr.findByMobileNumber(mobileNumber);
		ResponseStructure<Customer> resp = new ResponseStructure<Customer>();
		if (cs != null) {
			cr.delete(cs);
			resp.setMessage("deleted successfully");
			resp.setstatuscode(HttpStatus.OK.value());
		} else {
			resp.setMessage("customer not found");

		}

		return resp;
	}


	public ResponseEntity<ResponseStructure<CartItemResponse>> AddCart(long mobileNumber, Long itemid, int quantity) {

		Customer customer = cr.findByMobileNumber(mobileNumber);
		if (customer == null) {
			throw new RuntimeException("Customer not found");
		}

		Item item = ir.findById(itemid).orElseThrow(() -> new RuntimeException("Item not found"));

//		CartItem cartItem = new CartItem();
		List<CartItem> clist=cartItemRepo.findAll();
		CartItem cartItem=clist.stream().filter(c->c.getItem().getItemId()==itemid).findFirst().orElse(null);
		if(cartItem!=null) {
			cartItem.setQuantity(cartItem.getQuantity()+quantity);	
		}else {
			cartItem=new CartItem();
			cartItem.setCustomer(customer);
			cartItem.setItem(item);
			cartItem.setQuantity(quantity);
			cartItem.setRestaurant(item.getRestaurant());
			customer.getCart().add(cartItem);
		}
			
		cr.save(customer);

		CartItemResponse cartResponse = new CartItemResponse();
		cartResponse.setItemName(cartItem.getItem().getItemName());
		cartResponse.setQuantity(cartItem.getQuantity());

		ResponseStructure<CartItemResponse> rs = new ResponseStructure<>();
		rs.setMessage("Added to the cart successfully");
		rs.setData(cartResponse);
		rs.setstatuscode(HttpStatus.OK.value());

		return ResponseEntity.ok(rs);
	}

	public ResponseEntity<ResponseStructure<List<RestaurentInfo>>> SearchItemOrRestaurent(long cusmobile,
			String searchkey) {

		Customer customer = cr.findByMobileNumber(cusmobile);
		if (customer == null) {
			throw new RuntimeException("customer not found ");
		}

		String cityname = customer.getAddress().get(0).getCity();
		List<Restaurant> reslist = restaurantRepo.findByAddress_City(cityname).orElseThrow();

		List<RestaurentInfo> restaurentlist = reslist.stream()
				.filter(r -> r.getName().contains(searchkey)
						|| r.getMenu().stream().anyMatch(i -> i.getItemName().contains(searchkey)))
				.map(r -> convertToRestaurantInfo(r)).toList();

		if (restaurentlist == null) {
			throw new RuntimeException("no data found");
		}

		ResponseStructure<List<RestaurentInfo>> rs = new ResponseStructure<List<RestaurentInfo>>();
		rs.setMessage("search result ");
		rs.setstatuscode(HttpStatus.OK.value());
		rs.setData(restaurentlist);

		return ResponseEntity.ok(rs);
	}

	private RestaurentInfo convertToRestaurantInfo(Restaurant r) {
		RestaurentInfo rf = new RestaurentInfo();
		rf.setName(r.getName());
		rf.setMobileno(r.getMobileno());
		rf.setMenu(r.getMenu());
		rf.setMail(r.getMail());
		rf.setType(r.getType());
		rf.setDescription(r.getDescription());
		rf.setRating(r.getRating());
		return rf;
	}
	public ResponseStructure<Address> addAddress(Address address, long mobileNumber) {
		Customer cs=cr.findByMobileNumber(mobileNumber);
		if(cs==null) throw new CustomerNotFoundException();
		ar.save(address);
		cs.getAddress().add(address);
		cr.save(cs);
		ResponseStructure<Address> resp=new ResponseStructure<Address>();
		resp.setData(address);
		resp.setMessage("Address has added successfully");
		resp.setstatuscode(HttpStatus.OK.value());
		
		return resp;
	}
		
	
	
//	public ResponseStructure<OrderNeedconsetDto> placeOrder(long mobileNumber, String addressType, String deliveryInstructions, String specialRequest) {
//		Customer cs=cr.findByMobileNumber(mobileNumber);
//		if(cs==null) throw new CustomerNotFoundException();
//		Orders order=new Orders();
//		order.setStatus("Placed");
//		Restaurant restaurant=cs.getCart().get(0).getItem().getRestaurant();
//		if(restaurant==null) throw new RestaurantnotFoundException();
//		
//
//		if(!restaurant.getStatus().equals("Opened")) {
//			throw new RestaurantException();
//		}
//		order.setRestarunt(restaurant);
//		order.setDeliveryInstructions(deliveryInstructions);
//		Address address=null;
//		for(Address a:cs.getAddress())
//		{
//			if(a.getType().equals(addressType))
//			{
//				order.setAddress(a);
//				address=a;
//				break;
//			}
//		}
//	
//		double productCost=0;
//		for(CartItem cartItem:cs.getCart())
//		{
//			productCost=cartItem.getItem().getPrice()*cartItem.getQuantity()+productCost;
//		}
//		
//		double deliveryCharge=0;
//		Map response=restTemplate.getForObject("https://us1.locationiq.com/v1/directions/driving/"+restaurant.getAddress().getLatitude()+","+restaurant.getAddress().getLongitude()+";"+address.getLatitude()+","+address.getLongitude()+"?key=pk.e155d0e145eee3dbf5bf4a52ae8ec527&steps=true&alternatives=true&geometries=polyline&overview=full&", Map.class);
//		double distance=0;
//		List routes = (List) response.get("routes");
//
//		if (routes != null && !routes.isEmpty()) {
//		    Map firstRoute = (Map) routes.get(0);
//
//		    Double distanceInMeters = (Double) firstRoute.get("distance");
//
//		    Double distanceInKm = distanceInMeters / 1000;
//		    distance=distanceInKm;
//
//		    System.out.println("Distance in KM: " + distanceInKm);
//		}
//		double chargableDistance;
//		if(distance>2.0)
//		{
//			 chargableDistance=distance-2.0;
//			deliveryCharge=chargableDistance*10;
//		}
//		double cost=deliveryCharge+restaurant.getPackagingfee()+productCost;
//		order.setCost(cost);
//		order.setCustomer(cs);
//		order.setItems(cs.getCart());
//		order.setPickupAddress(restaurant.getAddress());
//		order.setEstimatedTime("10min");
//		order.setDate("date");
//		orderRepo.save(order);
//		OrderNeedconsetDto conset=new OrderNeedconsetDto();
//		conset.setCart(order.getItems());
//		conset.setDate(order.getDate());
//		conset.setDeliveryAddress(order.getAddress());
//		conset.setPrice(cost);
//		conset.setStatus("yet to confirm");		
//		ResponseStructure<OrderNeedconsetDto> resp=new ResponseStructure<OrderNeedconsetDto>();
//		resp.setData(conset);
//		resp.setMessage("Order set successfully");
//		resp.setstatuscode(HttpStatus.OK.value());
//		return resp;
//	}
	public ResponseStructure<OrderNeedconsetDto> placeOrder(long mobileNumber,
	        String addressType,
	        String deliveryInstructions,
	        String specialRequest) {

	    Customer customer = cr.findByMobileNumber(mobileNumber);
	    if (customer == null)
	        throw new CustomerNotFoundException();
	    if (customer.getCart() == null || customer.getCart().isEmpty())
	        throw new RuntimeException("Cart is empty");

	    CartItem firstCartItem = customer.getCart().get(0);

	    if (firstCartItem.getItem() == null)
	        throw new ItemNotFoundException();

	    Restaurant restaurant = firstCartItem.getItem().getRestaurant();

	    if (restaurant == null)
	        throw new RestaurantnotFoundException();

	    if (!"Opened".equalsIgnoreCase(restaurant.getStatus()))
	        throw new RestaurantException();

	    for (CartItem cartItem : customer.getCart()) {
	        if (cartItem.getItem().getRestaurant().getId()!=(restaurant.getId())) {
	            throw new RuntimeException("Cart contains items from multiple restaurants");
	        }
	    }
	    Address deliveryAddress = null;

	    for (Address address : customer.getAddress()) {
	        if (address.getType().equalsIgnoreCase(addressType)) {
	            deliveryAddress = address;
	            break;
	        }
	    }

	    if (deliveryAddress == null)
	        throw new RuntimeException("Address type not found");

	    double productCost = 0;

	    for (CartItem cartItem : customer.getCart()) {
	        productCost += cartItem.getItem().getPrice() * cartItem.getQuantity();
	    }

	    double distance = 0;

	    Map response = restTemplate.getForObject(
	            "https://us1.locationiq.com/v1/directions/driving/"
	                    + restaurant.getAddress().getLatitude() + ","
	                    + restaurant.getAddress().getLongitude()
	                    + ";"
	                    + deliveryAddress.getLatitude() + ","
	                    + deliveryAddress.getLongitude()
	                    + "?key=pk.e155d0e145eee3dbf5bf4a52ae8ec527&overview=false",
	            Map.class);

	    List routes = (List) response.get("routes");

	    if (routes != null && !routes.isEmpty()) {
	        Map firstRoute = (Map) routes.get(0);
	        Double distanceMeters = (Double) firstRoute.get("distance");
	        distance = distanceMeters / 1000;
	    }

	    double deliveryCharge = 0;

	    if (distance > 2) {
	        double chargeableDistance = distance - 2;
	        deliveryCharge = chargeableDistance * 10;
	    }

	    double totalCost = productCost + deliveryCharge + restaurant.getPackagingfee();

	    Orders order = new Orders();
	    order.setStatus("Placed");
	    order.setCustomer(customer);
	    order.setRestarunt(restaurant);
	    order.setAddress(deliveryAddress);
	    order.setPickupAddress(restaurant.getAddress());
	    List<CartItem> orderItems = new ArrayList<>(customer.getCart());
	    order.setItems(orderItems);
	    order.setDeliveryInstructions(deliveryInstructions);
	    order.setEstimatedTime("10min");
	    order.setCost(totalCost);
	    order.setDate(LocalDate.now().toString());

	    orderRepo.save(order);

	    OrderNeedconsetDto dto = new OrderNeedconsetDto();
	    dto.setCart(order.getItems());
	    dto.setDate(order.getDate());
	    dto.setDeliveryAddress(order.getAddress());
	    dto.setPrice(totalCost);
	    dto.setStatus("yet to confirm");

	    ResponseStructure<OrderNeedconsetDto> responseStructure = new ResponseStructure<>();
	    responseStructure.setData(dto);
	    responseStructure.setMessage("Order placed successfully");
	    responseStructure.setstatuscode(HttpStatus.OK.value());

	    return responseStructure;
	}
	public ResponseStructure<Orders> confirmOrder(long mobileNo, Long orderId) {
		Customer customer=cr.findByMobileNumber(mobileNo);
		if(customer==null)throw new CustomerNotFoundException();
		Orders order=orderRepo.findById(orderId).orElseThrow(()->new OrderNotFoundException());
		order.setStatus("Placed");
		int min=1000;
		int max=9999;
		int otp = ThreadLocalRandom.current().nextInt(min, max + 1);
		order.setOtp(otp);
		Restaurant restaurant=customer.getCart().get(0).getItem().getRestaurant();
		order.setRestarunt(restaurant);
		restaurant.getOrders().add(order);
		restaurantRepo.save(restaurant);
		orderRepo.save(order);
		ResponseStructure<Orders> resp=new ResponseStructure<Orders>();
		resp.setData(order);
		resp.setMessage("Order set successfully");
		resp.setstatuscode(HttpStatus.OK.value());
		return resp;
		
		
	}
	public ResponseStructure<Orders> denyOrder(long orderId, Long mobileNumber) {
		Customer customer=cr.findByMobileNumber(mobileNumber);
		if(customer==null)throw new CustomerNotFoundException();
		Orders order=orderRepo.findById(orderId).orElseThrow(()->new OrderNotFoundException());
		order.setStatus("Cancelled");
		ResponseStructure<Orders> resp=new ResponseStructure<Orders>();
		resp.setData(order);
		resp.setMessage("Order cancelled successfully");
		resp.setstatuscode(HttpStatus.OK.value());
		return resp;
		
	}

	
	
}
