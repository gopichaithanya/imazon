package com.google.code.imazon.model.order;

import java.util.Calendar;

import javax.persistence.*;

import com.google.code.imazon.model.order.util.OrderState;
import com.google.code.imazon.model.user.User;

@Entity
public class Order {
	private long orderId;
	private Calendar date;
	private User user;
	private String name;
	private String surname;
	private String cardNumber;
	private String address;
	private OrderState state;
	private String stateMessage;
	private String voucher; // Proof of payment.
	private Long version;

	public Order() {
	}

	public Order(Calendar date, User user, String stateMessage) {
		super();
		this.date = date;
		this.user = user;
		this.name = user.getName();
		this.surname = user.getSurname();
		this.address = user.getAddress();
		this.state = OrderState.SHOPPING_CART;
		this.stateMessage = stateMessage;
	}

	@SequenceGenerator(name = "OrderIdGenerator", sequenceName = "OrderSeg")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "OrderIdGenerator")
	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public OrderState getState() {
		return state;
	}

	public void setState(OrderState state) {
		this.state = state;
	}

	public String getStateMessage() {
		return stateMessage;
	}

	public void setStateMessage(String stateMessage) {
		this.stateMessage = stateMessage;
	}

	public String getVoucher() {
		return voucher;
	}

	public void setVoucher(String voucher) {
		this.voucher = voucher;
	}
	
	@Version
	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
}
