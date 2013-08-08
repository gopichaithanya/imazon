package com.google.code.imazon.model.publisherservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.code.imazon.model.book.Book;
import com.google.code.imazon.model.book.BookDao;
import com.google.code.imazon.model.book.util.BookState;
import com.google.code.imazon.model.category.Category;
import com.google.code.imazon.model.category.CategoryDao;
import com.google.code.imazon.model.category.util.CategoryState;
import com.google.code.imazon.model.user.User;
import com.google.code.imazon.model.user.UserDao;

import es.udc.pojo.modelutil.exceptions.DuplicateInstanceException;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;

@Service("publisherService")
@Transactional
public class PublisherServiceImp implements PublisherService {

	@Autowired
	private BookDao bookDao;
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private UserDao userDao;
	
	@Override
	public List<Category> findAllCategories() throws InstanceNotFoundException {
		return categoryDao.findAllCategories(CategoryState.AVAILABLE);
	}
	
	@Override
	public Book findBook(Long bookId) throws InstanceNotFoundException {
		return bookDao.find(bookId);
	}
	
	@Override
	public Book addBook(Long publisherId, BookDetails bookDetails)
			throws InstanceNotFoundException, DuplicateInstanceException {
		User publisher = userDao.find(publisherId);
		String isbn = bookDetails.getIsbn();
		try {
			bookDao.findBookByIsbn(isbn);
			throw new DuplicateInstanceException(isbn, Book.class.getName());
		} catch (InstanceNotFoundException e) {
			Book book = new Book(bookDetails.getTitle(),
					bookDetails.getAuthor(), bookDetails.getCategory(),
					publisher, bookDetails.getPublicationDate(),
					bookDetails.getPrice(), bookDetails.getLanguage(),
					bookDetails.getCity(), bookDetails.getCountry(), isbn);
			bookDao.save(book);
			return book;
		}
	}

	@Override
	public void updateBookDetails(Long publisherId, Long bookId,
			BookDetails bookDetails) throws InstanceNotFoundException {
		Book book = bookDao.find(bookId);
		book.setTitle(bookDetails.getTitle());
		book.setAuthor(bookDetails.getAuthor());
		book.setCategory(bookDetails.getCategory());
		book.setPublicationDate(bookDetails.getPublicationDate());
		book.setPrice(bookDetails.getPrice());
		book.setLanguage(bookDetails.getLanguage());
		book.setCity(bookDetails.getCity());
		book.setCountry(bookDetails.getCountry());
		book.setIsbn(bookDetails.getIsbn());
		bookDao.save(book);
	}

	@Override
	public void removeBook(Long publisherId, Long bookId)
			throws InstanceNotFoundException {
		Book book = bookDao.find(bookId);
		book.setState(BookState.REMOVED);
		bookDao.save(book);
	}
}
