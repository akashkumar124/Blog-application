package com.akash.blog.services.impl;

import java.util.List;import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akash.blog.entity.User;
import com.akash.blog.exception.ResourceNotFoundException;
import com.akash.blog.payloads.UserDto;
import com.akash.blog.repositories.UserRepo;
import com.akash.blog.services.UserService;

@Service
public class userServiceImpl implements UserService{
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {
		// TODO Auto-generated method stub
		
		User user = this.dtoToUser(userDto);
		User saveduser = this.userRepo.save(user);
		return this.userToDto(saveduser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		// TODO Auto-generated method stub
		
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","id",userId));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updatedUser = this.userRepo.save(user);
		UserDto  userDto1 = this.userToDto(updatedUser);
		
		
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		// TODO Auto-generated method stub
		
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","id",userId));
		
		UserDto  userDto = this.userToDto(user);
		return userDto;
	}

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		List<User> users=this.userRepo.findAll();
		
		List<UserDto> cuserDto=users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		
		return cuserDto;
	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub
		
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","id",userId));
		
		this.userRepo.delete(user);
		
	}
	
	
	private User dtoToUser(UserDto userDto)
	{
		User user = this.modelMapper.map(userDto, User.class);	
//		user.setId(userDto.getId());
//		user.setAbout(userDto.getAbout());
//		user.setEmail(userDto.getEmail());
//		user.setName(userDto.getName());
//		user.setPassword(userDto.getPassword());
		
		return user;
	}
	
	private UserDto userToDto(User user)
	{
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		
//		userDto.setId(user.getId());
//		userDto.setAbout(user.getAbout());
//		userDto.setEmail(user.getEmail());
//		userDto.setName(user.getName());
//		userDto.setPassword(user.getPassword());
		
		return userDto;
	}
	

}
