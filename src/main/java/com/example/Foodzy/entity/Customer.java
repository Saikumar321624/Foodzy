package com.example.Foodzy.entity;

import java.util.ArrayList;
import java.util.List;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private Long mobileNumber;
	private String mail;
	private  String gender;
	@OneToMany
	private  List<Address> addresses=new ArrayList<Address>();
	@OneToMany
	private List<Orders>orders;
	@OneToMany
	private List<CartItem> cart;
	
	
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
	public Long getMobileNo() {
		return mobileNumber;
	}
	public void setMobileNo(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public List<Address> getAddress() {
		return addresses;
	}
	public void setAddress(List<Address> address) {
		this.addresses = address;
	}
	public List<Orders> getOrders() {
		return orders;
	}
	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}
	public List<CartItem> getCart() {
		return cart;
	}
	public void setCart(List<CartItem> cart) {
		this.cart = cart;
	}
	public Customer(String name, Long mobileNo, String mail, String gender, List<Address> address, List<Orders> orders,
			List<CartItem> cart) {
		super();
		this.name = name;
		this.mobileNumber = mobileNo;
		this.mail = mail;
		this.gender = gender;
		this.addresses = address;
		this.orders = orders;
		this.cart = cart;
	}
	public Customer() {
		super();
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", mobileNo=" + mobileNumber + ", mail=" + mail + ", gender="
				+ gender + ", address=" + addresses + ", orders=" + orders + ", cart=" + cart + "]";
	}
	

}
