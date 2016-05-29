package com.cecore.model;

public class Message {

	private String title;
	private String message;

	public Message() {
	}
	
	public Message(String title, String message) {
		this.title = title;
		this.message = message;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
