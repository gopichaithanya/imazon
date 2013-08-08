package com.google.code.imazon.model.adminservice;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.code.imazon.model.category.Category;
import com.google.code.imazon.model.category.CategoryDao;
import com.google.code.imazon.model.category.util.CategoryState;
import com.google.code.imazon.model.user.User;
import com.google.code.imazon.model.user.UserDao;
import com.google.code.imazon.model.user.util.UserProfile;

import es.udc.pojo.modelutil.exceptions.DuplicateInstanceException;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;

@Service("adminService")
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private CategoryDao categoryDao;

    @Override
	public List<UserProfile> findAllUserProfiles() {
		return Arrays.asList(UserProfile.values());
	}
    
	@Override
	public void changeUserProfile(String login, UserProfile newProfile)
			throws InstanceNotFoundException {
		User user = userDao.findUserByLogin(login);
		user.setProfile(newProfile);
		userDao.save(user);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Category> findAllCategories()
			throws InstanceNotFoundException {
		return categoryDao.findAllCategories(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Category findCategoryByName(String name)
			throws InstanceNotFoundException {
		return categoryDao.findCategoryByName(name);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Category findCategory(Long categoryId)
			throws InstanceNotFoundException {
		return categoryDao.find(categoryId);
	}

	@Override
	public Category addCategory(CategoryDetails categoryDetails)
			throws DuplicateInstanceException {
		String name = categoryDetails.getName();
		try {
			categoryDao.findCategoryByName(name);
			throw new DuplicateInstanceException(name, Category.class.getName());
		} catch(InstanceNotFoundException e) {
			Category category = new Category(name);
			categoryDao.save(category);
			return category;
		}
	}

	@Override
	public void updateCategoryDetails(Long categoryId,
			CategoryDetails categoryDetails) throws InstanceNotFoundException {
		Category category = categoryDao.find(categoryId);
		category.setName(categoryDetails.getName());
		categoryDao.save(category);
	}

	@Override
	public void removeCategory(Long categoryId)
			throws InstanceNotFoundException {
		Category category = categoryDao.find(categoryId);
		category.setState(CategoryState.REMOVED);
		categoryDao.save(category);
	}
}
