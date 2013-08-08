package com.google.code.imazon.model.adminservice;

import java.util.List;

import com.google.code.imazon.model.category.Category;
import com.google.code.imazon.model.user.util.UserProfile;

import es.udc.pojo.modelutil.exceptions.DuplicateInstanceException;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;

public interface AdminService {
	
	public List<UserProfile> findAllUserProfiles();
	
	public void changeUserProfile(String login, UserProfile newProfile)
			throws InstanceNotFoundException;
	
	public List<Category> findAllCategories() throws InstanceNotFoundException;
	
	public Category findCategoryByName(String name)
			throws InstanceNotFoundException;
	
	public Category findCategory(Long categoryId)
			throws InstanceNotFoundException;
	
	public Category addCategory(CategoryDetails categoryDetails)
			throws DuplicateInstanceException;
	
	public void updateCategoryDetails(Long categoryId, 
			CategoryDetails categoryDetails) throws InstanceNotFoundException;
	
	public void removeCategory(Long categoryId)
			throws InstanceNotFoundException;
}
