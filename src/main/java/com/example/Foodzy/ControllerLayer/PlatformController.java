package com.example.Foodzy.ControllerLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Foodzy.Exceptions.couponNotException;
import com.example.Foodzy.Response.ResponseStructure;
import com.example.Foodzy.ServiceLayer.PlatformService;
import com.example.Foodzy.entity.Coupon;

@RestController
@RequestMapping("/platform")
public class PlatformController {
	@Autowired
	private PlatformService platformService;
	
	
	
	@PostMapping("/createcoupon")
	public ResponseStructure<Coupon> createCoupon(@RequestBody Coupon c) {
		
		 return platformService.createCoupon(c);
	}
	
	@DeleteMapping("/deletecoupon")
	public ResponseStructure<Coupon> deleteCoupon (@RequestParam long id) {
		return platformService.deleteCoupon(id);
	
	}

}
