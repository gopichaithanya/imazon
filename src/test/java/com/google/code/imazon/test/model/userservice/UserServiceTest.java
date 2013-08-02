package com.google.code.imazon.test.model.userservice;

import static com.google.code.imazon.model.util.GlobalNames.SPRING_CONFIG_FILE;
import static com.google.code.imazon.test.util.GlobalNames.SPRING_CONFIG_TEST_FILE;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.google.code.imazon.model.userprofile.User;
import com.google.code.imazon.model.userservice.IncorrectPasswordException;
import com.google.code.imazon.model.userservice.UserDetails;
import com.google.code.imazon.model.userservice.UserService;
import es.udc.pojo.modelutil.exceptions.DuplicateInstanceException;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { SPRING_CONFIG_FILE, SPRING_CONFIG_TEST_FILE })
@Transactional
public class UserServiceTest {

    private final long NON_EXISTENT_USER_PROFILE_ID = -1;

    @Autowired
    private UserService userService;

    @Test
    public void testRegisterUserAndFindUserProfile()
        throws DuplicateInstanceException, InstanceNotFoundException {

        /* Register user and find profile. */
        User userProfile = userService.registerUser(
            "user", "userPassword",
            new UserDetails("name", "lastName", "user@udc.es"));

        User userProfile2 = userService.findUserProfile(
            userProfile.getUserProfileId());

        /* Check data. */
        assertEquals(userProfile, userProfile2);

    }

    @Test(expected = DuplicateInstanceException.class)
    public void testRegisterDuplicatedUser() throws DuplicateInstanceException,
        InstanceNotFoundException {

        String loginName = "user";
        String clearPassword = "userPassword";
        UserDetails userProfileDetails = new UserDetails(
            "name", "lastName", "user@udc.es");

        userService.registerUser(loginName, clearPassword,
            userProfileDetails);

        userService.registerUser(loginName, clearPassword,
            userProfileDetails);

    }

    @Test
    public void testLoginClearPassword() throws IncorrectPasswordException,
        InstanceNotFoundException {

        String clearPassword = "userPassword";
        User userProfile = registerUser("user", clearPassword);

        User userProfile2 = userService.login(userProfile.getLoginName(),
            clearPassword, false);

        assertEquals(userProfile, userProfile2);

    }


    @Test
    public void testLoginEncryptedPassword() throws IncorrectPasswordException,
            InstanceNotFoundException {

        User userProfile = registerUser("user", "clearPassword");

        User userProfile2 = userService.login(userProfile.getLoginName(),
            userProfile.getEncryptedPassword(), true);

        assertEquals(userProfile, userProfile2);

    }

    @Test(expected = IncorrectPasswordException.class)
    public void testLoginIncorrectPasword() throws IncorrectPasswordException,
            InstanceNotFoundException {

        String clearPassword = "userPassword";
        User userProfile = registerUser("user", clearPassword);

        userService.login(userProfile.getLoginName(), 'X' + clearPassword,
             false);

    }

    @Test(expected = InstanceNotFoundException.class)
    public void testLoginWithNonExistentUser()
            throws IncorrectPasswordException, InstanceNotFoundException {

        userService.login("user", "userPassword", false);

    }

    @Test(expected = InstanceNotFoundException.class)
    public void testFindNonExistentUser() throws InstanceNotFoundException {

        userService.findUserProfile(NON_EXISTENT_USER_PROFILE_ID);

    }

    @Test
    public void testUpdate() throws InstanceNotFoundException,
            IncorrectPasswordException {

        /* Update profile. */
        String clearPassword = "userPassword";
        User userProfile = registerUser("user", clearPassword);

        UserDetails newUserProfileDetails = new UserDetails(
            'X' + userProfile.getFirstName(), 'X' + userProfile.getLastName(),
            'X' + userProfile.getEmail());

        userService.updateUserProfileDetails(userProfile.getUserProfileId(),
            newUserProfileDetails);

        /* Check changes. */
        userService.login(userProfile.getLoginName(), clearPassword, false);
        User userProfile2 = userService.findUserProfile(
            userProfile.getUserProfileId());

        assertEquals(newUserProfileDetails.getFirstName(),
            userProfile2.getFirstName());
        assertEquals(newUserProfileDetails.getLastName(),
            userProfile2.getLastName());
        assertEquals(newUserProfileDetails.getEmail(),
            userProfile2.getEmail());

    }

    @Test(expected = InstanceNotFoundException.class)
    public void testUpdateWithNonExistentUser()
            throws InstanceNotFoundException {

        userService.updateUserProfileDetails(NON_EXISTENT_USER_PROFILE_ID,
            new UserDetails("name", "lastName", "user@udc.es"));

    }

    @Test
    public void testChangePassword() throws InstanceNotFoundException,
            IncorrectPasswordException {

        /* Change password. */
        String clearPassword = "userPassword";
        User userProfile = registerUser("user", clearPassword);
        String newClearPassword = 'X' + clearPassword;

        userService.changePassword(userProfile.getUserProfileId(),
            clearPassword, newClearPassword);

        /* Check new password. */
        userService.login(userProfile.getLoginName(), newClearPassword, false);

    }

    @Test(expected = IncorrectPasswordException.class)
    public void testChangePasswordWithIncorrectPassword()
            throws InstanceNotFoundException, IncorrectPasswordException {

        String clearPassword = "userPassword";
        User userProfile = registerUser("user", clearPassword);

        userService.changePassword(userProfile.getUserProfileId(),
            'X' + clearPassword, 'Y' + clearPassword);

    }

    @Test(expected = InstanceNotFoundException.class)
    public void testChangePasswordWithNonExistentUser()
            throws InstanceNotFoundException, IncorrectPasswordException {

        userService.changePassword(NON_EXISTENT_USER_PROFILE_ID,
                "userPassword", "XuserPassword");

    }

    private User registerUser(String loginName, String clearPassword) {

        UserDetails userProfileDetails = new UserDetails(
            "name", "lastName", "user@udc.es");

        try {

            return userService.registerUser(
                loginName, clearPassword, userProfileDetails);

        } catch (DuplicateInstanceException e) {
            throw new RuntimeException(e);
        }

    }

}
