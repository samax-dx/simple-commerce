package com.samax.simpleCommerce.session.util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;


public class HttpSessionUtil {

    private static final String SC_SESSION_HEADER_KEY = "sessionId";


    public String getSessionId(HttpServletRequest request) {
        return request.getHeader(SC_SESSION_HEADER_KEY);
    }

    public String getUserId(Authentication authentication) {
        return authentication instanceof AnonymousAuthenticationToken ? null : authentication.getName();
    }

}
