package com.example.Foodzy.Dtos;

import java.util.List;

import com.example.Foodzy.entity.Item;

public class RestaurentInfo {
	private String name;
	private long mobileno;
	private String mail;
	private List<Item> menu;
	private int rating;
	private String description;
	private String type;
	public RestaurentInfo(String name, long mobileno, String mail, List<Item> menu, int rating, String description,
			String type) {
		super();
		this.name = name;
		this.mobileno = mobileno;
		this.mail = mail;
		this.menu = menu;
		this.rating = rating;
		this.description = description;
		this.type = type;
	}
	public RestaurentInfo() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getMobileno() {
		return mobileno;
	}
	public void setMobileno(long mobileno) {
		this.mobileno = mobileno;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public List<Item> getMenu() {
		return menu;
	}
	public void setMenu(List<Item> menu) {
		this.menu = menu;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "RestaurentInfo [name=" + name + ", mobileno=" + mobileno + ", mail=" + mail + ", menu=" + menu
				+ ", rating=" + rating + ", description=" + description + ", type=" + type + "]";
	}
	
	
}
