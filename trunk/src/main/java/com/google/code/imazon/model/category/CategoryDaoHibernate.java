package com.google.code.imazon.model.category;

import java.util.List;

import es.udc.pojo.modelutil.dao.*;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;
import org.springframework.stereotype.Repository;

@Repository("categoryDao")
public class CategoryDaoHibernate extends GenericDaoHibernate<Category, Short>
		implements CategoryDao {

	@Override
	@SuppressWarnings("unchecked")
	public List<Category> findAllCategories() throws InstanceNotFoundException{
		List<Category> categories = (List<Category>)getSession().createQuery(
				"SELECT c FROM Category c").list();
		if (categories.isEmpty())
			throw new InstanceNotFoundException(null, Category.class.getName());
		else return categories;
	}
	
	@Override
	public Category findCategoryByName(String name) throws InstanceNotFoundException {
		Category category = (Category) getSession().createQuery(
		"SELECT c FROM Category c WHERE c.name = :name")
		.setParameter("name", name)
		.uniqueResult();
		
		if (category == null)
			throw new InstanceNotFoundException(null, Category.class.getName());
		else
			return category;
	}
}
