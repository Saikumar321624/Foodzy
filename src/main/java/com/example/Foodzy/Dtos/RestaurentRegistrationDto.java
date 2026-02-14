package com.example.Foodzy.Dtos;

public class RestaurentRegistrationDto {
private String name;

private long mobileNo;
private String mail;
private String description;
private double packagingFee;
private String  type;
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
public double getPackagingFee() {
	return packagingFee;
}
public void setPackagingFee(double packagingFee) {
	this.packagingFee = packagingFee;
}
@Override
public String toString() {
	return "RestaurentRegistrationDto [name=" + name + ", mobileNo=" + mobileNo + ", mail=" + mail + ", description="
			+ description + ", packagingFee=" + packagingFee + ", type=" + type + "]";
}
public RestaurentRegistrationDto() {
	super();
}
public RestaurentRegistrationDto(String name, long mobileNo, String mail, String description, double packagingFee,
		String type) {
	super();
	this.name = name;
	this.mobileNo = mobileNo;
	this.mail = mail;
	this.description = description;
	this.packagingFee = packagingFee;
	this.type = type;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
}
