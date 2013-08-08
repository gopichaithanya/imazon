package com.google.code.imazon.model.buyerservice;

import java.util.List;

import com.google.code.imazon.model.book.Book;
import com.google.code.imazon.model.category.Category;
import com.google.code.imazon.model.order.Order;
import com.google.code.imazon.model.order.util.OrderState;

import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;

public interface BuyerService {
	
	public List<Book> findBooks(Long categoryId, String keys, int start,
			int count) throws InstanceNotFoundException;
	
	public Long getNumberOfBooks(Long categoryId, String keys)
			throws InstanceNotFoundException;
	
	public Book findBook(Long bookId) throws InstanceNotFoundException;
	
	public List<Category> findAllCategories() throws InstanceNotFoundException;
	
	public Category findCategoryByName(String name)
			throws InstanceNotFoundException;
	
	public Category findCategory(Long categoryId)
			throws InstanceNotFoundException;

	public Order findShoppingCart(Long userId)
			throws InstanceNotFoundException;
	
	public void removeShoppingCart(Long orderId)
			throws InstanceNotFoundException;

	public void addBookToShoppingCart(Long userId, Long orderId, Long bookId,
			Integer quantity) throws InstanceNotFoundException;
	
	public void removeBookFromShoppingCart(Long orderId, Long bookId,
			Integer quantity) throws InstanceNotFoundException;
	
	public List<Order> findOrdersByUserIdAndOrderState(Long userId,
			OrderState state, int start, int count)
					throws InstanceNotFoundException;
	
	public Long getNumberOfOrdersByUserIdAndOrderState(Long userId,
			OrderState state) throws InstanceNotFoundException;
	
	public Order findOrder(Long orderId) throws InstanceNotFoundException;
	
	public void placeOrder(Long orderId) throws InstanceNotFoundException;
	
	public void payOrder(Long orderId, String voucher)
			throws InstanceNotFoundException;
}
