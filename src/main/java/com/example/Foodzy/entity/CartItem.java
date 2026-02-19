package com.example.Foodzy.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CartItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private Item item;
	private Customer customer;
	private Restaurant restaurant;
	private int quantity;
	public CartItem(long id, Item item, Customer customer, Restaurant restaurant, int quantity) {
		super();
		this.id = id;
		this.item = item;
		this.customer = customer;
		this.restaurant = restaurant;
		this.quantity = quantity;
	}
	public CartItem() {
		super();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Restaurant getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "CartItem [id=" + id + ", item=" + item + ", customer=" + customer + ", restaurant=" + restaurant
				+ ", quantity=" + quantity + "]";
	}
	
	

}
