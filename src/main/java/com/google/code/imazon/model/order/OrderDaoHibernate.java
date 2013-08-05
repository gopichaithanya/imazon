package com.google.code.imazon.model.order;

import java.util.List;

import es.udc.pojo.modelutil.dao.GenericDaoHibernate;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.google.code.imazon.model.order.util.OrderState;

@Repository("orderDao")
public class OrderDaoHibernate extends GenericDaoHibernate<Order, Long>
		implements OrderDao {

	@SuppressWarnings("unchecked")
	public List<Order> findOrdersByUserIdAndOrderState(Long userId,
			OrderState state, int start, int count) 
					throws InstanceNotFoundException {
		String where = "WHERE o.user.userId = :userId ";
		if (state != null) {
			where += "AND state = :state ";
		}
		String sql = "SELECT o " +
				"FROM Order o " +
				 where +
				"ORDER BY b.date DESC";
		Query query = getSession().createQuery(sql)
				.setParameter("userId", userId);
		if (state != null) {
			query.setParameter("state", state);
		}
		List<Order> orders = (List<Order>)
				query.setFirstResult(start).setMaxResults(count).list();
		if (orders.isEmpty()) {
			throw new InstanceNotFoundException(userId + " " + state,
					Order.class.getName());
		}
		return orders;
	}

	@Override
	public Long getNumberOfOrdersByUserIdAndOrderState(Long userId,
			OrderState state) {
		String where = "WHERE o.user.userId = :userId ";
		if (state != null) {
			where += "AND state = :state ";
		}
		String sql = "SELECT COUNT(o) " +
				"FROM Order o " +
				where;
		Query query = getSession().createQuery(sql)
				.setParameter("userId", userId);
		if (state != null) {
			query.setParameter("state", state);
		}
		Long n = (Long) query.uniqueResult(); 
		return n;
	}

	@Override
	public Order findShoppingCart(Long userId)
			throws InstanceNotFoundException {
		Order order;
		OrderState state = OrderState.SHOPPING_CART;
		order = (Order) getSession().createQuery(
			"SELECT o" +
			"FROM Order o" +
			"WHERE o.user.userId = :userId" +
			"AND o.state = :state")
			.setParameter("userId", userId)
			.setParameter("state", state)
			.uniqueResult();
		if (order == null) {
			throw new InstanceNotFoundException(userId + " " + state,
					Order.class.getName());
		}
		return order;
	}
}
