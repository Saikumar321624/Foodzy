package com.example.Foodzy.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Foodzy.entity.Coupon;

@Repository
public interface CouponRepo extends JpaRepository<Coupon, Long> {


}
