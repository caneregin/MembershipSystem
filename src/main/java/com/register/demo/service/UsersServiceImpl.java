package com.register.demo.service;


import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.register.demo.model.Roles;
import com.register.demo.model.Users;
import com.register.demo.repository.RolesRepository;
import com.register.demo.repository.UsersRepository;
@Service("usersService")
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private RolesRepository rolesRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Override
	public Users findUsersByEmail(String email) {
		// TODO Auto-generated method stub
		return usersRepository.findByEmail(email);
	}

	@Override
	public void saveUser(Users users) {
		// TODO Auto-generated method stub
		users.setPw(bCryptPasswordEncoder.encode(users.getPw()));
		users.setActives(1);
		Roles usersRole = rolesRepository.findByRoles("admin");
		users.setRoles(new HashSet<Roles>(Arrays.asList(usersRole)));
		usersRepository.save(users);
		
	}

	

}