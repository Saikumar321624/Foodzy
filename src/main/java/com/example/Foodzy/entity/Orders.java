package com.example.Foodzy.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Orders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String status;
	private String restarunt;
	private String customer;
    private int cost;
    @OneToMany
    List<Item> items;
    private String address;
    private String pickupAddress;
    private int otp;
    private String deliveryPartner;
    private int payment;
    private String estimatedTime;
    private int distance;
    private int discount;
    private String coupones;
    private String specialRequest;
    private String deliveryInstructions;
    private String date;
	public Orders(String status, String restarunt, String customer, int cost, List<Item> items, String address,
			String pickupAddress, int otp, String deliveryPartner, int payment, String estimatedTime, int distance,
			int discount, String coupones, String specialRequest, String deliveryInstructions, String date) {
		super();
		this.status = status;
		this.restarunt = restarunt;
		this.customer = customer;
		this.cost = cost;
		this.items = items;
		this.address = address;
		this.pickupAddress = pickupAddress;
		this.otp = otp;
		this.deliveryPartner = deliveryPartner;
		this.payment = payment;
		this.estimatedTime = estimatedTime;
		this.distance = distance;
		this.discount = discount;
		this.coupones = coupones;
		this.specialRequest = specialRequest;
		this.deliveryInstructions = deliveryInstructions;
		this.date = date;
	}
	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRestarunt() {
		return restarunt;
	}
	public void setRestarunt(String restarunt) {
		this.restarunt = restarunt;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPickupAddress() {
		return pickupAddress;
	}
	public void setPickupAddress(String pickupAddress) {
		this.pickupAddress = pickupAddress;
	}
	public int getOtp() {
		return otp;
	}
	public void setOtp(int otp) {
		this.otp = otp;
	}
	public String getDeliveryPartner() {
		return deliveryPartner;
	}
	public void setDeliveryPartner(String deliveryPartner) {
		this.deliveryPartner = deliveryPartner;
	}
	public int getPayment() {
		return payment;
	}
	public void setPayment(int payment) {
		this.payment = payment;
	}
	public String getEstimatedTime() {
		return estimatedTime;
	}
	public void setEstimatedTime(String estimatedTime) {
		this.estimatedTime = estimatedTime;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public String getCoupones() {
		return coupones;
	}
	public void setCoupones(String coupones) {
		this.coupones = coupones;
	}
	public String getSpecialRequest() {
		return specialRequest;
	}
	public void setSpecialRequest(String specialRequest) {
		this.specialRequest = specialRequest;
	}
	public String getDeliveryInstructions() {
		return deliveryInstructions;
	}
	public void setDeliveryInstructions(String deliveryInstructions) {
		this.deliveryInstructions = deliveryInstructions;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Orders [id=" + id + ", status=" + status + ", restarunt=" + restarunt + ", customer=" + customer
				+ ", cost=" + cost + ", items=" + items + ", address=" + address + ", pickupAddress=" + pickupAddress
				+ ", otp=" + otp + ", deliveryPartner=" + deliveryPartner + ", payment=" + payment + ", estimatedTime="
				+ estimatedTime + ", distance=" + distance + ", discount=" + discount + ", coupones=" + coupones
				+ ", specialRequest=" + specialRequest + ", deliveryInstructions=" + deliveryInstructions + ", date="
				+ date + "]";
	}
    

}
