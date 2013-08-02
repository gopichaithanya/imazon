package com.google.code.imazon.model.user;

import es.udc.pojo.modelutil.dao.*;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;

public interface UserDao extends GenericDao<User, Long> {

	public User findUserByLogin(String login) throws InstanceNotFoundException;

}
