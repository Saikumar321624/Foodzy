package com.example.Foodzy.entity;

//import java.lang.foreign.Linker.Option;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class CartItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@OneToOne
	private Item item; 
	
	@ManyToOne
	@JsonBackReference
	private Customer customer;
	
	@OneToOne
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
	public void setItem(Item i) {
		this.item = i;
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
