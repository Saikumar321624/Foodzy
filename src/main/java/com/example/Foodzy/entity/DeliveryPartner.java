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
	private long mobileNumber;
	private String mailId;
	private int rating;
	@OneToOne
	private Address address;
	@OneToMany
	private List<Order> order;
	private String  vehicleNumber;
	private String status;
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
	public long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
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
	public String getVehicleNumber() {
		return vehicleNumber;
	}
	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public DeliveryPartner(String name, long mobileNumber, String mailId, int rating, Address address,
			List<Order> order, String vehicleNumber, String status) {
		super();
		this.name = name;
		this.mobileNumber = mobileNumber;
		this.mailId = mailId;
		this.rating = rating;
		this.address = address;
		this.order = order;
		this.vehicleNumber = vehicleNumber;
		this.status = status;
	}
	public DeliveryPartner() {
		super();
	}
	@Override
	public String toString() {
		return "DeliveryPartner [id=" + id + ", name=" + name + ", mobileNumber=" + mobileNumber + ", mailId=" + mailId
				+ ", rating=" + rating + ", address=" + address + ", order=" + order + ", vehicleNumber="
				+ vehicleNumber + ", status=" + status + "]";
	}
	
	
	
}
