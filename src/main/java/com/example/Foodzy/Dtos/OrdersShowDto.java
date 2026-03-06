package com.example.Foodzy.Dtos;

import java.util.List;

import com.example.Foodzy.entity.Address;
import com.example.Foodzy.entity.CartItem;
import com.example.Foodzy.entity.Item;
import com.example.Foodzy.entity.Restaurant;

public class OrdersShowDto {
	private long id;
	private String restuarntName;
	private String ItemName;
	private Address pickupaddress;
	private Address dropaddress;
	public OrdersShowDto(String restuarntName, String itemName, Address pickupaddress, Address dropaddress) {
		super();
		this.restuarntName = restuarntName;
		ItemName = itemName;
		this.pickupaddress = pickupaddress;
		this.dropaddress = dropaddress;
	}
	public OrdersShowDto() {
		super();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getRestuarntName() {
		return restuarntName;
	}
	public void setRestuarntName(String restuarntName) {
		this.restuarntName = restuarntName;
	}
	public String getItemName() {
		return ItemName;
	}
	public void setItemName(String itemName) {
		ItemName = itemName;
	}
	public Address getPickupaddress() {
		return pickupaddress;
	}
	public void setPickupaddress(Address pickupaddress) {
		this.pickupaddress = pickupaddress;
	}
	public Address getDropaddress() {
		return dropaddress;
	}
	public void setDropaddress(Address dropaddress) {
		this.dropaddress = dropaddress;
	}
	@Override
	public String toString() {
		return "OrdersShowDto [id=" + id + ", restuarntName=" + restuarntName + ", ItemName=" + ItemName
				+ ", pickupaddress=" + pickupaddress + ", dropaddress=" + dropaddress + "]";
	}
	
	
}
