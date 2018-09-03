package com.simcode.fps.web;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import com.simcode.fps.web.dto.LoginUserDetails;

@Component
public class LoginAccessDecisionVoter implements AccessDecisionVoter<FilterInvocation> {

	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

	@Override
	public int vote(Authentication authentication, FilterInvocation filterInvocation, Collection<ConfigAttribute> attributes) {

		HttpServletRequest request = filterInvocation.getRequest();
		
		HttpSession session = request.getSession(false);
		System.out.println(request.hashCode());
		if (session == null && authentication instanceof AnonymousAuthenticationToken && "anonymousUser".equals(authentication.getName())
				&& filterInvocation.getRequestUrl().contains("/login")) {
			return ACCESS_GRANTED;
		} 
		
		if(session !=null) {
		Object attribute = session.getAttribute("SPRING_SECURITY_CONTEXT");
		if (attribute instanceof UsernamePasswordAuthenticationToken) {
			UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) attribute;
			LoginUserDetails loginUserDetails = (LoginUserDetails) token.getPrincipal();
			for (GrantedAuthority authority : loginUserDetails.getAuthorities()) {
				if ("ROLE_USER".equals(authority.getAuthority()) || "ROLE_ADMIN".equals(authority.getAuthority())) {
					return ACCESS_GRANTED;
				}
			}
		}
		}
		return ACCESS_DENIED;
	}

}
