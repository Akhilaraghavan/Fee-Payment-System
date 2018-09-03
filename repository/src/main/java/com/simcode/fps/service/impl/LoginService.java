package com.simcode.fps.service.impl;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simcode.fps.repository.LoginRepository;
import com.simcode.fps.repository.model.User;
import com.simcode.fps.service.ILoginService;

@Service
public class LoginService implements ILoginService {

	@Autowired
	private LoginRepository loginRepository;
	
	@Override
	public void save(User user) {
		loginRepository.save(user);
	}

	@Override
	public User findByUserName(String username) {
		Optional<User> optional = loginRepository.findByUsername(username);
		if (optional.isPresent()) {
			User user = optional.get();
			user.getRoles().stream().collect(Collectors.toList());
			return user;
		}
		return null;
	}

	@Override
	public boolean checkIfEmailExists(String email) {
		return loginRepository.findByEmail(email) != null;
	}

	public User registerUser(User user) {
		return loginRepository.save(user);
	}

}
