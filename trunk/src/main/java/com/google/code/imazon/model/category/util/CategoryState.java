package com.google.code.imazon.model.category.util;

public enum CategoryState {
	AVAILABLE	("A"),
	REMOVED		("R"),
	UNDEFINED	("U");
	
	private String state;
	
	CategoryState(String state) {
		this.state = state;
	}
	
	@Override
	public String toString() {
		return state;
	}
}
