package com.example.Foodzy.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private double amount;
	private String paymentType;
	private String status;
	private int orders;
	@Override
	public String toString() {
		return "Payment [id=" + id + ", amount=" + amount + ", paymentType=" + paymentType + ", status=" + status
				+ ", orders=" + orders + "]";
	}
	public Payment() {
		super();
	}
	public Payment( double amount, String paymentType, String status, int orders) {
		super();
		this.amount = amount;
		this.paymentType = paymentType;
		this.status = status;
		this.orders = orders;
	}
	
	public long getId() {
		return  id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getOrders() {
		return orders;
	}
	public void setOrders(int orders) {
		this.orders = orders;
	}
	

}
