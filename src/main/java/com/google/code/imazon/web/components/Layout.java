package com.google.code.imazon.web.components;

import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Cookies;

import com.google.code.imazon.web.pages.Index;
import com.google.code.imazon.web.services.AuthenticationPolicy;
import com.google.code.imazon.web.services.AuthenticationPolicyType;
import com.google.code.imazon.web.util.CookiesManager;
import com.google.code.imazon.web.util.UserSession;

public class Layout {
    @Property
    @Parameter(required = false, defaultPrefix = "message")
    private String menuExplanation;

    @Property
    @Parameter(required = true, defaultPrefix = "message")
    private String pageTitle;

    @Property
    @SessionState(create=false)
    private UserSession userSession;

    @Inject
    private Cookies cookies;

    @AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)
   	Object onActionFromLogout() {
        userSession = null;
        CookiesManager.removeCookies(cookies);
        return Index.class;
	}
    
}