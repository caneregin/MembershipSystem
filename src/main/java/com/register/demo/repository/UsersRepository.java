package com.register.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.register.demo.model.Users;
@Repository("usersRepository")
public interface UsersRepository extends JpaRepository<Users,Long>{
	Users findByEmail(String email);
}