package com.example.Foodzy.ServiceLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import com.example.Foodzy.Dtos.CustomerRegistrationDto;
import com.example.Foodzy.Repositary.CustomerRepo;
import com.example.Foodzy.Response.ResponseStructure;
import com.example.Foodzy.entity.Customer;

@Service
public class CustomerService {
	@Autowired
	CustomerRepo cr;

	public ResponseStructure<CustomerRegistrationDto> registerCustomer(CustomerRegistrationDto cdto) {
		Customer c=new Customer();
		c.setName(cdto.getName());
		c.setMobileNo(cdto.getMobileNo());
		c.setMail(cdto.getMail());
		c.setGender(cdto.getGender());
		cr.save(c);
		ResponseStructure<CustomerRegistrationDto> resp=new ResponseStructure<CustomerRegistrationDto>();
		resp.setMessage("customer has registred successfully");
		resp.setstatuscode(HttpStatus.CREATED.value());
		resp.setData(cdto);
		return resp;
		
	}
	

}
