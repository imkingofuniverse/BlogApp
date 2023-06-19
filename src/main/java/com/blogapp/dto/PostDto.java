package com.blogapp.dto;

import java.util.Set;

import com.blogapp.entity.Comment;

import lombok.Data;

@Data
public class PostDto {

	private Long id;
	private String title;
	private String image;
	private String content;
	private String category;
	private Set<Comment> comments;
	
}
