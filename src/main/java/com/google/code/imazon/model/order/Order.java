package com.google.code.imazon.model.order;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.BatchSize;

import com.google.code.imazon.model.order.util.OrderState;
import com.google.code.imazon.model.user.User;

@Entity
@BatchSize(size = 10)
public class Order {
	private long orderId;
	private Calendar orderDate;
	private User user;
	private String name;
	private String surname;
	private String address;
	private OrderState state;
	private String stateMessage;
	private String voucher; // Proof of payment.
	private Long version;

	public Order() {
	}

	public Order(User user) {
		super();
		this.user = user;
		this.orderDate = Calendar.getInstance();
		this.name = user.getName();
		this.surname = user.getSurname();
		this.address = user.getAddress();
		this.state = OrderState.SHOPPING_CART;
	}

	@Id
	@SequenceGenerator(name = "OrderIdGenerator", sequenceName = "OrderSeq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "OrderIdGenerator")
	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Calendar getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Calendar orderDate) {
		this.orderDate = orderDate;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Enumerated(EnumType.STRING)
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
