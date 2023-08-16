package com.genc.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.genc.api.payloads.ApiResponse;
import com.genc.api.payloads.UserDto;
import com.genc.api.services.UserService;


@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		UserDto createUserDto = this.userService.createUser(userDto);
		return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);	
	}
	
	@PutMapping("/{userIds}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userIds") Integer uids){
		UserDto updatedUser = this.userService.updateUser(userDto, uids);
		return ResponseEntity.ok(updatedUser);
	}
	//Admin can delete user
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{userIds}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userIds") Integer uids){
		this.userService.deleteUser(uids);
		return ResponseEntity.ok(new ApiResponse("User deleted Successfully", true));
	}
	
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUser(){
		return ResponseEntity.ok(this.userService.getAllUserDto());
	}
	
//	@GetMapping("/{userIds}")
//	private UserDto getSingleUser(@PathVariable("userIds") Integer uids) {
//		return userService.getUserById(uids);
//	}
	
	
	@GetMapping("/{userIds}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userIds){
		return ResponseEntity.ok(this.userService.getUserById(userIds));
	}

}

