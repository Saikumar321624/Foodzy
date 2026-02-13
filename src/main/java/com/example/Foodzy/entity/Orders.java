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
	private long id;
	private String status;
	private String restarunt;
	private Customer customer;
    private int cost;
    @OneToMany
    List<Item> items;
    private Address address;
    private Address pickupAddress;
    private int otp;
    private DeliveryPartner deliveryPartner;
    private Payment payment;
    private String estimatedTime;
    private int distance;
    private double discount;
    private String coupons;
    private String specialRequest;
    private String deliveryInstructions;
    private String date;
	
	public Orders(String status, String restarunt, Customer customer, int cost, List<Item> items, Address address,
			Address pickupAddress, int otp, DeliveryPartner deliveryPartner, Payment payment, String estimatedTime,
			int distance, double discount, String coupons, String specialRequest, String deliveryInstructions,
			String date) {
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
		this.coupons = coupons;
		this.specialRequest = specialRequest;
		this.deliveryInstructions = deliveryInstructions;
		this.date = date;
	}

	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Address getPickupAddress() {
		return pickupAddress;
	}

	public void setPickupAddress(Address pickupAddress) {
		this.pickupAddress = pickupAddress;
	}

	public int getOtp() {
		return otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}

	public DeliveryPartner getDeliveryPartner() {
		return deliveryPartner;
	}

	public void setDeliveryPartner(DeliveryPartner deliveryPartner) {
		this.deliveryPartner = deliveryPartner;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
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

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public String getCoupons() {
		return coupons;
	}

	public void setCoupons(String coupons) {
		this.coupons = coupons;
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
				+ estimatedTime + ", distance=" + distance + ", discount=" + discount + ", coupons=" + coupons
				+ ", specialRequest=" + specialRequest + ", deliveryInstructions=" + deliveryInstructions + ", date="
				+ date + "]";
	}


	
    

}
