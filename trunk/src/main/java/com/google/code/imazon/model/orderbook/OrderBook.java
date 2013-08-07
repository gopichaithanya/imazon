package com.google.code.imazon.model.orderbook;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import org.hibernate.annotations.BatchSize;

import com.google.code.imazon.model.book.Book;
import com.google.code.imazon.model.order.Order;
import com.google.code.imazon.model.orderbook.util.OrderBookPK;

@Entity
@IdClass(OrderBookPK.class)
@BatchSize(size = 10)
public class OrderBook {
	private Order order;
	private Book book;
	private Integer quantity;
	private BigDecimal price;
	private Long version;
	
	public OrderBook() {
	}
	
	public OrderBook(Order order, Book book, Integer quantity) {
		this.order = order;
		this.book = book;
		this.quantity = quantity;
		this.price = book.getPrice();
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
	
	@Id
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "bookId")
	public Book getBook() {
		return book;
	}
	
	public void setBook(Book book) {
		this.book = book;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	@Version
	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
}
