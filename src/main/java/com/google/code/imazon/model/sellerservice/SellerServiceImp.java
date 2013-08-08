package com.google.code.imazon.model.sellerservice;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.code.imazon.model.buyerservice.OrderDetails;
import com.google.code.imazon.model.order.Order;
import com.google.code.imazon.model.order.OrderDao;
import com.google.code.imazon.model.order.util.OrderState;
import com.google.code.imazon.model.user.User;
import com.google.code.imazon.model.user.UserDao;

import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;

@Service("sellerService")
@Transactional
public class SellerServiceImp implements SellerService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private OrderDao orderDao;
	
	@Override
	public List<OrderState> findAllOrderStates() {
		return Arrays.asList(OrderState.values());
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Order> findOrdersByLoginAndOrderState(String login,
			OrderState state, int start, int count)
					throws InstanceNotFoundException {
		Long userId = null;
		if (login != null) {
			User user = userDao.findUserByLogin(login);
			userId = user.getUserId();
		}
		return orderDao.findOrdersByUserIdAndOrderState(userId,
				state, start, count);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Long getNumberOfOrdersByLoginAndOrderState(String login,
			OrderState state) throws InstanceNotFoundException {
		Long userId = null;
		if (login != null) {
			User user = userDao.findUserByLogin(login);
			userId = user.getUserId();
		}
		return orderDao.getNumberOfOrdersByUserIdAndOrderState(userId, state);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Order findOrder(Long orderId) throws InstanceNotFoundException {
		return orderDao.find(orderId);
	}
	
	@Override
	public void confirmOrder(Long orderId)
			throws InstanceNotFoundException, IllegalActionException {
		Order order = orderDao.find(orderId);
		if (order.getState() != OrderState.TBC) {
			throw new IllegalActionException(orderId);
		}
		order.setState(OrderState.CONFIRMED);
		orderDao.save(order);
	}
	
	@Override
	public void rejectOrder(Long orderId, OrderDetails orderDetails)
			throws InstanceNotFoundException, IllegalActionException {
		Order order = orderDao.find(orderId);
		if (order.getState() != OrderState.TBC) {
			throw new IllegalActionException(orderId);
		}
		order.setState(OrderState.REJECTED);
		order.setStateMessage(orderDetails.getStateMessage());
		orderDao.save(order);
	}
}
