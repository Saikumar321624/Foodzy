package com.example.Foodzy.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private Long mobileno;
	private String mail;
	private  String gender;
	private  Address address;
	@OneToMany
	private List<Orders>orders;
	@OneToMany
	private List<Item>item;
	public long getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getMobileno() {
		return mobileno;
	}
	public void setMobileno(Long mobileno) {
		this.mobileno = mobileno;
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
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public List<Orders> getOrders() {
		return orders;
	}
	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}
	public List<Item> getItem() {
		return item;
	}
	public void setItem(List<Item> item) {
		this.item = item;
	}
	public Customer(int id, String name, Long mobileno, String mail, String gender, Address address,
			List<Orders> orders, List<Item> item) {
		super();
		
		this.name = name;
		this.mobileno = mobileno;
		this.mail = mail;
		this.gender = gender;
		this.address = address;
		this.orders = orders;
		this.item = item;
	}
	public Customer() {
		super();
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", mobileno=" + mobileno + ", mail=" + mail + ", gender="
				+ gender + ", address=" + address + ", orders=" + orders + ", item=" + item + "]";
	}
	
	
	
	
	
	

}
