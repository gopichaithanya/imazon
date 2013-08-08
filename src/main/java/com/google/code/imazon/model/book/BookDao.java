package com.google.code.imazon.model.book;

import java.util.List;

import com.google.code.imazon.model.category.util.CategoryState;

import es.udc.pojo.modelutil.dao.GenericDao;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;

public interface BookDao extends GenericDao<Book, Long> {
	
	public Book findBookByIsbn(String isbn) throws InstanceNotFoundException;

	public List<Book> findBooksByCategoryAndKeys(Long categoryId,
			CategoryState state, String keys, int start, int count)
					throws InstanceNotFoundException;
	
	public Long getNumberOfBooksByCategoryAndKeys(Long categoryId,
			CategoryState state, String keys);

	public List<Book> findBooks(int start, int count)
			throws InstanceNotFoundException;
	
	public Long getNumberOfBooks();

	public List<Book> findBooksByPublisherId(Long publisherId, int start, int count)
			throws InstanceNotFoundException;

	public Long getNumberOfBooksByPublisherId(Long publisherId);

}
