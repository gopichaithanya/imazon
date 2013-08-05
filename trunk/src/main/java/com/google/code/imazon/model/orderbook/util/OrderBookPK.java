package com.google.code.imazon.model.orderbook.util;

import java.io.Serializable;

import com.google.code.imazon.model.book.Book;
import com.google.code.imazon.model.order.Order;

public class OrderBookPK implements Serializable {
	private static final long serialVersionUID = 8340690815645915568L;
	private Order order;
	private Book book;
	
	public OrderBookPK() {
	}
	
	public OrderBookPK(Order order, Book book) {
		this.order = order;
		this.book = book;
	}
	
	public Order getOrder() {
		return order;
	}
	
	public void setOrder(Order order) {
		this.order = order;
	}
	
	public Book getBook() {
		return book;
	}
	
	public void setBook(Book book) {
		this.book = book;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof OrderBookPK)) {
            return false;
        }
        OrderBookPK other = (OrderBookPK) obj;
        if (this.getOrder().getOrderId() == other.getOrder().getOrderId()
        		&& this.getBook().getBookId() == other.getBook().getBookId()) {
        	return true;
        }
        return false;
	}
	
	@Override
	public int hashCode() {
		int hash = 11;
        hash = 13 * hash + (this.order != null ? this.order.hashCode() : 0);
        hash = 13 * hash + (this.book != null ? this.book.hashCode() : 0);
        return hash;
	}
}
