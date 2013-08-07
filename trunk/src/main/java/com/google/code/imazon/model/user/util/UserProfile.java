package com.google.code.imazon.model.user.util;

public enum UserProfile {
	BUYER		("B"),
	PUBLISHER	("P"),
	SELLER		("S"),
	ADMIN		("A"),
	REMOVED		("R"),
	UNDEFINED	("U");
	
	private String profile;
	
	UserProfile(String profile) {
		this.profile = profile;
	}
	
	@Override
	public String toString() {
		return profile;
	}
}
