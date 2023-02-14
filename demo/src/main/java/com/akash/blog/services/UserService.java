package com.akash.blog.services;

import java.util.List;

import com.akash.blog.entity.User;
import com.akash.blog.payloads.UserDto;



public interface UserService {
	
	public UserDto createUser(UserDto userDto);
	
	public UserDto updateUser(UserDto userDto, Integer userId);
	public UserDto getUserById(Integer userId);
	
	public List<UserDto> getAllUsers();
	
	void deleteUser(Integer userId);

}
 