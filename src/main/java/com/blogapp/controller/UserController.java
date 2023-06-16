package com.blogapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogapp.dto.UserDto;
import com.blogapp.exception.UserNotFoundException;
import com.blogapp.service.UserService;


@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
		return new ResponseEntity<UserDto>(this.userService.createUser(userDto),HttpStatus.OK);
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,@PathVariable("userId") Long userId) throws UserNotFoundException{
		return new ResponseEntity<UserDto>(this.userService.updateUser(userDto, userId),HttpStatus.OK);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserById(Long userId){
		return new ResponseEntity<UserDto>(this.userService.getUserById(userId),HttpStatus.OK);
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<String> deleteUser(Long userId){
		return new ResponseEntity<String>(this.userService.deleteUser(userId),HttpStatus.OK);
	}
	
	
	
}
