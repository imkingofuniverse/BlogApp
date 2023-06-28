package com.blogapp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.blogapp.dto.PostDto;
import com.blogapp.dto.PostResponse;
import com.blogapp.entity.Post;
import com.blogapp.entity.User;
import com.blogapp.exception.ResourceNotFoundException;
import com.blogapp.exception.UserNotFoundException;
import com.blogapp.repository.PostRepository;
import com.blogapp.repository.UserRepository;
import com.blogapp.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private ModelMapper mapper;
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private UserRepository userRepository;

//	public PostServiceImpl(ModelMapper mapper, PostRepository postRepository) {
//		this.mapper = mapper;
//		this.postRepository = postRepository;
//	}

	@Override
	public PostDto createPost(PostDto postDto, Long id) throws UserNotFoundException {

		User user = this.userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User not found for userId: " + id));

		Post post = mapToEntity(postDto);
		post.setUser(user);
		Post newPost = postRepository.save(post);
		PostDto postResponse = mapToDto(newPost);
		return postResponse;
	}

	private PostDto mapToDto(Post post) {

		PostDto postDto = mapper.map(post, PostDto.class);

		return postDto;
	}

	private Post mapToEntity(PostDto postDto) {

		Post post = mapper.map(postDto, Post.class);

		return post;
	}
	
	@Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
	
	@Override
	public PostDto getPostById(long id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
		return mapToDto(post);
	}

	@Override
	public void deletePost(long id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
		postRepository.delete(post);
	}

	@Override
	public PostDto updatePost(PostDto postDto, long id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

		post.setTitle(postDto.getTitle());
		post.setImage(postDto.getImage());
		post.setContent(postDto.getContent());
		post.setCategory(postDto.getCategory());

		Post updatedPost = postRepository.save(post);
		return mapToDto(updatedPost);
	}

	@Override
	public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();

		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

		Page<Post> posts = postRepository.findAll(pageable);

		List<Post> listOfPosts = posts.getContent();

		List<PostDto> content = listOfPosts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();
		postResponse.setContent(content);
		postResponse.setPageNo(posts.getNumber());
		postResponse.setPageSize(posts.getSize());
		postResponse.setTotalElements(posts.getTotalElements());
		postResponse.setTotalPages(posts.getTotalPages());
		postResponse.setLast(posts.isLast());

		return postResponse;
	}

}