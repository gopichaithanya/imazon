package com.google.code.imazon.model.adminservice;

import java.util.List;

import com.google.code.imazon.model.user.util.UserProfile;

import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;

public interface AdminService {
	
	public List<UserProfile> findAllUserProfiles();
	
	public void changeUserProfile(String login, UserProfile newProfile)
			throws InstanceNotFoundException;
}
