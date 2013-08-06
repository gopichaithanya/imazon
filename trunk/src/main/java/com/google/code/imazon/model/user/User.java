package com.google.code.imazon.model.user;

import java.util.Calendar;

import javax.persistence.*;

import org.hibernate.annotations.BatchSize;

@Entity
@BatchSize(size = 10)
public class User {
	private Long userId;
	private String name;
	private String surname;
	private String login;
	private String password;
	private String email;
	private Calendar birthDate;
	private String phone;
	private String mobile;
	private String address;
	private Long version;

	public User() {
	}

	public User(String name, String surname, String login, String password,
			String email, Calendar birthDate, String phone, String mobile,
			String address) {
		super();
		this.name = name;
		this.surname = surname;
		this.login = login;
		this.password = password;
		this.email = email;
		this.birthDate = birthDate;
		this.phone = phone;
		this.mobile = mobile;
		this.address = address;
	}
	
	@Id
	@SequenceGenerator(name = "UserIdGenerator", sequenceName = "UserSeq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "UserIdGenerator")
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Temporal(TemporalType.DATE)
	public Calendar getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Calendar birthDate) {
		this.birthDate = birthDate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Version
	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
}
