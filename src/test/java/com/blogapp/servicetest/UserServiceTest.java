package com.blogapp.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.blogapp.dto.UserDto;
import com.blogapp.dto.UserResponseDto;
import com.blogapp.exception.UserAlreadyExistException;
import com.blogapp.exception.UserNotFoundException;
import com.blogapp.service.UserService;

@SpringBootTest
public class UserServiceTest {

	@Autowired
	UserService userService;


	UserDto userDto = new UserDto("Harshwardhan", "Katkar", generateUniqueEmail(), "harsh123", "Hi i am Harshwardhan");
	UserResponseDto userResponse = new UserResponseDto(0L, "Harshwardhan", "Katkar", "h.katkar2000@gmail.com",
			"Hi i am Harshwardhan");
	

	public static String generateUniqueEmail() {
		// Generate a unique identifier
		String uniqueId = UUID.randomUUID().toString();

		// Create the email ID using the unique identifier
		String emailId = "user_" + uniqueId + "@example.com";

		return emailId;
	}

	@Test
	void updateUserTest() throws UserAlreadyExistException, UserNotFoundException {
		Long id=userService.registerNewUser(userDto).getId();
		assertEquals(userResponse.getFirstName(), userService.updateUser(userResponse, id).getFirstName());
		assertEquals(userResponse.getLastName(), userService.updateUser(userResponse, id).getLastName());
		assertEquals(userResponse.getEmail(), userService.updateUser(userResponse, id).getEmail());
		assertEquals(userResponse.getBio(), userService.updateUser(userResponse, id).getBio());
	}

	@Test
	void registerUserTest() throws UserAlreadyExistException {

		UserResponseDto user = userService.registerNewUser(userDto);
		assertEquals(userDto.getFirstName(), user.getFirstName());
		assertEquals(userDto.getLastName(), user.getLastName());
		assertEquals(userDto.getEmail(), user.getEmail());
		assertEquals(userDto.getBio(), user.getBio());
	}
	
	@Test
	void getUserByIdTest() throws UserAlreadyExistException, UserNotFoundException {
		Long id=userService.registerNewUser(userDto).getId();
		assertEquals(userDto.getFirstName(), userService.getUserById(id).getFirstName());
		assertEquals(userDto.getLastName(), userService.getUserById(id).getLastName());
		assertEquals(userDto.getEmail(), userService.getUserById(id).getEmail());
		assertEquals(userDto.getBio(), userService.getUserById(id).getBio());	
	}
	
	@Test
	void getUserByEmailTest() throws UserAlreadyExistException, UserNotFoundException {
		String email=userService.registerNewUser(userDto).getEmail();
		assertEquals(userDto.getFirstName(), userService.getUserByEmail(email).getFirstName());
		assertEquals(userDto.getLastName(), userService.getUserByEmail(email).getLastName());
		assertEquals(userDto.getEmail(), userService.getUserByEmail(email).getEmail());
		assertEquals(userDto.getBio(), userService.getUserByEmail(email).getBio());
	}

}
