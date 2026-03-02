package com.example.Foodzy.Dtos;

import java.util.List;

import com.example.Foodzy.entity.Address;
import com.example.Foodzy.entity.CartItem;
import com.example.Foodzy.entity.Customer;
import com.example.Foodzy.entity.Restaurant;

public class OrderDto {
	private Restaurant restaurant;
	private Customer customer;
	private double cost;
	private List<CartItem> items;
	private Address deliveryAddress;
	private Address pickUpAddress;
	private double distance;
	private String deliveryInstructions;
	public OrderDto() {
		super();
	}
	public OrderDto( Restaurant restaurant, Customer customer, double cost, List<CartItem> items,
			Address deliveryAddress, Address pickUpAddress, double distance, String deliveryInstructions) {
		super();
//		this.status = status;
		this.restaurant = restaurant;
		this.customer = customer;
		this.cost = cost;
		this.items = items;
		this.deliveryAddress = deliveryAddress;
		this.pickUpAddress = pickUpAddress;
		this.distance = distance;
		this.deliveryInstructions = deliveryInstructions;
	}
	@Override
	public String toString() {
		return "OrderDto [status=" +  ", restaurant=" + restaurant + ", customer=" + customer + ", cost=" + cost
				+ ", items=" + items + ", deliveryAddress=" + deliveryAddress + ", pickUpAddress=" + pickUpAddress
				+ ", distance=" + distance + ", deliveryInstructions=" + deliveryInstructions + "]";
	}
	
	
	public Restaurant getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public List<CartItem> getItems() {
		return items;
	}
	public void setItems(List<CartItem> items) {
		this.items = items;
	}
	public Address getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	public Address getPickUpAddress() {
		return pickUpAddress;
	}
	public void setPickUpAddress(Address pickUpAddress) {
		this.pickUpAddress = pickUpAddress;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public String getDeliveryInstructions() {
		return deliveryInstructions;
	}
	public void setDeliveryInstructions(String deliveryInstructions) {
		this.deliveryInstructions = deliveryInstructions;
	}

}
