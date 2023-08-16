package com.genc.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.genc.api.entities.UserLogin;
import com.genc.api.exceptions.ResourceNotFoundException;
import com.genc.api.repositories.UserLoginRepo;



@Service
public class CustomUserDetailService implements UserDetailsService{
	
	@Autowired
	private UserLoginRepo userLoginRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		UserLogin userLogin = this.userLoginRepo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("UserLogin", "email :" +username, 0));
		
		return userLogin;
	}

} 