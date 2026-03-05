package com.example.Foodzy.ServiceLayer;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.Foodzy.Dtos.DeliveryRegistrationDto;
import com.example.Foodzy.Exceptions.DeliveryPartnerNotFoundException;
import com.example.Foodzy.Exceptions.OrderNotFoundException;
import com.example.Foodzy.Repositary.DeliveryPartnerRepo;
import com.example.Foodzy.Repositary.OrdersRepo;
import com.example.Foodzy.Response.ResponseStructure;
import com.example.Foodzy.entity.DeliveryPartner;
import com.example.Foodzy.entity.Orders;
@Service
public class DeliveryService {
	
	@Autowired
	private DeliveryPartnerRepo deliveryPartnerRepo;
	@Autowired
	private OrdersRepo orderRepo;
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	
	public ResponseStructure<DeliveryRegistrationDto> saveDriver(DeliveryRegistrationDto deliveryRegistrationDto) {
		// TODO Auto-generated method stub
		DeliveryPartner dp=new DeliveryPartner();
		dp.setName(deliveryRegistrationDto.getName());
		dp.setMailId(deliveryRegistrationDto.getMailId());
		dp.setMobileNumber(deliveryRegistrationDto.getMob());
		dp.setVehicleNumber(deliveryRegistrationDto.getVehNo());
		
		deliveryPartnerRepo.save(dp);
		
		ResponseStructure<DeliveryRegistrationDto> rsp=new ResponseStructure<DeliveryRegistrationDto>();
		rsp.setMessage("data is successfully registred");
		rsp.setstatuscode(HttpStatus.CREATED.value());
		rsp.setData(deliveryRegistrationDto);
		
		return rsp;
	}


	public ResponseStructure<DeliveryPartner> findDelivery(long mob) {
		// TODO Auto-generated method stub
	DeliveryPartner dp=deliveryPartnerRepo.findByMobileNumber(mob);
	
	ResponseStructure<DeliveryPartner> rsp=new ResponseStructure<DeliveryPartner>();
	if(dp!=null) {
		rsp.setMessage("data is successfully retreved");
		rsp.setstatuscode(HttpStatus.ACCEPTED.value());
		rsp.setData(dp);
		return rsp;
	}else {
		rsp.setMessage("Delivery with mobile number is not present");
		rsp.setstatuscode(HttpStatus.BAD_REQUEST.value());
		rsp.setData(dp);
		
		return rsp;
	}
		
	}


	public ResponseStructure<DeliveryPartner> deleteDeliveryPartner(long mob) {
		// TODO Auto-generated method stub
		DeliveryPartner dp=deliveryPartnerRepo.findByMobileNumber(mob);
		
		ResponseStructure<DeliveryPartner> rsp=new ResponseStructure<DeliveryPartner>();
		if(dp!=null) {
			rsp.setMessage("data is successfully deleted");
			rsp.setstatuscode(HttpStatus.ACCEPTED.value());
			deliveryPartnerRepo.delete(dp);
			return rsp;
		}else {
			rsp.setMessage("Delivery with mobile number is not present");
			rsp.setstatuscode(HttpStatus.BAD_REQUEST.value());
			rsp.setData(dp);
			return rsp;
		}
	}


	public void acceptOrder(Long orderId, long partnerId) {
		DeliveryPartner deliveryPartner=deliveryPartnerRepo.findById(partnerId).orElseThrow(()->new DeliveryPartnerNotFoundException());
		Orders order=orderRepo.findById(orderId).orElseThrow(()->new OrderNotFoundException());
		if(order.getStatus().equals("Picked")) throw new RuntimeException("order already assigned");
		order.setStatus("Picked");
		order.setDeliveryPartner(deliveryPartner);
		orderRepo.save(order);
		removeOrderFromAllPartners(orderId);
	}


	private void removeOrderFromAllPartners(Long orderId) {
		Set<String> keys=redisTemplate.keys("partner:*:orders");
		if(keys!=null)
		{
			for(String key:keys)
			{
				redisTemplate.opsForList().remove(key, 0, orderId.toString());
			}
		}
		
	}


	

}
