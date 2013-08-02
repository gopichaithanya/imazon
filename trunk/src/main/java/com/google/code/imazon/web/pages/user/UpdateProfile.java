package com.google.code.imazon.web.pages.user;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.google.code.imazon.model.userprofile.User;
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
    private String firstName;

    @Property
    private String lastName;

    @Property
    private String email;

    @SessionState(create=false)
    private UserSession userSession;

    @Inject
    private UserService userService;

    void onPrepareForRender() throws InstanceNotFoundException {

        User userProfile;

        userProfile = userService.findUserProfile(userSession
                .getUserProfileId());
        firstName = userProfile.getFirstName();
        lastName = userProfile.getLastName();
        email = userProfile.getEmail();

    }

    Object onSuccess() throws InstanceNotFoundException {

        userService.updateUserProfileDetails(
                userSession.getUserProfileId(), new UserDetails(
                        firstName, lastName, email));
        userSession.setFirstName(firstName);
        return Index.class;

    }

}