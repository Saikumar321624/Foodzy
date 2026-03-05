package com.example.Foodzy.Dtos;

import java.util.List;

import com.example.Foodzy.entity.Address;
import com.example.Foodzy.entity.CartItem;

public class OrderNeedconsetDto {
	private List<CartItem> cart;
	private Address deliveryAddress;
	private String status;
	private String date;
	private double price;
	public OrderNeedconsetDto(List<CartItem> cart, Address deliveryAddress, String status, String date, double price) {
		super();
		this.cart = cart;
		this.deliveryAddress = deliveryAddress;
		this.status = status;
		this.date = date;
		this.price = price;
	}
	public OrderNeedconsetDto() {
		super();
	}
	public List<CartItem> getCart() {
		return cart;
	}
	public void setCart(List<CartItem> cart) {
		this.cart = cart;
	}
	public Address getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "OrderNeedconsetDto [cart=" + cart + ", deliveryAddress=" + deliveryAddress + ", status=" + status
				+ ", date=" + date + ", price=" + price + "]";
	}
	
	
	
	
}
