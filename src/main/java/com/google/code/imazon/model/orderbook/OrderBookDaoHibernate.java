package com.google.code.imazon.model.orderbook;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.google.code.imazon.model.book.Book;
import com.google.code.imazon.model.orderbook.util.OrderBookPK;

import es.udc.pojo.modelutil.dao.GenericDaoHibernate;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;

@Repository("orderBookDao")
public class OrderBookDaoHibernate
		extends GenericDaoHibernate<OrderBook, OrderBookPK>
		implements OrderBookDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> findBooksByOrderId(Long orderId, int start, int count)
			throws InstanceNotFoundException {
		List<Book> books = (List<Book>) getSession().createQuery(
		"SELECT ob.book " + 
		"FROM OrderBook ob " +
		"WHERE ob.order.orderId = :orderId " +
		"ORDER BY ob.book.title")
		.setParameter("orderId", orderId).setFirstResult(start)
		.setMaxResults(count).list();
		if (books.isEmpty()) {
			throw new InstanceNotFoundException(orderId,
					OrderBook.class.getName());
		}
		return books;
	}

	@Override
	public Long getNumberOfBooksByOrderId(Long orderId) {
		Long n = ((Long) getSession().createQuery(
				"SELECT COUNT(ob) " +
				" FROM OrderBook ob " +
				" WHERE b.order.orderId = :orderId")
				.setParameter("orderId", orderId).uniqueResult());
		return n;
	}
	
}
