package com.example.Foodzy.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CouponRedemption {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Coupon coupon;
	private Customer customer;
	private Orders orders;
	public CouponRedemption(Coupon coupon, Customer customer, Orders orders) {
		super();
		this.coupon = coupon;
		this.customer = customer;
		this.orders = orders;
	}
	public CouponRedemption() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Coupon getCoupon() {
		return coupon;
	}
	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Orders getOrders() {
		return orders;
	}
	public void setOrders(Orders orders) {
		this.orders = orders;
	}
	@Override
	public String toString() {
		return "CouponRedemption [id=" + id + ", coupon=" + coupon + ", customer=" + customer + ", orders=" + orders
				+ "]";
	}
	
	

}
