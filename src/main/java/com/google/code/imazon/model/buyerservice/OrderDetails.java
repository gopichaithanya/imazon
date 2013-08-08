package com.google.code.imazon.model.buyerservice;

public class OrderDetails {

	private String stateMessage;
	
	public OrderDetails(String stateMessage) {
		this.stateMessage = stateMessage;
	}

	public String getStateMessage() {
		return stateMessage;
	}
}
