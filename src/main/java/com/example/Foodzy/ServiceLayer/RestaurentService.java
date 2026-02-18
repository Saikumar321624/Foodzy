package com.example.Foodzy.ServiceLayer;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
		
		Address address =new Address();
	    Map response=restTemplate.getForObject("https://us1.locationiq.com/v1/reverse?key=pk.87ab9aed219019e92d140b2d50bad383&lat="+rdto.getCoodinates().getLatitude()+"&lon="+rdto.getCoodinates().getLongitude()+"&format=json\r\n", Map.class);
	     Map add=(Map)	response.get("address");
	     
	     
	   address.setCity((String)add.get("city"));
	   address.setCountry((String)add.get("country"));
//	   address.setPincode((int)add.get("postcode"));
	   address.setState((String)add.get("state"));
	   
	   rs.setAddress(address);
	   addressRepo.save(address);
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
	
	

}
