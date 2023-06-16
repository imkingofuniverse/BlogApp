package com.blogapp.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapp.dto.UserDto;
import com.blogapp.entity.User;
import com.blogapp.exception.UserAlreadyExistException;
import com.blogapp.exception.UserNotFoundException;
import com.blogapp.repository.UserRepository;
import com.blogapp.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) throws UserAlreadyExistException {
		User user = this.dtoToUser(userDto);
		if (this.userRepo.findByEmail(user.getEmail()) != null) {
			System.out.println(this.userRepo.findByEmail(user.getEmail()));
			throw new UserAlreadyExistException("User already exist for email ID: " + user.getEmail());
		} else {
			User savedUser = this.userRepo.save(user);
			return this.userToDto(savedUser);
		}
	}

	@Override
	public UserDto updateUser(UserDto userDto, Long userId) throws UserNotFoundException {
		Optional<User> optionalUser = userRepo.findById(userId);
		User user = optionalUser.orElseThrow(() -> new UserNotFoundException("User not found for userId: " + userId));

		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());

		User updatedUser = userRepo.save(user);
		return this.userToDto(updatedUser);
	}

	@Override
	public UserDto getUserById(Long userId) throws UserNotFoundException {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User not found for user ID: " + userId));
		return this.userToDto(user);
	}

	@Override
	public UserDto getUserByEmail(String email) throws UserNotFoundException{
		User user=this.userRepo.findByEmail(email);
		if(user==null) {
			throw new UserNotFoundException("User not found for email ID: " + email);
		}else {
			UserDto userDto=this.userToDto(user);
			return userDto;
		}
		
		
	}
	
	@Override
	public List<UserDto> getAllUsers() throws UserNotFoundException {
		List<User> users = this.userRepo.findAll();

		if (users.isEmpty()) {
			throw new UserNotFoundException("No users found");
		}

		List<UserDto> userDtos = users.stream().map(this::userToDto).collect(Collectors.toList());
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
	private User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		return user;
	}

	private UserDto userToDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		return userDto;
	}

}
