package com.register.demo.service;

import com.register.demo.model.Users;

public interface UsersService {
	public Users findUsersByEmail(String email);
	public void saveUser(Users users);

}
