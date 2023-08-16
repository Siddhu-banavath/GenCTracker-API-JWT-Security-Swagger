package com.genc.api.services;

import java.util.List;

import com.genc.api.payloads.UserLoginDto;




public interface UserLoginService {
	
	UserLoginDto registerNewUser(UserLoginDto userLogin);
	
	UserLoginDto createUserLogin(UserLoginDto userLoginDto);
	UserLoginDto updateUserLogin(UserLoginDto userLoginDto, Integer userId);
	UserLoginDto getUserLoginById(Integer userId);
	List<UserLoginDto> getAllUserLoginDto();
	void deleteUserLogin(Integer userId);

	
}

