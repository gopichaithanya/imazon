package com.google.code.imazon.model.userservice;

import com.google.code.imazon.model.user.User;
import es.udc.pojo.modelutil.exceptions.DuplicateInstanceException;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;

public interface UserService {

    public User registerUser(String login, String password,
            UserDetails userDetails) throws DuplicateInstanceException;
    
    public User login(String login, String password,
            boolean isPasswordEncrypted) throws InstanceNotFoundException,
            IncorrectPasswordException;

    public User findUser(Long userId) throws InstanceNotFoundException;

    public void updateUserDetails(Long userId, UserDetails userDetails)
            throws InstanceNotFoundException;

    public void changePassword(Long userId, String oldPassword,
            String newPassword) throws IncorrectPasswordException,
            InstanceNotFoundException;
    
    public void unregisterUser(Long userId, String password)
    		throws InstanceNotFoundException, IncorrectPasswordException;
}
