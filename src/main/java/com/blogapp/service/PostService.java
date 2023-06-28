package com.blogapp.service;

import com.blogapp.dto.PostDto;
import com.blogapp.dto.PostResponse;
import com.blogapp.entity.Post;

import java.util.List;

public interface PostService {

	public PostDto createPost(PostDto postDto);
	
	public PostDto getPostById(long id);
	
	public void deletePost(long id);
	
	public PostDto updatePost(PostDto postDto, long id);
	
	public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
	
	public List<Post> getPostByCategory(String category);

	public List<Post> getAllPosts();

	
}