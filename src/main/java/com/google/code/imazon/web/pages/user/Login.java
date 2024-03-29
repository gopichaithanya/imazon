package com.google.code.imazon.web.pages.user;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Cookies;

import com.google.code.imazon.model.user.User;
import com.google.code.imazon.model.userservice.IncorrectPasswordException;
import com.google.code.imazon.model.userservice.UserService;
import com.google.code.imazon.web.pages.Index;
import com.google.code.imazon.web.services.AuthenticationPolicy;
import com.google.code.imazon.web.services.AuthenticationPolicyType;
import com.google.code.imazon.web.util.CookiesManager;
import com.google.code.imazon.web.util.UserSession;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;

@AuthenticationPolicy(AuthenticationPolicyType.NON_AUTHENTICATED_USERS)
public class Login {

    @Property
    private String name;

    @Property
    private String password;

    @Property
    private boolean rememberMyPassword;

    @SessionState(create=false)
    private UserSession userSession;

    @Inject
    private Cookies cookies;

    @Component
    private Form loginForm;

    @Inject
    private Messages messages;

    @Inject
    private UserService userService;

    private User user = null;


    void onValidateFromLoginForm() {

        if (!loginForm.isValid()) {
            return;
        }

        try {
            user = userService.login(name, password, false);
        } catch (InstanceNotFoundException e) {
            loginForm.recordError(messages.get("error-authenticationFailed"));
        } catch (IncorrectPasswordException e) {
            loginForm.recordError(messages.get("error-authenticationFailed"));
        }

    }

    Object onSuccess() {

    	userSession = new UserSession();
        userSession.setUserProfileId(user.getUserId());
        userSession.setFirstName(user.getName());

        if (rememberMyPassword) {
            CookiesManager.leaveCookies(cookies, name, user
                    .getPassword());
        }
        return Index.class;

    }

}
