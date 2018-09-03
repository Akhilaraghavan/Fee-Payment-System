package com.simcode.fps.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class UserAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	//private static final String SPRING_SECURITY_CONTEXT = "SPRING_SECURITY_CONTEXT";


	private static final Logger logger = Logger.getLogger(UserAuthenticationSuccessHandler.class);


	private RedirectStrategy redirectionStrategy = new DefaultRedirectStrategy();
	
	public UserAuthenticationSuccessHandler() {
		
	}
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		if (authentication == null) {
			logger.error("Session is null or not authenticated");
			redirectionStrategy.sendRedirect(request, response, "/login");
			return;
		}
			
		HttpSession session = request.getSession(false);
		
        if (session == null) {
            return;
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
       /* Object attribute = session.getAttribute(SPRING_SECURITY_CONTEXT);
        if (attribute == null) {
        	session.setAttribute(SPRING_SECURITY_CONTEXT, authentication);
        }*/
        
        redirectionStrategy.sendRedirect(request, response, "/home");
        
        
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}

}
