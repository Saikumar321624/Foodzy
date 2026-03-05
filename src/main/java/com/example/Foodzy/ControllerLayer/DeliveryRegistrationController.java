package com.example.Foodzy.ControllerLayer;

import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Foodzy.Dtos.DeliveryRegistrationDto;
import com.example.Foodzy.Dtos.OrdersShowDto;
import com.example.Foodzy.Response.ResponseStructure;
import com.example.Foodzy.ServiceLayer.DeliveryService;
import com.example.Foodzy.ServiceLayer.RedisService;
import com.example.Foodzy.entity.DeliveryPartner;
import com.example.Foodzy.entity.Orders;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/deliveryPartner")
public class DeliveryRegistrationController {
	
	@Autowired
	private DeliveryService deliveryService;
	@Autowired
	RedisService redisService;
	
	@PostMapping("/register")
	public ResponseStructure<DeliveryRegistrationDto> DeliveryRegistration(@RequestBody DeliveryRegistrationDto deliveryRegistrationDto) {
	  return	deliveryService.saveDriver(deliveryRegistrationDto);
	}
	
	
	@GetMapping("/find")
	public ResponseStructure<DeliveryPartner> DeliveryINfo(@RequestParam long mob) {
		 return deliveryService.findDelivery(mob);
	}
	
	@DeleteMapping("/delete")
	public ResponseStructure<DeliveryPartner> DeleteDelivery(@RequestParam long mob) {
		return deliveryService.deleteDeliveryPartner(mob);
	}
	@PostMapping("/updateLocation")
	public String updateLocation(@RequestParam long id,@RequestParam double latitude,@RequestParam double longitude )
	{
		return redisService.updateLocation(id,latitude,longitude);
	}
	@PatchMapping("/acceptOrder")
	public String acceptOrder(@RequestParam Long orderId,@RequestParam Long partnerId)
	{
	boolean accept=	deliveryService.acceptOrder(orderId,partnerId);
	
	 return accept?"Order is accepted successfully":"order is alreday assigned";
	}
	
	@GetMapping("/acceptrder")
	public ResponseStructure<OrdersShowDto> Acceptorder(@RequestParam long id) {
	 return	deliveryService.showOrder(id);
	}
	@GetMapping("/ResturantLocation")
	public void ResturantLocation(@RequestParam double dplat,@RequestParam double dplong ,@RequestParam double reslat,@RequestParam double reslong,HttpServletResponse resp) {
	 	deliveryService.dpRestaurantLoc(dplat,dplong,reslat,reslong,resp);
	}
	
	@GetMapping("/markasdeliver")
	public ResponseStructure<Orders> Deliverye(@RequestParam Long dpmob,@RequestParam Long Orderid,@RequestParam Integer otp) {
	  return	deliveryService.MarkAsDeliver(dpmob,Orderid,otp);
	}
	
	
}

