package com.example.Foodzy.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Restaurant {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private long mobileno;
	private String mail;
	@OneToOne
	private Address address;
	@OneToMany
	private List<Item> menu;
	
	private int rating;
	private String description;
	@OneToMany
	private List<Orders>orders;
	private double packagingfee;
	private String type;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getMobileno() {
		return mobileno;
	}
	public void setMobileno(long mobileno) {
		this.mobileno = mobileno;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public List<Item> getMenu() {
		return menu;
	}
	public void setMenu(List<Item> menu) {
		this.menu = menu;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Orders> getOrders() {
		return orders;
	}
	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}
	public double getPackagingfee() {
		return packagingfee;
	}
	public void setPackagingfee(double packagingfee) {
		this.packagingfee = packagingfee;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Restaurant(String name, long mobileno, String mail, Address address, List<Item> menu, int rating,
			String description, List<Orders> orders, double packagingfee, String type) {
		super();
		this.name = name;
		this.mobileno = mobileno;
		this.mail = mail;
		this.address = address;
		this.menu = menu;
		this.rating = rating;
		this.description = description;
		this.orders = orders;
		this.packagingfee = packagingfee;
		this.type = type;
	}
	public Restaurant() {
		super();
	}
	@Override
	public String toString() {
		return "Restaurant [id=" + id + ", name=" + name + ", mobileno=" + mobileno + ", mail=" + mail + ", address="
				+ address + ", menu=" + menu + ", rating=" + rating + ", description=" + description + ", orders="
				+ orders + ", packagingfee=" + packagingfee + ", type=" + type + "]";
	}
	
	
	}
	



