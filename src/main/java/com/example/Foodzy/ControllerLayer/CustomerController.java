package com.example.Foodzy.ControllerLayer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Foodzy.Dtos.CustomerRegistrationDto;
import com.example.Foodzy.Response.ResponseStructure;
import com.example.Foodzy.ServiceLayer.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	CustomerService cs;
	@PostMapping("/register")
	public ResponseStructure<CustomerRegistrationDto> customerRegistration(@RequestBody CustomerRegistrationDto cdto)
	{
		return cs.registerCustomer(cdto);
	}


	

}
