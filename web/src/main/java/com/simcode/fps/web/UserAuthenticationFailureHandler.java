package com.simcode.fps.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class UserAuthenticationFailureHandler implements AuthenticationFailureHandler {

	private static final Logger logger = Logger.getLogger(UserAuthenticationFailureHandler.class);

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		if (exception != null) {
			logger.error(exception.getMessage());
			response.sendRedirect("/login");
		}
				
	}

}
