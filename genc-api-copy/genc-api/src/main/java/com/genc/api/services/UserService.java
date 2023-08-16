package com.genc.api.services;

import java.util.List;

import com.genc.api.payloads.UserDto;


public interface UserService {
	
	UserDto createUser(UserDto userDto);
	UserDto updateUser(UserDto userDto, Integer userIds);
	UserDto getUserById(Integer userIds);
	List<UserDto> getAllUserDto();
	void deleteUser(Integer userIds);

}

