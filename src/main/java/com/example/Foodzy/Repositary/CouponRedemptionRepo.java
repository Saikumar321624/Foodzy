package com.example.Foodzy.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Foodzy.entity.CouponRedemption;
@Repository
public interface CouponRedemptionRepo extends JpaRepository<CouponRedemption, Long> {

	CouponRedemption findByCustomermobileNumberAndCouponId(long mobileNumber, long couponid);

}
