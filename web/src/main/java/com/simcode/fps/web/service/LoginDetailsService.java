package com.simcode.fps.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.simcode.fps.repository.model.User;
import com.simcode.fps.service.impl.LoginService;
import com.simcode.fps.web.dto.LoginUserDetails;

public class LoginDetailsService implements UserDetailsService {

	@Autowired
	private LoginService loginService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = loginService.findByUserName(username);
		if (user ==null) {
			throw new UsernameNotFoundException("Username not found");
		}
		return new LoginUserDetails(user);
	}

}
