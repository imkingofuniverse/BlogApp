package com.blogapp.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
	
	Long id=33L;
	
//	UserDto userDto=new UserDto("Harshwardhan","Katkar","h.katkar2000@gmail.com","harsh123","Hi i am Harshwardhan");
	UserResponseDto userResponse=new UserResponseDto(0L,"Harshwardhan","Katkar","h.katkar2000@gmail.com","Hi i am Harshwardhan");
	
	@Test
	void resgisterUserTest() throws UserAlreadyExistException, UserNotFoundException {
		assertEquals(userResponse.getFirstName(), userService.updateUser(userResponse,id).getFirstName());
		assertEquals(userResponse.getLastName(), userService.updateUser(userResponse,id).getLastName());
		assertEquals(userResponse.getEmail(), userService.updateUser(userResponse,id).getEmail());
		assertEquals(userResponse.getBio(), userService.updateUser(userResponse,id).getBio());
	}
	
	
}
