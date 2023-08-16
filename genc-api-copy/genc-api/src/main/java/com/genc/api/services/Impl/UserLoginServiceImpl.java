package com.genc.api.services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.genc.api.config.AppConstants;
import com.genc.api.entities.Role;
import com.genc.api.entities.UserLogin;
import com.genc.api.exceptions.ResourceNotFoundException;
import com.genc.api.payloads.UserLoginDto;
import com.genc.api.repositories.RoleRepo;
import com.genc.api.repositories.UserLoginRepo;
import com.genc.api.services.UserLoginService;


@Service
public class UserLoginServiceImpl implements UserLoginService{
	
	@Autowired
	public UserLoginRepo userLoginRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;

	@Override
	public UserLoginDto createUserLogin(UserLoginDto userLoginDto) {
		UserLogin userLogin = this.dtoToUserLogin(userLoginDto);
		UserLogin savedUserLogin = this.userLoginRepo.save(userLogin);
		return this.userLoginDto(savedUserLogin);

	}

	@Override
	public UserLoginDto updateUserLogin(UserLoginDto userLoginDto, Integer userId) {
		
		UserLogin userLogin = this.userLoginRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("UserLogin", " id ", userId));
		
		userLogin.setName(userLoginDto.getName());
		userLogin.setEmail(userLoginDto.getEmail());
		userLogin.setPassword(userLoginDto.getPassword());
		UserLogin updatedUserLogin = this.userLoginRepo.save(userLogin);
		UserLoginDto userLoginDto1 =   this.userLoginDto(updatedUserLogin);
		return userLoginDto1;
	}

	@Override
	public UserLoginDto getUserLoginById(Integer userId) {
		
		UserLogin userLogin = userLoginRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("UserLogin", "Id", userId));
		return this.userLoginDto(userLogin);
	}

	@Override
	public List<UserLoginDto> getAllUserLoginDto() {
		
		List<UserLogin> userLogins = this.userLoginRepo.findAll();
		List<UserLoginDto> userLoginDtos = userLogins.stream().map(userLogin->this.userLoginDto(userLogin))
				.collect(Collectors.toList());
		
		return userLoginDtos;
	}

	@Override
	public void deleteUserLogin(Integer userId) {
		
		UserLogin userLogin = this.userLoginRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("UserLogin", "Id", userId));
		this.userLoginRepo.delete(userLogin);
		
	}
	
	public UserLogin dtoToUserLogin(UserLoginDto userLoginDto) {
		
		UserLogin userLogin = this.modelMapper.map(userLoginDto, UserLogin.class);
		
//		userLogin.setId(userLoginDto.getId());
//		userLogin.setName(userLoginDto.getName());
//		userLogin.setEmail(userLoginDto.getEmail());
//		userLogin.setPassword(userLoginDto.getPassword());
		
		return userLogin;
	}
	
	public UserLoginDto userLoginDto(UserLogin userLogin) {
		UserLoginDto userLoginDto = this.modelMapper.map(userLogin, UserLoginDto.class);
	
//		userLoginDto.setId(userLogin.getId());
//		userLoginDto.setName(userLogin.getName());
//		userLoginDto.setEmail(userLogin.getEmail());
//		userLoginDto.setPassword(userLogin.getPassword());
		
		return userLoginDto;
	
	}

	@Override
	public UserLoginDto registerNewUser(UserLoginDto userLoginDto) {
		
		UserLogin userLogin = this.modelMapper.map(userLoginDto, UserLogin.class);
		
		//encode the password
		userLogin.setPassword(this.passwordEncoder.encode(userLogin.getPassword()));
		
		//roles
		 Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();
		 
		 userLogin.getRoles().add(role);
		 
		 UserLogin newUser = this.userLoginRepo.save(userLogin);
		 
		 return this.modelMapper.map(newUser, UserLoginDto.class);
		

	}
}
