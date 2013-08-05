package com.google.code.imazon.model.sellerorder;

import java.util.List;

import com.google.code.imazon.model.order.Order;
import com.google.code.imazon.model.order.util.OrderState;
import com.google.code.imazon.model.sellerorder.util.SellerOrderPK;

import es.udc.pojo.modelutil.dao.GenericDao;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;

public interface SellerOrderDao extends GenericDao<SellerOrder, SellerOrderPK> {
	public List<Order> findOrdersBySellerIdAndOrderState(Long sellerId,
			OrderState state, int start, int count)
					throws InstanceNotFoundException;;

	public Long getNumberOfOrdersBySellerIdAndOrderState(Long sellerId,
			OrderState state);
}
