package com.example.Foodzy.Dtos;
public class RestaurentRegistrationDto {
private String name;
private long mobileNo;
private String mail;
private String description;
private Double packagingFee;
private String type;
private LocationCoordinates locationCoordinates;
public RestaurentRegistrationDto(String name, long mobileNo, String mail, String description, Double packagingFee,
		String type, LocationCoordinates locationCoordinates) {
	super();
	this.name = name;
	this.mobileNo = mobileNo;
	this.mail = mail;
	this.description = description;
	this.packagingFee = packagingFee;
	this.type = type;
	this.locationCoordinates = locationCoordinates;
}
public RestaurentRegistrationDto() {
	super();
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
public Double getPackagingFee() {
	return packagingFee;
}
public void setPackagingFee(Double packagingFee) {
	this.packagingFee = packagingFee;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public LocationCoordinates getLocationCoordinates() {
	return locationCoordinates;
}
public void setLocationCoordinates(LocationCoordinates locationCoordinates) {
	this.locationCoordinates = locationCoordinates;
}
@Override
public String toString() {
	return "RestaurentRegistrationDto [name=" + name + ", mobileNo=" + mobileNo + ", mail=" + mail + ", description="
			+ description + ", packagingFee=" + packagingFee + ", type=" + type + ", locationCoordinates="
			+ locationCoordinates + "]";
}


}
