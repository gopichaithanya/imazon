package com.google.code.imazon.model.userservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.code.imazon.model.user.User;
import com.google.code.imazon.model.user.UserDao;
import com.google.code.imazon.model.userservice.util.PasswordEncrypter;
import es.udc.pojo.modelutil.exceptions.DuplicateInstanceException;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User registerUser(String login, String password,
            UserDetails userDetails)
            throws DuplicateInstanceException {
        try {
            userDao.findUserByLogin(login);
            throw new DuplicateInstanceException(login,
                    User.class.getName());
        } catch (InstanceNotFoundException e) {
            String encryptedPassword = PasswordEncrypter.crypt(password);
            User user = new User(login, encryptedPassword,
            		userDetails.getName(), userDetails.getSurname(),
            		userDetails.getEmail(), userDetails.getBirthDate(),
            		userDetails.getPhone(), userDetails.getMobile(),
            		userDetails.getAddress());
            userDao.save(user);
            return user;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public User login(String login, String password,
            boolean isPasswordEncrypted) throws InstanceNotFoundException,
            IncorrectPasswordException {
        User user = userDao.findUserByLogin(login);
        String storedPassword = user.getPassword();
        if (isPasswordEncrypted) {
            if (!password.equals(storedPassword)) {
                throw new IncorrectPasswordException(login);
            }
        } else {
            if (!PasswordEncrypter.isClearPasswordCorrect(password,
                    storedPassword)) {
                throw new IncorrectPasswordException(login);
            }
        }
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public User findUser(Long userId)
            throws InstanceNotFoundException {
        return userDao.find(userId);
    }

    @Override
    public void updateUserDetails(Long userId,
            UserDetails userDetails)
            throws InstanceNotFoundException {
        User user = userDao.find(userId);
        user.setName(userDetails.getName());
        user.setSurname(userDetails.getSurname());
        user.setEmail(userDetails.getEmail());
        user.setBirthDate(userDetails.getBirthDate());
        user.setPhone(userDetails.getPhone());
        user.setMobile(userDetails.getMobile());
        user.setAddress(userDetails.getAddress());
    }

    @Override
    public void changePassword(Long userId, String oldPassword,
            String newPassword) throws IncorrectPasswordException,
            InstanceNotFoundException {
        User user;
        user = userDao.find(userId);
        String storedPassword = user.getPassword();
        if (!PasswordEncrypter.isClearPasswordCorrect(oldPassword,
                storedPassword)) {
            throw new IncorrectPasswordException(user.getLogin());
        }
        user.setPassword(PasswordEncrypter.crypt(newPassword));
    }
}
