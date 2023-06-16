package com.blogapp.service;

import java.util.List;

import com.blogapp.dto.UserDto;
import com.blogapp.exception.UserNotFoundException;

public interface UserService {
	//Create User
	UserDto createUser(UserDto userDto);
	//Update User
	UserDto updateUser(UserDto user, Long userId) throws UserNotFoundException;
	//Get User By Id
	UserDto getUserById(Long userId);
	//Get all Users
	List<UserDto> getAllUsers();
	//Delete User
	String deleteUser(Long userId);
}
