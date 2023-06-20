package com.blogapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogapp.dto.UserLoginDto;
import com.blogapp.exception.UserAlreadyExistException;
import com.blogapp.payload.JwtAuthRequest;
import com.blogapp.payload.JwtAuthResponse;
import com.blogapp.repository.UserRepository;
import com.blogapp.security.JwtTokenHelper;
import com.blogapp.service.UserService;

@RestController
@RequestMapping("/user/auth")
public class AuthController {

	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Autowired
	private UserDetailsService userDetailsSerrvice;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception {

		this.authenticate(request.getUsername(), request.getPassword());
		UserDetails userDetails = this.userDetailsSerrvice.loadUserByUsername(request.getUsername());
		String token = this.jwtTokenHelper.generateToken(userDetails);
		JwtAuthResponse response = new JwtAuthResponse();
		response.setToken(token);
		return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
	}

	private void authenticate(String username, String password) throws Exception {

		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				username, password);
		try {
			this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		} catch (BadCredentialsException e) {
			System.out.println("Invalid details !!");
			throw new Exception("Invalid username and password");
		}

	}
	
	
	
	//register new user
	@PostMapping("/signup")
	public ResponseEntity<UserLoginDto> registerUser(@Valid@RequestBody UserLoginDto userDto) throws UserAlreadyExistException{
		System.out.println(userDto);
		UserLoginDto registeredUser=this.userService.registerNewUser(userDto);
		return new ResponseEntity<UserLoginDto>(registeredUser,HttpStatus.CREATED);
	}
}
