package com.google.code.imazon.model.sellerorder;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.google.code.imazon.model.order.Order;
import com.google.code.imazon.model.order.util.OrderState;
import com.google.code.imazon.model.sellerorder.util.SellerOrderPK;

import es.udc.pojo.modelutil.dao.GenericDaoHibernate;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;

@Repository("sellerOrderDao")
public class SellerOrderDaoHibernate
		extends GenericDaoHibernate<SellerOrder, SellerOrderPK>
		implements SellerOrderDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> findOrdersBySellerIdAndOrderState(Long sellerId,
			OrderState state, int start, int count)
					throws InstanceNotFoundException {
		String where = "WHERE so.seller.userId = :sellerId ";
		if (state != null) {
			where += "AND state = :state ";
		}
		String sql = "SELECT so.order " + 
				"FROM SellerOrder so " +
				where +
				"ORDER BY so.date";
		Query query = getSession().createQuery(sql)
				.setParameter("sellerId", sellerId);
		if (state != null) {
			query = query.setParameter("state", state);
		}
		List<Order> orders = (List<Order>) query.setFirstResult(start)
				.setMaxResults(count).list();
		if (orders.isEmpty()) {
			throw new InstanceNotFoundException(sellerId + " " + state,
					SellerOrder.class.getName());
		}
		return orders;
	}

	@Override
	public Long getNumberOfOrdersBySellerIdAndOrderState(Long sellerId,
			OrderState state) {
		String where = " WHERE so.seller.userId = :sellerId ";
		if (state != null) {
			where += "AND state = :state ";
		}
		String sql = "SELECT COUNT(so) " +
				"FROM SellerOrder so " +
				where;
		Query query = getSession().createQuery(sql)
				.setParameter("sellerId", sellerId);
		if (state != null) {
			query = query.setParameter("state", state);
		}
		Long n = (Long) query.uniqueResult(); 
		return n;
	}
}
