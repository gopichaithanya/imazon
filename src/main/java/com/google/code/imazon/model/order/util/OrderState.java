package com.google.code.imazon.model.order.util;

public enum OrderState {
	SHOPPING_CART	("S"),
	OUTSTANDING		("O"), // Pending of payment.
	TBC				("T"), // To be confirmed.
	CONFIRMED		("C"),
	REJECTED		("R"),
	UNDEFINED		("U");
	
	private String state;
	
	OrderState(String state) {
		this.state = state;
	}
	
	@Override
	public String toString() {
		return state;
	}
}
