package net.java.inventoryapp.model;

import java.time.LocalDateTime;

public class InventoryItem {

	private long id;
	private String item;
	private String username;
	private LocalDateTime timeStamp;
	private String action;
	
	
	public InventoryItem(long id, String item, String username, LocalDateTime timeStamp, String action) {
		super();
		this.id = id;
		this.item = item;
		this.username = username;
		this.timeStamp = timeStamp;
		this.action = action;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getItem() {
		return item;
	}


	public void setItem(String item) {
		this.item = item;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}


	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}


	public String getAction() {
		return action;
	}


	public void setAction(String action) {
		this.action = action;
	}

	

}
