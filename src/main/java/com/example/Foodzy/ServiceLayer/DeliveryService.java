package com.example.Foodzy.ServiceLayer;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Foodzy.Dtos.DeliveryRegistrationDto;
//import com.example.Foodzy.Dtos.OdersShowDto;
import com.example.Foodzy.Dtos.OrdersShowDto;
import com.example.Foodzy.Exceptions.LocationNotFoundException;
import com.example.Foodzy.Repositary.DeliveryPartnerRepo;
import com.example.Foodzy.Repositary.OrdersRepo;
import com.example.Foodzy.Response.ResponseStructure;
import com.example.Foodzy.entity.CartItem;
import com.example.Foodzy.entity.DeliveryPartner;
import com.example.Foodzy.entity.Item;
import com.example.Foodzy.entity.Orders;
import com.example.Foodzy.entity.Restaurant;

import ch.qos.logback.core.util.Duration;
import jakarta.servlet.http.HttpServletResponse;
@Service
public class DeliveryService {
	
	@Autowired
	private DeliveryPartnerRepo deliveryPartnerRepo;
	
	@Autowired
	private OrdersRepo orderRepository;
	
	@Autowired
	private RedisTemplate<String, String > redisTemplate;

	
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

	public boolean acceptOrder(Long orderId, Long partnerId) {
	    Orders order = orderRepository.findById(orderId)
	            .orElseThrow(() -> new RuntimeException("Order not found"));
	    DeliveryPartner deliveryPartner = deliveryPartnerRepo.findById(partnerId)
	            .orElseThrow(() -> new RuntimeException("Partner not found"));

	    String lockKey = "order_lock" + orderId;
	    Boolean locked = redisTemplate.opsForValue()
	            .setIfAbsent(lockKey, partnerId.toString());

	    if (Boolean.TRUE.equals(locked)) {
            order.setDeliveryPartner(deliveryPartner);
            orderRepository.save(order);
            redisTemplate.delete("order:" + orderId);

            return true;
        }
        return false;
	}


	public void dpRestaurantLoc(double dplat, double dplong, double reslat, double reslong, HttpServletResponse resp) {
		// TODO Auto-generated method stub
				String getdir = "https://www.google.com/maps/dir/?api=1&origin=" + dplat + "," + dplong + "&destination="
				        + reslat + "," + reslong + "&travelmode=driving";
				try {
					resp.sendRedirect(getdir);
				} catch (IOException e) {
					throw new LocationNotFoundException();
				}
	}


	public ResponseStructure<OrdersShowDto> showOrder(long id) {
		// TODO Auto-generated method stub
	Orders order=	orderRepository.findById(id).orElseThrow(()->new RuntimeException("order not found exception"));
	  OrdersShowDto ordersShowDto=new OrdersShowDto();
	  
	  ordersShowDto.setId(order.getId());
	  Restaurant restaurant=new Restaurant();
	  ordersShowDto.setId(id);
	  ordersShowDto.setItemName(order.getItems().get(0).getItem().getItemName());
	  ordersShowDto.setPickupaddress(order.getRestarunt().getAddress());
	  ordersShowDto.setDropaddress(order.getPickupAddress());
	  
	  ResponseStructure<OrdersShowDto> rs=new ResponseStructure<OrdersShowDto>();
	  rs.setstatuscode(HttpStatus.ACCEPTED.value());
	  rs.setMessage("data has successfully loaded");
	  rs.setData(ordersShowDto);
	  
	  return rs;
	  
	}
	
	}


	


