package com.example.Foodzy.Dtos;

public class DeliveryRegistrationDto {
private String name;
private long mob;
private String mailId;
private String vehNo;
public DeliveryRegistrationDto(String name, long mob, String mailId, String vehNo) {
	super();
	this.name = name;
	this.mob = mob;
	this.mailId = mailId;
	this.vehNo = vehNo;
}
public DeliveryRegistrationDto() {
	super();
	// TODO Auto-generated constructor stub
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public long getMob() {
	return mob;
}
public void setMob(long mob) {
	this.mob = mob;
}
public String getMailId() {
	return mailId;
}
public void setMailId(String mailId) {
	this.mailId = mailId;
}
public String getVehNo() {
	return vehNo;
}
public void setVehNo(String vehNo) {
	this.vehNo = vehNo;
}
@Override
public String toString() {
	return "DeliveryRegistrationDto [name=" + name + ", mob=" + mob + ", mailId=" + mailId + ", vehNo=" + vehNo + "]";
}

}
