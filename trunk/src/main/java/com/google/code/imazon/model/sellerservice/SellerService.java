package com.google.code.imazon.model.sellerservice;

import java.util.List;

import com.google.code.imazon.model.buyerservice.OrderDetails;
import com.google.code.imazon.model.order.Order;
import com.google.code.imazon.model.order.util.OrderState;

import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;

public interface SellerService {
	
	public List<OrderState> findAllOrderStates();
	
	public List<Order> findOrdersByLoginAndOrderState(String login,
			OrderState state, int start, int count)
					throws InstanceNotFoundException;
	
	public Long getNumberOfOrdersByLoginAndOrderState(String login,
			OrderState state) throws InstanceNotFoundException;
	
	public Order findOrder(Long orderId) throws InstanceNotFoundException;
	
	public void confirmOrder(Long orderId)
			throws InstanceNotFoundException, IllegalActionException;
	
	public void rejectOrder(Long orderId, OrderDetails orderDetails)
			throws InstanceNotFoundException, IllegalActionException;
}
