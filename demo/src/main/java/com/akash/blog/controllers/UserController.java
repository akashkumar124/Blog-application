package com.akash.blog.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akash.blog.payloads.ApiResonse;
import com.akash.blog.payloads.UserDto;
import com.akash.blog.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto useDto){
		
		UserDto createdUserDto=this.userService.createUser(useDto);
		
		return new ResponseEntity<>(createdUserDto,HttpStatus.CREATED);
		
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateeUser(@Valid @RequestBody UserDto useDto, @PathVariable Integer userId){
		
		UserDto createdUserDto=this.userService.updateUser(useDto, userId);
		
		return new ResponseEntity<>(createdUserDto,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResonse> deleteUser(@PathVariable Integer userId){
		this.userService.deleteUser(userId);
		return new ResponseEntity<ApiResonse>(new ApiResonse("User Deleted successfull", true), HttpStatus.OK);
	}
	
	
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUser(){
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId){
		
//		UserDto userdto=this.userService.getUserById(userId);
		
		return ResponseEntity.ok(this.userService.getUserById(userId));
		
//		return ResponseEntity<UserDto>(userdto,HttpStatus.OK);
	}

}
