package com.google.code.imazon.model.sellerorder.util;

import java.io.Serializable;

import com.google.code.imazon.model.order.Order;
import com.google.code.imazon.model.user.User;

public class SellerOrderPK implements Serializable {
	private static final long serialVersionUID = -1014655770384224684L;
	private User seller;
	private Order order;
	
	public SellerOrderPK() {
	}
	
	public SellerOrderPK(User seller, Order order) {
		this.seller = seller;
		this.order = order;
	}
	
	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof SellerOrderPK)) {
            return false;
        }
        SellerOrderPK other = (SellerOrderPK) obj;
        if (this.getSeller().getUserId() == other.getSeller().getUserId()
        		&& this.getOrder().getOrderId() == other.getOrder().getOrderId()) {
        	return true;
        }
        return false;
	}
	
	@Override
	public int hashCode() {
		int hash = 11;
		hash = 13 * hash + (this.seller != null ? this.seller.hashCode() : 0);
        hash = 13 * hash + (this.order != null ? this.order.hashCode() : 0);
        return hash;
	}
}
