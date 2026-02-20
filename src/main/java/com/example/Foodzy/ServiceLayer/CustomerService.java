package com.example.Foodzy.ServiceLayer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.support.Repositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Foodzy.Dtos.CartItemResponse;
import com.example.Foodzy.Dtos.CustomerRegistrationDto;
import com.example.Foodzy.Dtos.RestaurentInfo;
import com.example.Foodzy.Repositary.CartItemRepo;
import com.example.Foodzy.Repositary.CustomerRepo;
import com.example.Foodzy.Repositary.ItemRepo;
import com.example.Foodzy.Repositary.RestaurantRepo;
import com.example.Foodzy.Response.ResponseStructure;
import com.example.Foodzy.entity.CartItem;
import com.example.Foodzy.entity.Customer;
import com.example.Foodzy.entity.Item;
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

		String cityname = customer.getAddress().getCity();
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

}
