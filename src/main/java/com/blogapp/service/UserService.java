package com.blogapp.service;

import java.util.List;

import com.blogapp.dto.UserDto;
import com.blogapp.exception.UserAlreadyExistException;
import com.blogapp.exception.UserNotFoundException;

public interface UserService {
	//Create User
	UserDto createUser(UserDto userDto) throws UserAlreadyExistException;
	//Update User
	UserDto updateUser(UserDto user, Long userId) throws UserNotFoundException;
	//Get User By Id
	UserDto getUserById(Long userId) throws UserNotFoundException;
	//Get User By Email
	UserDto getUserByEmail(String email) throws UserNotFoundException;
	//Get all Users
	List<UserDto> getAllUsers() throws UserNotFoundException;
	//Delete User
	String deleteUser(Long userId) throws UserNotFoundException;
}
