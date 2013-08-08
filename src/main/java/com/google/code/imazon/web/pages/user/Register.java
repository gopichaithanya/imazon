package com.google.code.imazon.web.pages.user;

import java.util.Calendar;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.PasswordField;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.google.code.imazon.model.user.User;
import com.google.code.imazon.model.userservice.UserDetails;
import com.google.code.imazon.model.userservice.UserService;
import com.google.code.imazon.web.pages.Index;
import com.google.code.imazon.web.services.AuthenticationPolicy;
import com.google.code.imazon.web.services.AuthenticationPolicyType;
import com.google.code.imazon.web.util.UserSession;
import es.udc.pojo.modelutil.exceptions.DuplicateInstanceException;

@AuthenticationPolicy(AuthenticationPolicyType.NON_AUTHENTICATED_USERS)
public class Register {

    @Property
    private String login;

    @Property
    private String password;

    @Property
    private String retypePassword;

    @Property
    private String name;

    @Property
    private String surname;

    @Property
    private String email;

    @SessionState(create=false)
    private UserSession userSession;

    @Inject
    private UserService userService;

    @Component
    private Form registrationForm;

    @Component(id = "login")
    private TextField loginField;

    @Component(id = "password")
    private PasswordField passwordField;

    @Inject
    private Messages messages;

    private Long userId;

    void onValidateFromRegistrationForm() {

        if (!registrationForm.isValid()) {
            return;
        }

        if (!password.equals(retypePassword)) {
            registrationForm.recordError(passwordField, messages
                    .get("error-passwordsDontMatch"));
        } else {

            try {
            	// TODO Update UserDetails to match all.
                User userProfile = userService.registerUser(login, password,
                    new UserDetails(name, surname, email,
                    		Calendar.getInstance(), "", "", ""));
                userId = userProfile.getUserId();
            } catch (DuplicateInstanceException e) {
                registrationForm.recordError(loginField, messages
                        .get("error-loginNameAlreadyExists"));
            }

        }

    }

    Object onSuccess() {

        userSession = new UserSession();
        userSession.setUserProfileId(userId);
        userSession.setFirstName(name);
        return Index.class;

    }

}
