package com.blogapp.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.blogapp.dto.CommentDto;
import com.blogapp.entity.Comment;
import com.blogapp.exception.PostNotFoundException;
import com.blogapp.repository.CommentRepository;
import com.blogapp.service.impl.CommentServiceImpl;


@SpringBootTest
public class CommentServiceTest {
	
	
	 @InjectMocks
	    private CommentServiceImpl commentServiceImpl;

	    @InjectMocks
	    private CommentDto commentDto;

	    public void setUp() {
	        CommentDto commentDto = new CommentDto();
	        commentDto.setId(1L);
	        commentDto.setName("Ram");
	        commentDto.setEmail("ram@gmail.com");
	        commentDto.setContent("nice post");
	    }

	    @Mock
	    private CommentRepository commentRepository;
	
	    
	    @Test
	    public void createComment() throws PostNotFoundException {
	        Mockito.doReturn(commentDto).when(commentRepository).save(Mockito.any());
	        //assertEquals(commentDto.getId(), commentServiceImpl.createComment(1,commentDto).getId());
	        assertEquals(commentDto.getName(), commentServiceImpl.createComment(1,commentDto).getName());
	        System.out.println("**********************************************");
	        System.out.println(commentDto.getName() +"---"+commentServiceImpl.createComment(1,commentDto).getName() );
	        System.out.println("**********************************************");
	        assertEquals(commentDto.getEmail(), commentServiceImpl.createComment(1,commentDto).getEmail());
	        assertEquals(commentDto.getContent(), commentServiceImpl.createComment(1,commentDto).getContent());
	        
	    }

}
