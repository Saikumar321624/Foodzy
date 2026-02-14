package com.example.Foodzy.ServiceLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Foodzy.Dtos.CustomerRegistrationDto;
import com.example.Foodzy.Repositary.CustomerRepo;
import com.example.Foodzy.entity.Customer;

@Service
public class CustomerService {
	@Autowired
	CustomerRepo cr;

	public void registerCustomer(CustomerRegistrationDto cdto) {
		Customer c=new Customer();
		c.setName(cdto.getName());
		c.setMobileNo(cdto.getMobileNo());
		c.setMail(cdto.getMail());
		c.setGender(cdto.getGender());
		cr.save(c);
		
		
	}
	

}
