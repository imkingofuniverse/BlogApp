package com.blogapp.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blogapp.config.AppConstants;
import com.blogapp.dto.UserDto;
import com.blogapp.dto.UserLoginDto;
import com.blogapp.entity.Role;
import com.blogapp.entity.User;
import com.blogapp.exception.UserAlreadyExistException;
import com.blogapp.exception.UserNotFoundException;
import com.blogapp.repository.RoleRepository;
import com.blogapp.repository.UserRepository;
import com.blogapp.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepo;

	@Override
	public UserLoginDto registerNewUser(UserLoginDto userDto) throws UserAlreadyExistException {
		User user = this.modelMapper.map(userDto, User.class);
		if (this.userRepo.findByEmail(user.getEmail()).isPresent()) {
			throw new UserAlreadyExistException("User already exist for email ID: " + user.getEmail());
		} else {
			System.out.println(this.passwordEncoder.encode(user.getPassword()));
			user.setPassword(this.passwordEncoder.encode(user.getPassword()));
			Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();
			System.out.println(user.getRoles());
			
			user.getRoles().add(role);
			
			User newUser = this.userRepo.save(user);
			return this.modelMapper.map(newUser, UserLoginDto.class);
		}
	}
	
//	@Override
//	public UserLoginDto createUser(UserLoginDto userDto) throws UserAlreadyExistException {
//		User user = this.dtoToUser(userDto);
//		System.out.println(user);
//		if (this.userRepo.findByEmail(user.getEmail()).isPresent()) {
//			throw new UserAlreadyExistException("User already exist for email ID: " + user.getEmail());
//		} else {
//			User savedUser = this.userRepo.save(user);
//			return this.userToDto(savedUser);
//		}
//	}

	@Override
	public UserLoginDto updateUser(UserDto updateUserDto, Long userId) throws UserNotFoundException {
		User user = userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found for userId: " + userId));
		
		user.setName(updateUserDto.getName());
		user.setEmail(updateUserDto.getEmail());
		user.setBio(updateUserDto.getBio());

		User updatedUser = userRepo.save(user);
		return this.userToDto(updatedUser);
	}

	@Override
	public UserDto getUserById(Long userId) throws UserNotFoundException {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User not found for user ID: " + userId));
		return this.modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto getUserByEmail(String email) throws UserNotFoundException {
		User user = this.userRepo.findByEmail(email)
				.orElseThrow(() -> new UserNotFoundException("User not found for email ID: " + email));
		return this.modelMapper.map(user, UserDto.class);
	}

	@Override
	public List<UserLoginDto> getAllUsers() throws UserNotFoundException {
		List<User> users = this.userRepo.findAll();

		if (users.isEmpty()) {
			throw new UserNotFoundException("No users found");
		}

		List<UserLoginDto> userDtos = users.stream().map(this::userToDto).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public String deleteUser(Long userId) throws UserNotFoundException {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User not found for userId: " + userId));
		this.userRepo.delete(user);
		return "User deleted successfully";
	}

	// Model Mapper
	private User dtoToUser(UserLoginDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		return user;
	}

	private UserLoginDto userToDto(User user) {
		UserLoginDto userDto = this.modelMapper.map(user, UserLoginDto.class);
		return userDto;
	}


}
