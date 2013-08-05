package com.google.code.imazon.model.sellerorder;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.google.code.imazon.model.order.Order;
import com.google.code.imazon.model.order.util.OrderState;
import com.google.code.imazon.model.sellerorder.util.SellerOrderPK;
import com.google.code.imazon.model.user.User;

@Entity
@IdClass(SellerOrderPK.class)
@org.hibernate.annotations.BatchSize(size = 10)
public class SellerOrder {
	private User seller;
	private Order order;
	private Calendar orderDate;
	private OrderState state;
	private String stateMessage;
	private Long version;
	
	@Id
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public User getSeller() {
		return seller;
	}
	
	public void setSeller(User seller) {
		this.seller = seller;
	}
	
	@Id
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "orderId")
	public Order getOrder() {
		return order;
	}
	
	public void setOrder(Order order) {
		this.order = order;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public Calendar getOrderDate() {
		return orderDate;
	}
	
	public void setOrderDate(Calendar orderDate) {
		this.orderDate = orderDate;
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
	
	@Version
	public Long getVersion() {
		return version;
	}
	
	public void setVersion(Long version) {
		this.version = version;
	}
}
