package com.simcode.fps.web.dto;

import java.util.ArrayList;
import java.util.List;

import com.simcode.fps.repository.model.Role;
import com.simcode.fps.repository.model.User;

public class UserDto {
	
	private String username;
	private String password;
	private String email;
	private String id;
	private List<Role> roles;
	
	public UserDto(User user) {
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.email = user.getEmail();
		this.id = String.valueOf(user.getId());
		this.setRoles(new ArrayList<>(user.getRoles()));
	}
	
	public UserDto() {
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getId() {
		return Long.valueOf(id);
	}
	public void setId(String id) {
		this.id = id;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}
