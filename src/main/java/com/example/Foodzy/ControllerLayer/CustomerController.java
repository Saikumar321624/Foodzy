package com.example.Foodzy.ControllerLayer;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

//import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Foodzy.Dtos.CartItemResponse;
import com.example.Foodzy.Dtos.CustomerRegistrationDto;
import com.example.Foodzy.Dtos.OrderNeedconsetDto;
import com.example.Foodzy.Dtos.RestaurentInfo;
import com.example.Foodzy.Response.ResponseStructure;
import com.example.Foodzy.ServiceLayer.CustomerService;
import com.example.Foodzy.entity.Address;
import com.example.Foodzy.entity.CartItem;
import com.example.Foodzy.entity.Customer;
import com.example.Foodzy.entity.Orders;


@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	
	@Autowired
	CustomerService cs;
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseStructure<CustomerRegistrationDto> customerRegistration(@RequestBody CustomerRegistrationDto cdto)
	{
		return cs.registerCustomer(cdto);
	}

	@GetMapping("/find")
	public ResponseStructure<Customer> findCustomer(@RequestParam long mobileNumber)
	{
		return cs.find(mobileNumber);
	}
	@DeleteMapping("/delete")
	public ResponseStructure<Customer> deleteCustomer(@RequestParam long mobileNumber)
	{
		return cs.delete(mobileNumber);
	}
	
	@PutMapping("/addtocart")
	public ResponseEntity<ResponseStructure<CartItemResponse>> Addtocart(@RequestParam long mobileNumber,@RequestParam Long itemid,@RequestParam int quantity) {
	  return	cs.AddCart(mobileNumber,itemid,quantity);
	}
	
	@GetMapping("/searchItemOrResturant")
	public ResponseEntity<ResponseStructure<List<RestaurentInfo>>> SearchItemOrRes(@RequestParam long cusmobile,@RequestParam String searchkey ) {
		return cs.SearchItemOrRestaurent(cusmobile,searchkey);
	}
	@PutMapping("/addAddress")
	public ResponseStructure<Address> addAddress(@RequestParam long mobileNumber,@RequestBody Address address) {
		return cs.addAddress(address,mobileNumber);
	}

	@PostMapping("/placeOrder")

	public ResponseStructure<OrderNeedconsetDto> placeOrder(@RequestParam long mobileNumber,@RequestParam String orderType,@RequestParam String deliveryInstructions,@RequestParam String specialRequest)
	{
		return  cs.placeOrder(mobileNumber,orderType,deliveryInstructions,specialRequest);
	}
	@PatchMapping("/confirmOrder")
	public ResponseStructure<Orders> confirmOrder(@RequestParam long mobileNo,@RequestParam long orderId)
	{
		return cs.confirmOrder(mobileNo,orderId);
	}
//	@PatchMapping("/denyOrder")
//	public ResponseStructure<Orders> denyOrder(@RequestParam long orderId,@RequestParam long mobileNumber) {
//		return cs.denyOrder(orderId,mobileNumber);
//	}
	
	@PatchMapping("/CancelOrder")
	public ResponseStructure<Orders> CancelOrder(@RequestParam long CustMobno,@RequestParam long Orderid) {
		return cs.CancelOrder(CustMobno,Orderid);
	}
	@DeleteMapping
	public ResponseStructure<Customer> removeItemFromCart(@RequestParam long mobileNumber,@RequestParam long itemId)
	{
		return cs.removeItemFromCart(mobileNumber,itemId);
	}
	

}
