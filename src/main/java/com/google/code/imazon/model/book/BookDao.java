package com.google.code.imazon.model.book;

import java.util.List;

import es.udc.pojo.modelutil.dao.*;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;

public interface BookDao extends GenericDao<Book, Long> {

	public List<Book> findBooksByCategoryAndKeys(Short categoryId, String keys,
			int start, int count) throws InstanceNotFoundException;
	
	public int getNumberOfBooksByCategoryAndKeys(Short categoryId, String keys);

	public List<Book> findBooks(int start, int count)
			throws InstanceNotFoundException;
	
	public int getNumberOfBooks();

	public List<Book> findBooksByPublisherId(Long publisherId, int start, int count)
			throws InstanceNotFoundException;

	public int getNumberOfBooksByPublisherId(Long publisherId);

}
