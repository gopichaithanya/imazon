package com.google.code.imazon.model.order.util;

public enum OrderState {
	SHOPPING_CART	('S'),
	OUTSTANDING		('O'),
	TBC				('T'), // To be confirmed.
	CONFIRMED		('C'),
	REJECTED		('R'),
	UNDEFINED		('U');
	
	private char state;
	
	OrderState(char state) {
		this.state = state;
	}
	
	public char toChar() {
		return state;
	}
	
	public static OrderState valueOf(char state) {
		switch (state) {
			case ('S'):
				return SHOPPING_CART;
			case ('O'):
				return OUTSTANDING;
			case ('T'):
				return TBC;
			case ('C'):
				return CONFIRMED;
			case ('R'):
				return REJECTED;
			default:
				return UNDEFINED;
		}
	}
}
