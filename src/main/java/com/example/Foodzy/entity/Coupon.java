package com.example.Foodzy.entity;

import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Coupon {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private String type;
	private String status;
	private int offer;
	private double minOrderPrice;
	private double maxOrderPrice;
	private int maxCoupons;
	private  LocalTime expirydate;
	public long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getOffer() {
		return offer;
	}
	public void setOffer(int offer) {
		this.offer = offer;
	}
	public double getMinOrderPrice() {
		return minOrderPrice;
	}
	public void setMinOrderPrice(double minOrderPrice) {
		this.minOrderPrice = minOrderPrice;
	}
	public double getMaxOrderPrice() {
		return maxOrderPrice;
	}
	public void setMaxOrderPrice(double maxOrderPrice) {
		this.maxOrderPrice = maxOrderPrice;
	}
	public int getMaxCoupons() {
		return maxCoupons;
	}
	public void setMaxCoupons(int maxCoupons) {
		this.maxCoupons = maxCoupons;
	}
	public LocalTime getExpirydate() {
		return expirydate;
	}
	public void setExpirydate(LocalTime expirydate) {
		this.expirydate = expirydate;
	}
	public Coupon(String name, String type, String status, int offer, double minOrderPrice, double maxOrderPrice,
			int maxCoupons, LocalTime expirydate) {
		super();
		this.name = name;
		this.type = type;
		this.status = status;
		this.offer = offer;
		this.minOrderPrice = minOrderPrice;
		this.maxOrderPrice = maxOrderPrice;
		this.maxCoupons = maxCoupons;
		this.expirydate = expirydate;
	}
	public Coupon() {
		super();
	}
	@Override
	public String toString() {
		return "Coupon [id=" + id + ", name=" + name + ", type=" + type + ", status=" + status + ", offer=" + offer
				+ ", minOrderPrice=" + minOrderPrice + ", maxOrderPrice=" + maxOrderPrice + ", maxCoupons=" + maxCoupons
				+ ", expirydate=" + expirydate + "]";
	}
	
	

}
