package com.genc.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.genc.api.entities.User;



public interface UserRepo extends JpaRepository<User, Integer>{
	
	

}
