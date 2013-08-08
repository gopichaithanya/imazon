package com.google.code.imazon.model.category;

import java.util.List;

import es.udc.pojo.modelutil.dao.GenericDaoHibernate;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.google.code.imazon.model.category.util.CategoryState;

@Repository("categoryDao")
public class CategoryDaoHibernate extends GenericDaoHibernate<Category, Long>
		implements CategoryDao {

	@Override
	@SuppressWarnings("unchecked")
	public List<Category> findAllCategories(CategoryState state)
			throws InstanceNotFoundException {
		String sql = "SELECT c " +
				"FROM Category c";
		if (state != null) {
			sql += " WHERE c.state = :state";
		}
		Query query = getSession().createQuery(sql);
		if (state != null) {
			query = query.setParameter("state", state);
		}
		List<Category> categories = (List<Category>) query.list();
		if (categories.isEmpty())
			throw new InstanceNotFoundException("categories",
					Category.class.getName());
		else return categories;
	}
	
	@Override
	public Category findCategoryByName(String name)
			throws InstanceNotFoundException {
		Category category = (Category) getSession().createQuery(
		"SELECT c " +
		"FROM Category c " +
		"WHERE c.name = :name").setParameter("name", name).uniqueResult();
		if (category == null)
			throw new InstanceNotFoundException(name, Category.class.getName());
		else
			return category;
	}
}
