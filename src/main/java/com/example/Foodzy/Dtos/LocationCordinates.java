package com.example.Foodzy.Dtos;

public class LocationCordinates {
	private double lattitude;
	private double longitude;
	public double getLattitude() {
		return lattitude;
	}
	public void setLattitude(double lattitude) {
		this.lattitude = lattitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public LocationCordinates(double lattitude, double longitude) {
		super();
		this.lattitude = lattitude;
		this.longitude = longitude;
	}
	@Override
	public String toString() {
		return "LocationCordinates [lattitude=" + lattitude + ", longitude=" + longitude + "]";
	}
	public LocationCordinates() {
		super();
	}
	

}
