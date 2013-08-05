package com.google.code.imazon.model.order;

import java.util.List;

import com.google.code.imazon.model.order.util.OrderState;

import es.udc.pojo.modelutil.dao.GenericDao;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;

public interface OrderDao extends GenericDao<Order, Long> {
	
	public List<Order> findOrdersByUserIdAndOrderState(Long userId,
			OrderState state, int start, int count) 
					throws InstanceNotFoundException;

	public Long getNumberOfOrdersByUserIdAndOrderState(Long userId,
			OrderState state);
	
	public Order findShoppingCart(Long userId)
			throws InstanceNotFoundException;
}
