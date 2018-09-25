package com.simcode.fps.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.logout.LogoutHandler;

public class UserLogoutHandler implements LogoutHandler {

	private static final Logger LOG = Logger.getLogger(UserLogoutHandler.class);
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy() ;
	
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		SecurityContextHolder.clearContext();
		try {
			redirectStrategy.sendRedirect(request, response, "/login");
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
	}

}
