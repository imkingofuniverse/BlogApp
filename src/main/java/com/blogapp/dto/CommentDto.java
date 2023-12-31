package com.blogapp.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    
	private long id;
	
	@NotEmpty(message = "User name should not be Empty")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "User name should contain only letters")
    private String name;
	
	@NotBlank(message = "User email cannot be blank")
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Customer email should be in valid format")
    private String email;
	
	@NotEmpty(message = "Content should not be Empty")
    private String content;
    
}
