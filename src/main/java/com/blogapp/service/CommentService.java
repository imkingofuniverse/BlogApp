package com.blogapp.service;

import java.util.List;

import com.blogapp.dto.CommentDto;


public interface CommentService {
    
	public CommentDto createComment(long postId, CommentDto commentDto);

    public List<CommentDto> getCommentsByPostId(long postId);

    public CommentDto getCommentById(Long postId, Long commentId);

    public CommentDto updateComment(Long postId, long commentId, CommentDto commentRequest);

    public void deleteComment(Long postId, Long commentId);
}
