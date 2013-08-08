package com.google.code.imazon.web.pages.user;

import java.util.Calendar;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.google.code.imazon.model.user.User;
import com.google.code.imazon.model.userservice.UserDetails;
import com.google.code.imazon.model.userservice.UserService;
import com.google.code.imazon.web.pages.Index;
import com.google.code.imazon.web.services.AuthenticationPolicy;
import com.google.code.imazon.web.services.AuthenticationPolicyType;
import com.google.code.imazon.web.util.UserSession;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;

@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
public class UpdateProfile {

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

    void onPrepareForRender() throws InstanceNotFoundException {

        User userProfile;

        userProfile = userService.findUser(userSession
                .getUserProfileId());
        name = userProfile.getName();
        surname = userProfile.getSurname();
        email = userProfile.getEmail();

    }

    Object onSuccess() throws InstanceNotFoundException {

        userService.updateUserDetails(
        		// TODO Update UserDetails to match all.
                userSession.getUserProfileId(), new UserDetails(
                        name, surname, email, Calendar.getInstance(), "", "",
                        ""));
        userSession.setFirstName(name);
        return Index.class;

    }

}