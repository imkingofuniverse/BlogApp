package com.blogapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogapp.dto.UserDto;
import com.blogapp.dto.UserLoginDto;
import com.blogapp.exception.UserNotFoundException;
import com.blogapp.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;

//	@PostMapping("/signup")
//	public ResponseEntity<UserLoginDto> createUser(@Valid@RequestBody UserLoginDto userDto) throws UserAlreadyExistException {
//		return new ResponseEntity<UserLoginDto>(this.userService.createUser(userDto), HttpStatus.OK);
//	}

	@PutMapping("/{userId}")
	public ResponseEntity<UserLoginDto> updateUser(@Valid@RequestBody UserDto updateUserDto,@PathVariable("userId") Long userId)
			throws UserNotFoundException {
		return new ResponseEntity<UserLoginDto>(this.userService.updateUser(updateUserDto, userId), HttpStatus.OK);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Long userId) throws UserNotFoundException {
		return new ResponseEntity<UserDto>(this.userService.getUserById(userId), HttpStatus.OK);
	}
	
	@GetMapping("/by-email/{email}")
	public ResponseEntity<UserDto> getUserByEmail(@PathVariable("email") String email)throws UserNotFoundException{
		return new ResponseEntity<UserDto>(this.userService.getUserByEmail(email),HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<UserLoginDto>> getAllUsers() throws UserNotFoundException {
		return new ResponseEntity<List<UserLoginDto>>(this.userService.getAllUsers(), HttpStatus.OK);
	}
	
	//ADMIN 
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{userId}")
	public ResponseEntity<String> deleteUser(Long userId) throws UserNotFoundException {
		return new ResponseEntity<String>(this.userService.deleteUser(userId), HttpStatus.OK);
	}

}