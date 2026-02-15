package com.example.Foodzy.ControllerLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Foodzy.Dtos.RestaurentRegistrationDto;
import com.example.Foodzy.Response.ResponseStructure;
import com.example.Foodzy.ServiceLayer.RestaurentService;
import com.example.Foodzy.entity.Restaurant;

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
	@GetMapping("/find")
	public ResponseStructure<Restaurant> FindRestaurant(@RequestParam long mobileNo)
	{
		return rs.findRestrant(mobileNo);
	}
	@DeleteMapping("/delete")
	public ResponseStructure<Restaurant> deleteMapping(@RequestParam long mobileNo )
	{
		return rs.deleterestaurant(mobileNo);
	}

}
