package com.example.Foodzy.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.Foodzy.Response.ResponseStructure;

@ControllerAdvice
public class GLobalExceptionHandler {
	@ExceptionHandler(AddressNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> AddressNotFound()
	{
		ResponseStructure<String> rs=new ResponseStructure<String>();
		rs.setstatuscode(HttpStatus.NOT_FOUND.value());
		rs.setData("ADDRESS NOT FOUND");
		rs.setMessage("ADDRESS NOT FOUND");
		
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> customerNotFound()
	{
		ResponseStructure<String> rs=new ResponseStructure<String>();
		rs.setstatuscode(HttpStatus.NOT_FOUND.value());
		rs.setData("CUSTOMER NOT FOUND");
		rs.setMessage("CUSTOMER NOT FOUND");
		
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(OrderNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> orderNotFound()
	{
		ResponseStructure<String> rs=new ResponseStructure<String>();
		rs.setstatuscode(HttpStatus.NOT_FOUND.value());
		rs.setData("ORDER NOT FOUND");
		rs.setMessage("ORDER NOT FOUND");
		
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(ItemNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> itemNotFound()
	{
		ResponseStructure<String> rs=new ResponseStructure<String>();
		rs.setstatuscode(HttpStatus.NOT_FOUND.value());
		rs.setData("ITEM NOT FOUND");
		rs.setMessage(" ITEM NOT FOUND");
		
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(DeliveryPartnerNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> deliveryPartnerNotFound()
	{
		ResponseStructure<String> rs=new ResponseStructure<String>();
		rs.setstatuscode(HttpStatus.NOT_FOUND.value());
		rs.setData("PARTNER NOT FOUND");
		rs.setMessage(" PARTNER NOT FOUND");
		
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(RestaurantnotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> productNotFound()
	{
		ResponseStructure<String> rs=new ResponseStructure<String>();
		rs.setstatuscode(HttpStatus.NOT_FOUND.value());
		rs.setData("RESTAURANT NOT FOUND");
		rs.setMessage(" RESTAURANT NOT FOUND");
		
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.NOT_FOUND);
	}
	
	

}
