package com.blogapp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.blogapp.dto.CommentDto;
import com.blogapp.exception.PostNotFoundException;
import com.blogapp.repository.CommentRepository;
import com.blogapp.service.CommentService;


public class CommentserviceTestAddC {
	
	

	@InjectMocks
	private CommentService commentService;
	
	@InjectMocks
	public CommentDto commentDto;
	
	@Mock
	private CommentRepository commentRepository;
	
	@BeforeEach
	public void setUp() {
		CommentDto commentDto = new CommentDto();
        commentDto.setId(1L);
        commentDto.setName("Ram");
        commentDto.setEmail("ram@gmail.com");
        commentDto.setContent("nice post");
        System.out.println(commentDto.getName());
	}
	
	@Test
	public void testAddProduct() throws PostNotFoundException {
		System.out.println("line1");
		Mockito.doReturn(commentDto).when(commentRepository).save(Mockito.any());
		System.out.println("running");
		assertEquals(commentDto.getName(), commentService.createComment(1,commentDto).getName());
	}

}
