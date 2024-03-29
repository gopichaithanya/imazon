package com.google.code.imazon.model.userservice;

import java.util.Calendar;

public class UserDetails {

	private String name;
	private String surname;
	private String email;
	private Calendar birthDate;
	private String phone;
	private String mobile;
	private String address;

	public UserDetails(String name, String surname, String email,
			Calendar birthDate, String phone, String mobile, String address) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.birthDate = birthDate;
		this.phone = phone;
		this.mobile = mobile;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getEmail() {
		return email;
	}
	
	public Calendar getBirthDate() {
		return birthDate;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public String getMobile() {
		return mobile;
	}
	
	public String getAddress() {
		return address;
	}
}
