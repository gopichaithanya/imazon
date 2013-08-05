package com.google.code.imazon.model.orderbook;

import java.util.List;

import com.google.code.imazon.model.book.Book;
import com.google.code.imazon.model.orderbook.util.OrderBookPK;

import es.udc.pojo.modelutil.dao.GenericDao;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;

public interface OrderBookDao extends GenericDao<OrderBook, OrderBookPK> {
	public List<Book> findBooksByOrderId(Long orderId, int start, int count)
			throws InstanceNotFoundException;

	public Long getNumberOfBooksByOrderId(Long orderId);
}
