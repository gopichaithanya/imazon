package com.google.code.imazon.model.order;

import java.util.List;

import es.udc.pojo.modelutil.dao.*;
import org.springframework.stereotype.Repository;

@Repository("orderDao")
public class OrderDaoHibernate extends GenericDaoHibernate<Order, Long> implements OrderDao {

	@SuppressWarnings("unchecked")
	public List<Order> findOrdersByUserId(Long userId, int start, int count) {
		return getSession().createQuery(
				"SELECT o " +
				"FROM Order o " +
				"WHERE o.user.userId = :userId " +
				"ORDER BY b.date DESC")
				.setParameter("userId", userId).setFirstResult(start)
				.setMaxResults(count).list();
	}

	@Override
	public int getNumberOfOrdersByUserId(Long userId) {
		long n = (Long) getSession().createQuery(
				"SELECT COUNT(o) " +
				"FROM Order o " +
				"WHERE o.user.userId = :userId " +
				"ORDER BY b.date DESC")
				.setParameter("userId", userId).uniqueResult();
		return (int) n;
	}
}
