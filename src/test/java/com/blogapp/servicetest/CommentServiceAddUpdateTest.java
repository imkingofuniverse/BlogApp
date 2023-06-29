package com.blogapp.servicetest;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.blogapp.controller.AuthController;
import com.blogapp.controller.CommentController;
import com.blogapp.controller.UserController;
import com.blogapp.dto.CommentDto;
import com.blogapp.dto.PostDto;
import com.blogapp.dto.UserDto;
import com.blogapp.exception.CommentNotFoundException;
import com.blogapp.exception.PostNotFoundException;
import com.blogapp.exception.UserAlreadyExistException;
import com.blogapp.exception.UserNotFoundException;
import com.blogapp.service.CommentService;
import com.blogapp.service.PostService;
import com.blogapp.service.UserService;

@SpringBootTest
public class CommentServiceAddUpdateTest {
    
    @Autowired
    CommentService commentService;
    
    @Autowired
    CommentController commentController;
    
    @Autowired
    PostService postService;
    
    @Autowired
    UserService userService;
    
    @Autowired
    AuthController authController;
    
//  @Autowired
//  PostService commentService;
    
    CommentDto commentDto = new CommentDto(1L,"Name","email@gmail.com","contenttttttttttttttt");
    UserDto userDto = new UserDto("Harswd nhwardhan","Knatkar","yahoo@ujhdj.net","harsh123","Hi i am Harshwardhan");
    PostDto postDto = new PostDto(1L,"Titleeeee","Image","Contenttttttttttttttttttttttt","Category");
    
//    @Test
//    void createCommentTest() throws PostNotFoundException, UserAlreadyExistException, UserNotFoundException {
//        authController.registerUser(userDto);
//        System.out.println(userDto.getFirstName()+ authController.registerUser(userDto).toString());
//        
//        postService.createPost(postDto, 1L);
//        
//        System.out.println(commentDto.getName()+ commentService.createComment(1L, commentDto).getName());
//        //assertEquals(commentDto.getId(), commentService.createComment(1L, commentDto).getId());
//        assertEquals(commentDto.getName(), commentService.createComment(1L, commentDto).getName());
//        assertEquals(commentDto.getEmail(), commentService.createComment(1L, commentDto).getEmail());
//        assertEquals(commentDto.getContent(), commentService.createComment(1L, commentDto).getContent());
//    }
    
//  @Test
//  void updateCommentTest() throws PostNotFoundException, UserAlreadyExistException, UserNotFoundException, CommentNotFoundException {
//      authController.registerUser(userDto);
//      System.out.println(userDto.getFirstName()+ authController.registerUser(userDto).toString());
//      
//      postService.createPost(postDto, 1L);
//      commentService.createComment(1L, commentDto);
//      System.out.println("************************************************");
//      System.out.println((commentService.getCommentsByPostId(1L)).toString());
//      System.out.println(commentDto.getName()+ commentService.updateComment(1L, 2L, commentDto).getName());
//      System.out.println("************************************************");
//      assertEquals(3L, commentService.updateComment(1L, 3L, commentDto).getId());
//      assertEquals(commentDto.getName(), commentService.updateComment(1L, 2L, commentDto).getName());
//      assertEquals(commentDto.getEmail(), commentService.updateComment(1L, 2L, commentDto).getEmail());
//      assertEquals(commentDto.getContent(), commentService.updateComment(1L, 2L, commentDto).getContent());
//  }
//    
//  @Test
//  void deleteCommentTest() throws CommentNotFoundException, PostNotFoundException, UserAlreadyExistException, UserNotFoundException {
//      
//      authController.registerUser(userDto);
//      System.out.println(userDto.getFirstName()+ authController.registerUser(userDto).toString());
//      
//      postService.createPost(postDto, 1L);
//      commentService.createComment(1L, commentDto);
//      
//      assertEquals("Comment Successfully Deleted",commentController.deleteComment(1L, 3L));
//  }
    
    

}