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
import com.genc.api.payloads.UserLoginDto;
import com.genc.api.services.UserLoginService;


@RestController
@RequestMapping("/api")
public class UserLoginController {
	
	@Autowired
	private UserLoginService userLoginService;
	
	@PostMapping("/")
	public ResponseEntity<UserLoginDto> createUserLogin(@Valid @RequestBody UserLoginDto userLoginDto){
		UserLoginDto createUserLoginDto = this.userLoginService.createUserLogin(userLoginDto);
		return new ResponseEntity<>(createUserLoginDto, HttpStatus.CREATED);	
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserLoginDto> updateUserLogin
	(@Valid @RequestBody UserLoginDto userLoginDto, @PathVariable("userId") Integer uid){
		UserLoginDto updatedUserLogin =  this.userLoginService.updateUserLogin(userLoginDto, uid);
		return ResponseEntity.ok(updatedUserLogin);
		
	}
	
	//Admin can delete 
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUserLogin(@PathVariable("userId") Integer uid){
		this.userLoginService.deleteUserLogin(uid);
		return ResponseEntity.ok(new ApiResponse("User deleted Successfully", true));
	}
	
	@GetMapping("/")
	public ResponseEntity<List<UserLoginDto>> getAllUserLogin(){
		return ResponseEntity.ok(this.userLoginService.getAllUserLoginDto());
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserLoginDto> getSingleUserLogin(@PathVariable Integer userId){
		return ResponseEntity.ok(this.userLoginService.getUserLoginById(userId));
	}


}
