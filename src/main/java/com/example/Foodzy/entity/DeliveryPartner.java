package com.example.Foodzy.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.criteria.Order;

@Entity
public class DeliveryPartner {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private long mobilenumber;
	private String mailid;
	private int rating;
	@OneToOne
	private Address address;
	@OneToMany
	private List<Order> order;
	@OneToOne
	private Vehicle vehicle;
	private String status;
	public DeliveryPartner( String name, long mobilenumber, String mailid, int rating, Address address,
			List<Order> order, Vehicle vehicle, String status) {
		super();
		this.name = name;
		this.mobilenumber = mobilenumber;
		this.mailid = mailid;
		this.rating = rating;
		this.address = address;
		this.order = order;
		this.vehicle = vehicle;
		this.status = status;
	}
	public DeliveryPartner() {
		super();
	}
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
	public long getMobilenumber() {
		return mobilenumber;
	}
	public void setMobilenumber(long mobilenumber) {
		this.mobilenumber = mobilenumber;
	}
	public String getMailid() {
		return mailid;
	}
	public void setMailid(String mailid) {
		this.mailid = mailid;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public List<Order> getOrder() {
		return order;
	}
	public void setOrder(List<Order> order) {
		this.order = order;
	}
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "DeliveryPartner [id=" + id + ", name=" + name + ", mobilenumber=" + mobilenumber + ", mailid=" + mailid
				+ ", rating=" + rating + ", address=" + address + ", order=" + order + ", vehicle=" + vehicle
				+ ", status=" + status + "]";
	}
	
	
}
