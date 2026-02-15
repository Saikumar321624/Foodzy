package com.example.Foodzy.ServiceLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.Foodzy.Dtos.DeliveryRegistrationDto;
import com.example.Foodzy.Repositary.DeliveryPartnerRepo;
import com.example.Foodzy.Response.ResponseStructure;
import com.example.Foodzy.entity.DeliveryPartner;
@Service
public class DeliveryService {
	
	@Autowired
	private DeliveryPartnerRepo deliveryPartnerRepo;

	
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


	

}
