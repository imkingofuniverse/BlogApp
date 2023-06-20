package com.blogapp.service;

import java.util.List;

import com.blogapp.dto.CommentDto;
import com.blogapp.exception.CommentNotFoundException;
import com.blogapp.exception.PostNotFoundException;


public interface CommentService {
    
	public CommentDto createComment(long postId, CommentDto commentDto) throws PostNotFoundException;

    public List<CommentDto> getCommentsByPostId(long postId);

    public CommentDto getCommentById(Long postId, Long commentId) throws CommentNotFoundException, PostNotFoundException;

    public CommentDto updateComment(Long postId, long commentId, CommentDto commentRequest) throws CommentNotFoundException, PostNotFoundException;

    public void deleteComment(Long postId, Long commentId) throws CommentNotFoundException, PostNotFoundException;
}
