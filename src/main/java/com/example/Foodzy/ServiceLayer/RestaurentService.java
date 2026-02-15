package com.example.Foodzy.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.Foodzy.Dtos.RestaurentRegistrationDto;
import com.example.Foodzy.Repositary.RestaurantRepo;
import com.example.Foodzy.Response.ResponseStructure;
import com.example.Foodzy.entity.Restaurant;
@Service
public class RestaurentService {
	@Autowired RestaurantRepo repo;
	public ResponseStructure<RestaurentRegistrationDto> registerRestaurent(RestaurentRegistrationDto rdto) {
		Restaurant rs=new Restaurant();
		rs.setName(rdto.getName());
		rs.setMobileno(rdto.getMobileNo());
		rs.setMail(rdto.getMail()); 
		rs.setDescription(rdto.getDescription());
		rs.setPackagingfee(rdto.getPackagingFee());
		rs.setType(rdto.getType());
		repo.save(rs);
		ResponseStructure<RestaurentRegistrationDto> rsp=new ResponseStructure<RestaurentRegistrationDto>();
		rsp.setMessage("Restaurant Has Registered Successfully");
		rsp.setstatuscode(HttpStatus.CREATED.value());
		rsp.setData(rdto);
		return rsp;
		
		
	}

}
