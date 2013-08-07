package com.google.code.imazon.model.book;

import java.util.List;

import es.udc.pojo.modelutil.dao.GenericDao;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;

public interface BookDao extends GenericDao<Book, Long> {

	public List<Book> findBooksByCategoryAndKeys(Long categoryId, String keys,
			int start, int count) throws InstanceNotFoundException;
	
	public Long getNumberOfBooksByCategoryAndKeys(Long categoryId, String keys);

	public List<Book> findBooks(int start, int count)
			throws InstanceNotFoundException;
	
	public Long getNumberOfBooks();

	public List<Book> findBooksByPublisherId(Long publisherId, int start, int count)
			throws InstanceNotFoundException;

	public Long getNumberOfBooksByPublisherId(Long publisherId);

}
