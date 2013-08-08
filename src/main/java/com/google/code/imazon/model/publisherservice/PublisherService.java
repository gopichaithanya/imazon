package com.google.code.imazon.model.publisherservice;

import java.util.List;

import com.google.code.imazon.model.book.Book;
import com.google.code.imazon.model.category.Category;

import es.udc.pojo.modelutil.exceptions.DuplicateInstanceException;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;

public interface PublisherService {
	
	public List<Category> findAllCategories() throws InstanceNotFoundException;
	
	public Book findBook(Long bookId) throws InstanceNotFoundException;
	
	public Book addBook(Long publisherId, BookDetails bookDetails)
			throws InstanceNotFoundException, DuplicateInstanceException;
	
	public void updateBookDetails(Long publisherId, Long bookId,
			BookDetails bookDetails) throws InstanceNotFoundException;
	
	public void removeBook(Long publisherId, Long bookId)
			throws InstanceNotFoundException;
}
