package com.google.code.imazon.model.book;

import java.util.List;

import es.udc.pojo.modelutil.dao.GenericDaoHibernate;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.google.code.imazon.model.category.util.CategoryState;

@Repository("bookDao")
public class BookDaoHibernate extends GenericDaoHibernate<Book, Long>
		implements BookDao {
	
	public Book findBookByIsbn(String isbn) throws InstanceNotFoundException {
		Book book = ((Book) getSession().createQuery(
				"SELECT b " +
				"FROM Book b " +
				"WHERE b.isbn = :isbn").uniqueResult());
		return book;
	}

	private String getHQLWhereCondition(Long categoryId, CategoryState state,
			String keys) {
		String categoryHQL = "";
		String keysHQL = "";
		if (categoryId != null) {
			categoryHQL += "b.category.categoryId = :categoryId";
		}
		if (state != null) {
			categoryHQL += " AND c.state = :state";
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
	public List<Book> findBooksByCategoryAndKeys(Long categoryId,
			CategoryState state, String keys, int start, int count)
					throws InstanceNotFoundException {
		String sql = "SELECT b " +
				"FROM Book b " +
				"WHERE " + getHQLWhereCondition(categoryId, state, keys);
		Query query = getSession().createQuery(sql);
		if (categoryId != null) {
			query = query.setParameter("categoryId", categoryId);
		}
		if (state != null) {
			query = query.setParameter("state", state);
		}
		if (keys != null) {
			keys = keys.toLowerCase();
			String[] aux = keys.split(" ");
			for (int i = 0; i < aux.length; i++) {
				query = query.setParameter("key" + i, "%" + aux[i] + "%");
			}
		}
		List<Book> books = (List<Book>) 
				query.setFirstResult(start).setMaxResults(count).list();
		if (books.isEmpty()) {
			throw new InstanceNotFoundException(categoryId + " " + keys,
					Book.class.getName());
		}
		return books;
	}
	
	@Override
	public Long getNumberOfBooksByCategoryAndKeys(Long categoryId,
			CategoryState state, String keys) {
		String sql = "SELECT COUNT (b) " +
				"FROM Book b " +
				"WHERE " + getHQLWhereCondition(categoryId, state, keys); 
		Query query = getSession().createQuery(sql);
		if (categoryId != null) {
			query = query.setParameter("categoryId", categoryId);
		}
		if (state != null) {
			query = query.setParameter("state", state);
		}
		if (keys != null) {
			keys = keys.toLowerCase();
			String[] aux = keys.split(" ");
			for (int i = 0; i < aux.length; i++) {
				query = query.setParameter("key" + i, "%" + aux[i] + "%");
			}
		}
		Long n = (Long) query.uniqueResult();
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
