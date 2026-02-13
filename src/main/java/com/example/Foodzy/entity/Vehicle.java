package com.example.Foodzy.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Vehicle {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String vehicleName;
	private int vehicleNumber;
	private String type;
	
	public Vehicle( String vehicleName, int vehicleNumber, String type) {
		super();
	
		this.vehicleName = vehicleName;
		this.vehicleNumber = vehicleNumber;
		this.type = type;
	}

	public Vehicle() {
		super();
	}

	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}

	public int getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(int vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", vehicleName=" + vehicleName + ", vehicleNumber=" + vehicleNumber + ", type="
				+ type + "]";
	}
	
	
}
