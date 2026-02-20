package com.example.Foodzy.ServiceLayer;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.Foodzy.Dtos.RestaurentRegistrationDto;
import com.example.Foodzy.Repositary.AddressRepo;
import com.example.Foodzy.Repositary.ItemRepo;
import com.example.Foodzy.Repositary.RestaurantRepo;
import com.example.Foodzy.Response.ResponseStructure;
import com.example.Foodzy.entity.Address;
import com.example.Foodzy.entity.Item;

import com.example.Foodzy.Dtos.RestaurentRegistrationDto;
import com.example.Foodzy.Repositary.AddressRepo;
import com.example.Foodzy.Repositary.RestaurantRepo;
import com.example.Foodzy.Response.ResponseStructure;
import com.example.Foodzy.entity.Address;
import com.example.Foodzy.entity.Item;
import com.example.Foodzy.entity.Restaurant;
@Service
public class RestaurentService {
	
	
	@Autowired
	private AddressRepo ar;
	
	@Autowired
	private ItemRepo ir;
	
	@Autowired
    private	RestaurantRepo repo;
	
	
	@Autowired
	private AddressRepo addressRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public ResponseStructure<Restaurant> registerRestaurent(RestaurentRegistrationDto rdto) {
		Restaurant rs=new Restaurant();
		rs.setName(rdto.getName());
		rs.setMobileno(rdto.getMobileNo());
		rs.setMail(rdto.getMail()); 
		rs.setDescription(rdto.getDescription());
		rs.setPackagingfee(rdto.getPackagingFee());
		rs.setType(rdto.getType());
		Address address=new Address();
		Map response=restTemplate.getForObject("https://us1.locationiq.com/v1/reverse?key=pk.0385211038eb29984c09bb077ddeb2d3&lat="+rdto.getLocationCoordinates().getLatitude()+"&lon="+rdto.getLocationCoordinates().getLongitude()+"&format=json&", Map.class);
		Map add=(Map) response.get("address");
		address.setCity((String)add.get("city"));
		address.setPincode(Integer.parseInt(add.get("postcode").toString()));
		address.setCountry((String)add.get("country"));
		address.setDistrict((String) add.get("city_district"));
		address.setState((String)add.get("state"));
		address.setLatitude(rdto.getLocationCoordinates().getLatitude());
		address.setLongitude(rdto.getLocationCoordinates().getLongitude());
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
		ResponseStructure<Restaurant> resp=new ResponseStructure<Restaurant>();
		if(rs!=null)
		{
			resp.setData(rs);
			resp.setMessage("Restaurant Found Successfully");
			resp.setstatuscode(HttpStatus.FOUND.value());
		}
		else
		{
			resp.setMessage("restarant  not Found");
			resp.setstatuscode(HttpStatus.NOT_FOUND.value());
		}
		return resp;
	}
	public ResponseStructure<Restaurant> deleterestaurant(long mobileNo) {
		Restaurant rs=repo.findByMobileno(mobileNo);
		ResponseStructure<Restaurant> resp=new ResponseStructure<Restaurant>();
		if(rs!=null)
		{
			repo.delete(rs);
			resp.setstatuscode(HttpStatus.OK.value());
			resp.setMessage("Restaurant has been Deleted Successfully");
		}
		else
		{
			resp.setstatuscode(HttpStatus.NOT_FOUND.value());
			resp.setMessage("Restaurant with"+mobileNo +"not Found");
		}
		return resp;
	}

	public ResponseStructure<Restaurant> addItem(long mobileNo, Item item) {
		Restaurant rs=repo.findByMobileno(mobileNo);
		ResponseStructure<Restaurant> resp=new ResponseStructure<Restaurant>();
		ir.save(item);
		if(rs!=null) {
			List l=rs.getMenu();
			l.add(item);
			rs.setMenu(l);
			repo.save(rs);
			resp.setData(rs);
			resp.setstatuscode(HttpStatus.OK.value());
			resp.setMessage("Item added Successfully");			
		}
		else
		{
			resp.setMessage("Restaurant not found");
		}
		return resp;

	}
	public ResponseStructure<Restaurant> updateStatus(long mobileNo) {
		Restaurant rs=repo.findByMobileno(mobileNo);
		ResponseStructure<Restaurant> resp=new ResponseStructure<Restaurant>();
		if(rs!=null)
		{
			rs.setStatus("Opened");
			repo.save(rs);
			resp.setMessage("status updated Successfully");
		}
		else
		{
			resp.setMessage("restaurantNotFound");
		}	
		return resp;
	}
	
	

}
