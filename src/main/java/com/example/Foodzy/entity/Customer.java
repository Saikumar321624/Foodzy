package com.example.Foodzy.entity;

import java.util.ArrayList;
import java.util.List;



import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
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
	@OneToMany(cascade = CascadeType.ALL)
	private  List<Address> addresses=new ArrayList<Address>();
	@OneToMany(cascade = CascadeType.ALL)
	private List<Orders>orders;
	@OneToMany(cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<CartItem>cart;
	private double penality;
	private double vallet;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public Long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public double getPenality() {
		return penality;
	}

	public void setPenality(double penality) {
		this.penality = penality;
	}

	public double getVallet() {
		return vallet;
	}

	public void setVallet(double d) {
		this.vallet = d;
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
