package com.blogapp.service;

import java.util.List;

import com.blogapp.dto.UserDto;
import com.blogapp.dto.UserLoginDto;
import com.blogapp.exception.UserAlreadyExistException;
import com.blogapp.exception.UserNotFoundException;

public interface UserService {
	//Create User
//	UserLoginDto createUser(UserLoginDto userDto) throws UserAlreadyExistException;
	//Update User
	UserLoginDto updateUser(UserDto updateUserDto, Long userId) throws UserNotFoundException;
	//Get User By Id
	UserDto getUserById(Long userId) throws UserNotFoundException;
	//Get User By Email
	UserDto getUserByEmail(String email) throws UserNotFoundException;
	//Get all Users
	List<UserLoginDto> getAllUsers() throws UserNotFoundException;
	//Delete User
	String deleteUser(Long userId) throws UserNotFoundException;
	
	UserLoginDto registerNewUser(UserLoginDto userDto) throws UserAlreadyExistException;
}
