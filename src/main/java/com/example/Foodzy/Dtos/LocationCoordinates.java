package com.example.Foodzy.Dtos;

public class LocationCoordinates {
	
	private Double latitude;
	private Double longitude;
	public LocationCoordinates(Double latitude, Double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public LocationCoordinates() {
		super();
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	@Override
	public String toString() {
		return "LocationCoordinates [latitude=" + latitude + ", longitude=" + longitude + "]";
	}
	
	
	
}
