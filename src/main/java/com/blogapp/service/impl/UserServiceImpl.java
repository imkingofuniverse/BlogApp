package com.blogapp.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapp.dto.UserDto;
import com.blogapp.entity.User;
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
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		User savedUser = this.userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Long userId) throws UserNotFoundException {
		Optional<User> user = userRepo.findById(userId);
		try {
			if (user.isPresent()) {
				user.get().setName(userDto.getName());
				user.get().setEmail(userDto.getEmail());
				user.get().setPassword(userDto.getPassword());
				User updatedUser = this.userRepo.save(user.get());
				return this.userToDto(updatedUser);
			} else {
				throw new UserNotFoundException();
			}
		} catch (UserNotFoundException e) {
			throw new UserNotFoundException("User not found for user ID: " + userId);
		}

	}

	@Override
	public UserDto getUserById(Long userId) {
		User user = this.userRepo.findById(userId).get();
		UserDto userDto = this.userToDto(user);
		return userDto;
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = this.userRepo.findAll();
		List<UserDto> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public String deleteUser(Long userId) {
		User user = this.userRepo.findById(userId).get();
		this.userRepo.delete(user);
		return "User deleted successfully";
	}

	// Model Mappers
	private User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		return user;
	}

	private UserDto userToDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		return userDto;
	}

}
