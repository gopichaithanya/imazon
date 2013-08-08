package com.google.code.imazon.model.category;

import java.util.List;

import com.google.code.imazon.model.category.util.CategoryState;

import es.udc.pojo.modelutil.dao.GenericDao;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;

public interface CategoryDao extends GenericDao<Category, Long> {

	public List<Category> findAllCategories(CategoryState state)
			throws InstanceNotFoundException;
	
	public Category findCategoryByName(String name)
			throws InstanceNotFoundException;
}
