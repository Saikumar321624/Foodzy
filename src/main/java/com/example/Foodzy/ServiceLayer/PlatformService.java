package com.example.Foodzy.ServiceLayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.Foodzy.Exceptions.couponNotException;
import com.example.Foodzy.Repositary.CouponRepo;
import com.example.Foodzy.Response.ResponseStructure;
import com.example.Foodzy.entity.Coupon;
@Service
public class PlatformService {
	@Autowired
	private CouponRepo couponRepo;

	public ResponseStructure<Coupon> createCoupon(Coupon c) {
		if(c==null) {
			throw new couponNotException();
		}
		couponRepo.save(c);
		
		ResponseStructure<Coupon> rs=new ResponseStructure<Coupon>();
		
		rs.setstatuscode(HttpStatus.ACCEPTED.value());
		rs.setMessage("coupon is created successfully");
		rs.setData(c);
		
		return rs;
		
		
	}
	
	public ResponseStructure<Coupon> deleteCoupon(long id) {
		Coupon c=couponRepo.findById(id).orElseThrow(()-> new RuntimeException("Order not found exception"));
		 if (c==null) {
			throw new couponNotException();
		}
		 couponRepo.delete(c);
		 ResponseStructure<Coupon> rs=new ResponseStructure<Coupon>();
		 
		 rs.setstatuscode(HttpStatus.OK.value());
			rs.setMessage("coupon delete successfully ");
			rs.setData(c);
			
			return rs;
	}

}
