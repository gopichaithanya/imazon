package com.google.code.imazon.model.book;

import java.util.List;

import es.udc.pojo.modelutil.dao.GenericDaoHibernate;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository("bookDao")
public class BookDaoHibernate extends GenericDaoHibernate<Book, Long>
		implements BookDao {

	private String getHQLWhereCondition(Short categoryId, String keys) {
		String categoryHQL = "";
		String keysHQL = "";
		if (categoryId != null) {
			categoryHQL += "b.category.categoryId = :categoryId";
		}
		if (keys != null) {
			keys = keys.toLowerCase();
			String[] aux = keys.split(" ");
			for (int i = 0; i < aux.length; i++) {
				keysHQL += "LOWER(b.title) LIKE :key" + i + " AND ";
			}
			// Removes the last 'AND '.
			keysHQL = keysHQL.substring(0, keysHQL.length() - 4) + ")";
		}
		if (keys != null)
			return categoryHQL + " AND (" + keysHQL + ")";
		else
			return categoryHQL;
	}

	@SuppressWarnings("unchecked")
	public List<Book> findBooksByCategoryAndKeys(Short categoryId, String keys,
			int start, int count) throws InstanceNotFoundException {
		Query q = getSession().createQuery(
				"SELECT b " +
				"FROM Book b " +
				"WHERE " + getHQLWhereCondition(categoryId, keys));
		if (categoryId != null) {
			q = q.setParameter("categoryId", categoryId);
		}
		if (keys != null) {
			keys = keys.toLowerCase();
			String[] aux = keys.split(" ");
			for (int i = 0; i < aux.length; i++) {
				q = q.setParameter("key" + i, "%" + aux[i] + "%");
			}
		}
		List<Book> books = (List<Book>) 
				q.setFirstResult(start).setMaxResults(count).list();
		if (books.isEmpty()) {
			throw new InstanceNotFoundException(categoryId + " " + keys,
					Book.class.getName());
		}
		return books;
	}
	
	@Override
	public Long getNumberOfBooksByCategoryAndKeys(Short categoryId,
			String keys) {
		Query q = getSession().createQuery(
				"SELECT COUNT (b) " +
				"FROM Book b " +
				"WHERE " + getHQLWhereCondition(categoryId, keys));
		if (categoryId != null) {
			q = q.setParameter("categoryId", categoryId);
		}
		if (keys != null) {
			keys = keys.toLowerCase();
			String[] aux = keys.split(" ");
			for (int i = 0; i < aux.length; i++) {
				q = q.setParameter("key" + i, "%" + aux[i] + "%");
			}
		}
		Long n = (Long) q.uniqueResult();
		return n;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Book> findBooks(int start, int count)
			throws InstanceNotFoundException {
		List<Book> books = (List<Book>) getSession().createQuery(
				"SELECT b " +
				"FROM Book b").setFirstResult(start)
				.setMaxResults(count).list();
		if (books.isEmpty())
			throw new InstanceNotFoundException("books", Book.class.getName());
		else
			return books;
	}
	
	@Override
	public Long getNumberOfBooks() {
		Long n = ((Long) getSession().createQuery(
				"SELECT COUNT(b) " +
				"FROM Book b").uniqueResult());
		return n;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Book> findBooksByPublisherId(Long publisherId, int start,
			int count) throws InstanceNotFoundException {
		List<Book> books = (List<Book>) getSession().createQuery(
				"SELECT b FROM Book b " +
				"WHERE b.publisher.userId = :publisherId " +
				"ORDER BY b.year DESC")
				.setParameter("publisherId", publisherId).setFirstResult(start)
				.setMaxResults(count).list();
		if (books.isEmpty()) {
			throw new InstanceNotFoundException(publisherId,
					Book.class.getName());
		} else {
			return books;
		}
	}
	
	@Override
	public Long getNumberOfBooksByPublisherId(Long publisherId) {
		Long n = ((Long) getSession().createQuery(
				"SELECT COUNT(b) " +
				"FROM Book b " +
				"WHERE b.publisher.userId = :publisherId")
				.setParameter("publisherId", publisherId).uniqueResult());
		return n;
	}
}
