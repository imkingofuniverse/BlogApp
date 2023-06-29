package com.blogapp.dto;

import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.blogapp.entity.Comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

	private Long id;
	
//	@NotNull(message = "title should not be empty")
	@NotEmpty
	@Size(min=4, message = "Title must contain atleast 4 charcters")
	private String title;
	
	private String image;
	
	@NotNull(message = "Please add your content")
	private String content;
	
	@NotNull(message = "please specify category of your post")
	private String category;
	//private Set<Comment> comments;
	
}