package com.example.Foodzy.Dtos;
public class RestaurentRegistrationDto {
private String name;
private long mobileNo;
private String mail;
private String description;
private double packagingFee;
private String type;
private LocationCordinates locationCordinates;


public RestaurentRegistrationDto(String name, long mobileNo, String mail, String description, double packagingFee,
		String type, LocationCordinates locationCordinates) {
	super();
	this.name = name;
	this.mobileNo = mobileNo;
	this.mail = mail;
	this.description = description;
	this.packagingFee = packagingFee;
	this.type = type;
	this.locationCordinates = locationCordinates;
}


public RestaurentRegistrationDto() {
	super();
}


public double getPackagingFee() {
	return packagingFee;
}

public void setPackagingFee(double packagingFee) {
	this.packagingFee = packagingFee;
}

public String getType() {
	return type;
}

public void setType(String type) {
	this.type = type;
}

public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public long getMobileNo() {
	return mobileNo;
}
public void setMobileNo(long mobileNo) {
	this.mobileNo = mobileNo;
}
public String getMail() {
	return mail;
}
public void setMail(String mail) {
	this.mail = mail;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public LocationCordinates getLocationCordinates() {
	return locationCordinates;
}
public void setLocationCordinates(LocationCordinates locationCordinates) {
	this.locationCordinates = locationCordinates;
}

@Override
public String toString() {
	return "RestaurentRegistrationDto [name=" + name + ", mobileNo=" + mobileNo + ", mail=" + mail + ", description="
			+ description + ", packagingFee=" + packagingFee + ", type=" + type + ", locationCordinates="
			+ locationCordinates + "]";
}


}
