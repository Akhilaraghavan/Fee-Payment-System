package com.simcode.fps.service;

import com.simcode.fps.repository.model.User;

public interface ILoginService {

	void save(User user);
	
	User findByUserName(String username);

	boolean checkIfEmailExists(String email);
}
