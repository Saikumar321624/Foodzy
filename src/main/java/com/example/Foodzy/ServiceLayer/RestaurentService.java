package com.example.Foodzy.ServiceLayer;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.Foodzy.Dtos.RestaurentRegistrationDto;
import com.example.Foodzy.Exceptions.ItemNotFoundException;
import com.example.Foodzy.Exceptions.OrderNotFoundException;
import com.example.Foodzy.Exceptions.RestaurantnotFoundException;
import com.example.Foodzy.Repositary.AddressRepo;
import com.example.Foodzy.Repositary.ItemRepo;
import com.example.Foodzy.Repositary.OrdersRepo;
import com.example.Foodzy.Repositary.RestaurantRepo;
import com.example.Foodzy.Response.ResponseStructure;
import com.example.Foodzy.entity.Address;
import com.example.Foodzy.entity.Item;

import com.example.Foodzy.entity.Orders;

import com.example.Foodzy.entity.Restaurant;
@Service
public class RestaurentService {
	@Autowired 
	RestaurantRepo repo;
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	AddressRepo ar;
	@Autowired
	ItemRepo ir;	
	@Autowired
	OrdersRepo orderRepo;
	@Autowired
	RedisService redisService;
	@Autowired
	RedisTemplate<String, String> redistemplate;
	public ResponseStructure<Restaurant> registerRestaurent(RestaurentRegistrationDto rdto) {
		Restaurant rs=new Restaurant();
		rs.setName(rdto.getName());
		rs.setMobileno(rdto.getMobileNo());
		rs.setMail(rdto.getMail()); 
		rs.setDescription(rdto.getDescription());
		rs.setPackagingfee(rdto.getPackagingFee());
		rs.setType(rdto.getType());
		Address address=new Address();
		Map response=restTemplate.getForObject("https://us1.locationiq.com/v1/reverse?key=pk.0385211038eb29984c09bb077ddeb2d3&lat="+rdto.getLocationCordinates().getLatitude()+"&lon="+rdto.getLocationCordinates().getLongitude()+"&format=json", Map.class);
		Map add=(Map) response.get("address");
		address.setCity((String)add.get("city"));
		address.setPincode(Integer.parseInt(add.get("postcode").toString()));
		address.setCountry((String)add.get("country"));
		address.setDistrict((String) add.get("city_district"));
		address.setState((String)add.get("state"));
		address.setLatitude(rdto.getLocationCordinates().getLatitude());
		address.setLongitude(rdto.getLocationCordinates().getLongitude());
		rs.setAddress(address);
		ar.save(address);	
		repo.save(rs);
		ResponseStructure<Restaurant> rsp=new ResponseStructure<Restaurant>();
    	rsp.setMessage("Restaurant Has Registered Successfully");
    	rsp.setstatuscode(HttpStatus.CREATED.value());
		rsp.setData(rs);
		return rsp;
		
	}
	public ResponseStructure<Restaurant> findRestrant(long phoneNo) {
		Restaurant rs=repo.findByMobileno(phoneNo);
		if(rs==null) throw new RestaurantnotFoundException();
		ResponseStructure<Restaurant> resp=new ResponseStructure<Restaurant>();
		
			resp.setData(rs);
			resp.setMessage("Restaurant Found Successfully");
			resp.setstatuscode(HttpStatus.FOUND.value());
		return resp;
	}
	public ResponseStructure<Restaurant> deleterestaurant(long mobileNo) {
		Restaurant rs=repo.findByMobileno(mobileNo);
		if(rs==null) throw new RestaurantnotFoundException();

		ResponseStructure<Restaurant> resp=new ResponseStructure<Restaurant>();
		
			repo.delete(rs);
			resp.setstatuscode(HttpStatus.OK.value());
			resp.setMessage("Restaurant has been Deleted Successfully");
		return resp;
	}

	public ResponseStructure<Restaurant> addItem(long mobileNo, Item item) {
		Restaurant rs=repo.findByMobileno(mobileNo);
		if(rs==null) throw new RestaurantnotFoundException();
		ResponseStructure<Restaurant> resp=new ResponseStructure<Restaurant>();
		ir.save(item);
					List l=rs.getMenu();
			l.add(item);
			rs.setMenu(l);
			repo.save(rs);
			resp.setData(rs);
			resp.setstatuscode(HttpStatus.OK.value());
			resp.setMessage("Item added Successfully");			
		
		return resp;

	}
	public ResponseStructure<Restaurant> updateStatus(long mobileNo) {
		Restaurant rs=repo.findByMobileno(mobileNo);
		ResponseStructure<Restaurant> resp=new ResponseStructure<Restaurant>();
		if(rs==null) throw new RestaurantnotFoundException();		
			rs.setStatus("Opened");
			repo.save(rs);
			resp.setMessage("status updated Successfully");
		return resp;
	}
	public ResponseStructure<Item> updateItemavailability(long restauranMobileNo, long itemId) {
		ResponseStructure<Item> resp=new ResponseStructure<Item>();
		Restaurant rs=repo.findByMobileno(restauranMobileNo);
		if(rs==null) throw new RestaurantnotFoundException();		
			List<Item> itemList=rs.getMenu();
			for(Item i:itemList)
			{
				if(i.getItemId()==itemId)
				{
					if(i.getAvailability().equals("Available"))
					{
						resp.setMessage("item is already available");
						resp.setData(i);
						resp.setstatuscode(HttpStatus.BAD_REQUEST.value());
						
					}
					else
					{
						i.setAvailability("Available");
						ir.save(i);
						resp.setData(i);
						resp.setMessage("Status updated successfullly");
						resp.setstatuscode(HttpStatus.OK.value());
					}
				}
			}
		
		return resp;
		
	}
	public ResponseStructure<List<Item>> getMenu(long mobileNo) {
		Restaurant rs=repo.findByMobileno(mobileNo);
		if(rs==null) throw new RestaurantnotFoundException();
		ResponseStructure<List<Item>> menu=new ResponseStructure<List<Item>>();
		menu.setData(rs.getMenu());
		menu.setMessage("Restaurant Items");
		menu.setstatuscode(HttpStatus.OK.value());
		return menu;
	}
	public ResponseStructure<List<String>> acceptOrder(Long orderId) {
		Orders order=orderRepo.findById(orderId).orElseThrow(()-> new OrderNotFoundException());
		List<String> partnerIds=redisService.getNearByPartners(order.getRestarunt().getAddress().getLatitude(), order.getRestarunt().getAddress().getLongitude(), 5);
		for(String partnerId :partnerIds)
		{ 
			redistemplate.opsForList().rightPush("partner:"+partnerId+":orders",orderId.toString());
		}
		ResponseStructure<List<String>> resp=new  ResponseStructure<List<String>>();
		resp.setMessage("Order Accepted and assigning the deliver parners");
		resp.setstatuscode(HttpStatus.ACCEPTED.value());
		resp.setData(partnerIds);
		return resp;
	}
	public ResponseStructure<Restaurant> removeItemFromMenu(Long mobileNumber, Long itemId) {
		Restaurant restaurant=repo.findByMobileno(mobileNumber);
		if(restaurant==null) throw new RestaurantnotFoundException();
		for(Item i:restaurant.getMenu())
		{
			if(i.getItemId()==itemId)
			{
				restaurant.getMenu().remove(i);
				break;
			}
		}
		repo.save(restaurant);
		ResponseStructure<Restaurant> resp=new ResponseStructure<Restaurant>();
		resp.setData(restaurant);
		resp.setMessage("Item has been removed successfully");
		resp.setstatuscode(HttpStatus.OK.value());
		return resp;
		
	}
	public ResponseStructure<Item> updateItemDetails(Long mobilenumber, Long itemId) {
		Restaurant restaurant =repo.findByMobileno(mobilenumber);
		if(restaurant==null) throw new RestaurantnotFoundException();
		Item i=null;
		for(Item item:restaurant.getMenu())
		{
			item.setAvailability("Not Available");
			ir.save(item);
			i=item;
			break;
		}
		ResponseStructure<Item> resp=new ResponseStructure<Item>();
		resp.setData(i);
		resp.setMessage("details Upfdated Successfully");
		resp.setstatuscode(HttpStatus.OK.value());
		return resp;
	}
	public ResponseStructure<Restaurant> CancelOrder(Long restMobno, long orderid) {
	     Restaurant restaurant=	repo.findByMobileno(restMobno);
	    Orders or= orderRepo.findById(orderid).orElseThrow(()-> new OrderNotFoundException());
	    
	    or.setStatus("Cancelled");
	    or.setRestarunt(restaurant);
	    
	    double penality=0.0;
	    penality=or.getCost()*0.1;
	    restaurant.setVallet(restaurant.getVallet()+penality);
	    
	    repo.save(restaurant);
	    orderRepo.save(or);
	    
	    ResponseStructure<Restaurant> rs=new ResponseStructure<Restaurant>();
	    rs.setstatuscode(HttpStatus.ACCEPTED.value());
	    rs.setMessage("Order cancelled successfully");
	    rs.setData(restaurant);
	    
	    return rs;
	    
	}
	
	
	
}