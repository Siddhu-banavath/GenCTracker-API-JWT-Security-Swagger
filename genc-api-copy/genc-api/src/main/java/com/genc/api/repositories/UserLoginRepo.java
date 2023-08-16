package com.genc.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.genc.api.entities.UserLogin;



public interface UserLoginRepo extends JpaRepository<UserLogin, Integer> {
	
	Optional<UserLogin> findByEmail(String email);

}
