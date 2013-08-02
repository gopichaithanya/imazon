package com.google.code.imazon.model.category;

import java.util.List;

import es.udc.pojo.modelutil.dao.*;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;

public interface CategoryDao extends GenericDao<Category, Short>{

	public List<Category> findAllCategories() throws InstanceNotFoundException;
	
	public Category findCategoryByName(String name) throws InstanceNotFoundException;
}
