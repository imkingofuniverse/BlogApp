package com.blogapp.service;

import com.blogapp.dto.PostDto;
import com.blogapp.dto.PostResponse;

public interface PostService {

	public PostDto createPost(PostDto postDto);
	
	public PostDto getPostById(long id);
	
	public void deletePost(long id);
	
	public PostDto updatePost(PostDto postDto, long id);
	
	public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
	
}