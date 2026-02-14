package com.example.Foodzy.Dtos;

public class CustomerRegistrationDto {
	private String name;
	private long mobileNo;
	private String mail;
	private String gender;
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public CustomerRegistrationDto(String name, long mobileNo, String mail, String gender) {
		super();
		this.name = name;
		this.mobileNo = mobileNo;
		this.mail = mail;
		this.gender = gender;
	}
	public CustomerRegistrationDto() {
		super();
	}
	@Override
	public String toString() {
		return "CustomerRegistrationDto [name=" + name + ", mobileNo=" + mobileNo + ", mail=" + mail + ", gender="
				+ gender + "]";

	}
}

