package com.example.Foodzy.ControllerLayer;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Foodzy.Dtos.RestaurentRegistrationDto;
import com.example.Foodzy.Response.ResponseStructure;
import com.example.Foodzy.ServiceLayer.RedisService;
import com.example.Foodzy.ServiceLayer.RestaurentService;
import com.example.Foodzy.entity.Item;
import com.example.Foodzy.entity.Restaurant;

@RestController
@RequestMapping("/restaurant")
public class RestaurentController {
	@Autowired
	RestaurentService rs;
	@Autowired
	private RedisService redisService;
	@PostMapping("/register")
	public ResponseStructure<Restaurant> restaurentRegister(@RequestBody RestaurentRegistrationDto rdto)
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

	@PatchMapping("/addItemsToMenu")
	public ResponseStructure<Restaurant> menu(@RequestParam long mobileNo,@RequestBody Item item)
	{
		return rs.addItem(mobileNo,item);
	}
	@PatchMapping("/updateStatus")
	public ResponseStructure<Restaurant> updateStatus(@RequestParam long mobileNo)
	{
		return rs.updateStatus(mobileNo);
	}
	@PatchMapping("/updateItemAvailability")
	public ResponseStructure<Item> updateItemavailability(@RequestParam long restauranMobileNo,@RequestParam long itemId)
	{
		return rs.updateItemavailability(restauranMobileNo,itemId);
	}
	@GetMapping("/menu")
	public ResponseStructure<List<Item>> getMenu(@RequestParam long mobileNo)
	{
		return rs.getMenu(mobileNo);
	}
	@GetMapping("/nearByPartners")
	public List<String> getNearByPartners(@RequestParam double latitude,@RequestParam double longitude,@RequestParam double radiusKm)
	{
		return redisService.getNearByPartners(latitude,longitude,radiusKm);
	}
	@PatchMapping("/acceptOrder") 
	public ResponseStructure<List<String>> acceptOrder(@RequestParam long orderId)
	{
		return rs.acceptOrder(orderId);
	}
	@DeleteMapping("/removeItemFromMEnu")
	public ResponseStructure<Restaurant> removeItemFromMenu(@RequestParam Long mobileNumber,@RequestParam Long itemId)
	{
		return rs.removeItemFromMenu(mobileNumber,itemId);
	}
	@PatchMapping("/updateItemDetails")
	public ResponseStructure<Item> updateItemDetails(@RequestParam Long mobilenumber,@RequestParam Long  itemId)
	{
		return rs.updateItemDetails(mobilenumber,itemId);
	}

	
	@PatchMapping("/cancelOrder")
	public ResponseStructure<Restaurant> CancelisationOrder(@RequestParam Long restMobno,@RequestParam long orderid) {
		 return rs.CancelOrder(restMobno,orderid);
	}
	
	
}