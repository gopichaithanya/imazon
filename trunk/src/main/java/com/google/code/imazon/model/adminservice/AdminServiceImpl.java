package com.google.code.imazon.model.adminservice;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.code.imazon.model.user.User;
import com.google.code.imazon.model.user.UserDao;
import com.google.code.imazon.model.user.util.UserProfile;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;

@Service("adminService")
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserDao userDao;

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
}
