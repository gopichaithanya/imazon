package com.google.code.imazon.model.order;

import java.util.List;

import es.udc.pojo.modelutil.dao.*;

public interface OrderDao extends GenericDao<Order, Long> {
	
	public List<Order> findOrdersByUserId(Long userId, int start, int count);

	public int getNumberOfOrdersByUserId(Long userId);
}
