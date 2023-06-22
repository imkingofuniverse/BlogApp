package com.blogapp.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.blogapp.dto.CommentDto;
import com.blogapp.entity.Comment;
import com.blogapp.entity.Post;
import com.blogapp.exception.PostNotFoundException;
import com.blogapp.repository.CommentRepository;
import com.blogapp.repository.PostRepository;
import com.blogapp.service.CommentService;

@RunWith(MockitoJUnitRunner.class)
public class CommentServiceTestAddComment {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private CommentService commentService;

    @Before
    public void setup() {
        // Set up any necessary mock data or configurations
    }

    @Test
    public void testCreateComment() throws PostNotFoundException {
        // Arrange
        long postId = 1L;
        System.out.println("*******************************************************");

        CommentDto mockCommentDto = new CommentDto();
        // Set properties of mockCommentDto
        mockCommentDto.setId(1L);
        mockCommentDto.setName("Ram");
        mockCommentDto.setEmail("ram@gmail.com");
        mockCommentDto.setContent("nice post");
        System.out.println(mockCommentDto.getName());
        
        Comment mockComment = new Comment();
        // Set properties of mockComment
        mockComment.setId(1L);
        mockComment.setName("Ram");
        mockComment.setEmail("ram@gmail.com");
        mockComment.setContent("nice post");

        Post mockPost = new Post();
        // Set properties of mockPost
        mockPost.setId(1L);
        mockPost.setTitle("MyBlog");
        mockPost.setImage("URL");
        mockPost.setContent("Text");
        mockPost.setCategory("My Category");

        when(postRepository.findById(postId)).thenReturn(Optional.of(mockPost));
        when(commentRepository.save(any(Comment.class))).thenReturn(mockComment);

        // Act
        CommentDto result = commentService.createComment(postId, mockCommentDto);

        // Assert
        assertEquals(mockCommentDto, result);
        System.out.println(mockCommentDto.getEmail() + " ********** " + result.getEmail());
//        assertEquals(mockPost, mockComment.getPost());
//        verify(postRepository).findById(postId);
//        verify(commentRepository).save(any(Comment.class));
    }
}