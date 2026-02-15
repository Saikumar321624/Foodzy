package com.example.Foodzy.ControllerLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Foodzy.Dtos.RestaurentRegistrationDto;
import com.example.Foodzy.Response.ResponseStructure;
import com.example.Foodzy.ServiceLayer.RestaurentService;

@RestController
@RequestMapping("/restaurant")
public class RestaurentController {
	@Autowired
	RestaurentService rs;
	@PostMapping("/register")
	public ResponseStructure<RestaurentRegistrationDto> restaurentRegister(@RequestBody RestaurentRegistrationDto rdto)
	{
		return rs.registerRestaurent(rdto);
	}
	
}
