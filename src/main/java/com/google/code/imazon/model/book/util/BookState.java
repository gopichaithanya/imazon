package com.google.code.imazon.model.book.util;

public enum BookState {
	AVAILABLE	("A"),
	REMOVED		("R"),
	UNDEFINED	("U");
	
	private String state;
	
	BookState(String state) {
		this.state = state;
	}
	
	@Override
	public String toString() {
		return state;
	}
}
