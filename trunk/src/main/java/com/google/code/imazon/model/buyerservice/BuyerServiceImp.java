package com.google.code.imazon.model.buyerservice;

import java.util.List;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.code.imazon.model.book.Book;
import com.google.code.imazon.model.book.BookDao;
import com.google.code.imazon.model.category.Category;
import com.google.code.imazon.model.category.CategoryDao;
import com.google.code.imazon.model.category.util.CategoryState;
import com.google.code.imazon.model.order.Order;
import com.google.code.imazon.model.order.OrderDao;
import com.google.code.imazon.model.order.util.OrderState;
import com.google.code.imazon.model.orderbook.OrderBook;
import com.google.code.imazon.model.orderbook.OrderBookDao;
import com.google.code.imazon.model.orderbook.util.OrderBookPK;
import com.google.code.imazon.model.user.User;
import com.google.code.imazon.model.user.UserDao;

import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;

@Service("buyerService")
@Transactional
public class BuyerServiceImp implements BuyerService {

	@Autowired
	private BookDao bookDao;
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private OrderBookDao orderBookDao;

	@Override
	@Transactional(readOnly = true)
	public List<Book> findBooks(Long categoryId, String key, int start,
			int count) throws InstanceNotFoundException {
		List<Book> books = bookDao.findBooksByCategoryAndKeys(categoryId,
				CategoryState.AVAILABLE, key, start, count);
		if (books.isEmpty()) {
			String msg = "books:";
			if (categoryId != null)
				msg += " category: " + categoryId;
			if (key != null)
				msg += " key: " + key;
			throw new InstanceNotFoundException(msg, Book.class.getName());
		}
		return books;
	}

	@Override
	@Transactional(readOnly = true)
	public Long getNumberOfBooks(Long categoryId, String key)
			throws InstanceNotFoundException {
		return bookDao.getNumberOfBooksByCategoryAndKeys(categoryId,
				CategoryState.AVAILABLE, key);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Book findBook(Long bookId) throws InstanceNotFoundException {
		return bookDao.find(bookId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Category> findAllCategories()
			throws InstanceNotFoundException {
		return categoryDao.findAllCategories(CategoryState.AVAILABLE);
	}

	@Override
	@Transactional(readOnly = true)
	public Category findCategoryByName(String name)
			throws InstanceNotFoundException {
		return categoryDao.findCategoryByName(name);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Category findCategory(Long categoryId)
			throws InstanceNotFoundException {
		return categoryDao.find(categoryId);
	}

	@Override
	public Order findShoppingCart(Long userId)
			throws InstanceNotFoundException {
		Order shoppingCart;
		try {
			shoppingCart = orderDao.findShoppingCart(userId);
		} catch(InstanceNotFoundException e) {
			User user = userDao.find(userId);
			shoppingCart = new Order(user);
			orderDao.save(shoppingCart);
		}
		return shoppingCart;
	}

	@Override
	@Cascade(CascadeType.REMOVE)
	public void removeShoppingCart(Long orderId)
			throws InstanceNotFoundException {
		orderDao.remove(orderId);
	}

	@Override
	public void addBookToShoppingCart(Long userId, Long orderId, Long bookId,
			Integer quantity) throws InstanceNotFoundException {
		Order shoppingCart;
		try {
			shoppingCart = orderDao.find(orderId);
		} catch(InstanceNotFoundException e) {
			User user = userDao.find(userId);
			shoppingCart = new Order(user);
			orderDao.save(shoppingCart);
			shoppingCart = orderDao.findShoppingCart(userId);
			orderId = shoppingCart.getOrderId();
		}
		Book book = bookDao.find(bookId);
		OrderBook orderBook;
		try {
			OrderBookPK orderBookPK = new OrderBookPK(shoppingCart, book);
			orderBook = orderBookDao.find(orderBookPK);
			orderBook.setQuantity(orderBook.getQuantity() + quantity);
		} catch(InstanceNotFoundException e) {
			orderBook = new OrderBook(shoppingCart, book, quantity);
		}
		orderBookDao.save(orderBook);
	}

	@Override
	public void removeBookFromShoppingCart(Long orderId, Long bookId,
			Integer quantity) throws InstanceNotFoundException {
		Order shoppingCart = orderDao.find(orderId);
		Book book = bookDao.find(bookId);
		OrderBookPK orderBookPK = new OrderBookPK(shoppingCart, book);
		orderBookDao.remove(orderBookPK);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Order> findOrdersByUserIdAndOrderState(Long userId,
			OrderState state, int start, int count)
					throws InstanceNotFoundException {
		return orderDao.findOrdersByUserIdAndOrderState(userId,
				state, start, count);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Long getNumberOfOrdersByUserIdAndOrderState(Long userId,
			OrderState state) throws InstanceNotFoundException {
		return orderDao.getNumberOfOrdersByUserIdAndOrderState(userId, state);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Order findOrder(Long orderId) throws InstanceNotFoundException {
		return orderDao.find(orderId);
	}

	@Override
	public void placeOrder(Long orderId) throws InstanceNotFoundException {
		Order order = orderDao.find(orderId);
		order.setState(OrderState.OUTSTANDING);
		orderDao.save(order);
	}

	@Override
	public void payOrder(Long orderId, String voucher)
			throws InstanceNotFoundException {
		Order order = orderDao.find(orderId);
		order.setVoucher(voucher);
		order.setState(OrderState.TBC);
		orderDao.save(order);
	}
}
