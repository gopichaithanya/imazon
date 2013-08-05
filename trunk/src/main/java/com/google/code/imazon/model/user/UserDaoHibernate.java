package com.google.code.imazon.model.user;

import org.springframework.stereotype.Repository;

import es.udc.pojo.modelutil.dao.GenericDaoHibernate;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;

@Repository("userDao")
public class UserDaoHibernate extends GenericDaoHibernate<User, Long> implements UserDao {

	public User findUserByLogin(String login)
			throws InstanceNotFoundException {
    	User user = (User) getSession().createQuery(
    			"SELECT u " +
    			"FROM User u " +
    			"WHERE u.login = :login")
    			.setParameter("login", login)
    			.uniqueResult();
    	if (user == null)
   			throw new InstanceNotFoundException(login, User.class.getName());
    	else return user;
	}
}
