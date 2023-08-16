package com.genc.api.services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genc.api.entities.User;
import com.genc.api.exceptions.ResourceNotFoundException;
import com.genc.api.payloads.UserDto;
import com.genc.api.repositories.UserRepo;
import com.genc.api.services.UserService;


@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		User savedUser = this.userRepo.save(user);
		return this.userDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userIds) {
		
		User user = this.userRepo.findById(userIds)
				.orElseThrow(()-> new ResourceNotFoundException("User", "id", userIds));
		user.setAsso_id(userDto.getAsso_id());
		user.setAsso_name(userDto.getAsso_name());
		user.setProj_id(userDto.getProj_id());
		user.setProj_name(userDto.getProj_name());
		user.setCust_name(userDto.getCust_name());
		user.setSkill(userDto.getSkill());
		user.setAsso_city(userDto.getAsso_city());
		user.setHcm_name(userDto.getHcm_name());
		user.setMentor_name(userDto.getMentor_name());
		user.setCurrent_status(userDto.getCurrent_status());
		
		User updateUser = this.userRepo.save(user);
		UserDto userDto1 = this.userDto(updateUser);
			
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userIds) {
		
		User user = userRepo.findById(userIds)
				.orElseThrow(()-> new ResourceNotFoundException("User", "Id", userIds));
		return this.userDto(user);
	}

	@Override
	public List<UserDto> getAllUserDto() {
		
		List<User> users = this.userRepo.findAll();
		List<UserDto> userDtos = users.stream().map(user->this.userDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userIds) {
		
		User user = this.userRepo.findById(userIds)
				.orElseThrow(()-> new ResourceNotFoundException("User", "Id", userIds));
		this.userRepo.delete(user);
		
		
	}
	
	public User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		
		
//		user.setId(userDto.getId());
//		user.setAsso_id(userDto.getAsso_id());
//		user.setAsso_name(userDto.getAsso_name());
//		user.setProj_id(userDto.getProj_id());
//		user.setProj_name(userDto.getProj_name());
//		user.setCust_name(userDto.getCust_name());
//		user.setSkill(userDto.getSkill());
//		user.setAsso_city(userDto.getAsso_city());
//		user.setHcm_name(userDto.getHcm_name());
//		user.setMentor_name(userDto.getMentor_name());
//		user.setCurrent_status(userDto.getCurrent_status());
		
		return user;
	}
	
	
	public UserDto userDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		
		
//		userDto.setId(user.getId());
//		userDto.setAsso_id(user.getAsso_id());
//		user.setAsso_name(userDto.getAsso_name());
//		userDto.setProj_id(user.getProj_id());
//		userDto.setProj_name(user.getProj_name());
//		userDto.setCust_name(user.getCust_name());
//		userDto.setSkill(user.getSkill());
//		userDto.setAsso_city(user.getAsso_city());
//		userDto.setHcm_name(user.getHcm_name());
//		userDto.setMentor_name(user.getMentor_name());
//		userDto.setCurrent_status(user.getCurrent_status());
		
		return userDto;
		
		
	}
	

	
}