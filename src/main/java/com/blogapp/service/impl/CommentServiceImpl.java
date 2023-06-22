package com.blogapp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapp.dto.CommentDto;
import com.blogapp.entity.Comment;
import com.blogapp.entity.Post;
import com.blogapp.exception.CommentNotFoundException;
import com.blogapp.exception.PostNotFoundException;
import com.blogapp.repository.CommentRepository;
import com.blogapp.repository.PostRepository;
import com.blogapp.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
    private CommentRepository commentRepository;
	
	@Autowired
    private PostRepository postRepository;
	
	@Autowired
    private ModelMapper mapper;

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) throws PostNotFoundException {

        Comment comment = mapToEntity(commentDto);

     // retrieve post entity by id
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new PostNotFoundException("Post not found for post ID: " + postId));

        // set post to comment entity
        comment.setPost(post);

        // comment entity to DB
        Comment newComment =  commentRepository.save(comment);

        return mapToDTO(newComment);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        // retrieve comments by postId
        List<Comment> comments = commentRepository.findByPostId(postId);

        // convert list of comment entities to list of comment dto's
        return comments.stream().map(comment -> mapToDTO(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) throws CommentNotFoundException, PostNotFoundException {
    	// retrieve post entity by id
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new PostNotFoundException("Post not found for post ID: " + postId));

        // retrieve comment by id
        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new CommentNotFoundException("Comment not found for comment ID: " + commentId));

        return mapToDTO(comment);
    }

    @Override
    public CommentDto updateComment(Long postId, long commentId, CommentDto commentRequest) throws CommentNotFoundException, PostNotFoundException {
    	// retrieve post entity by id
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new PostNotFoundException("Post not found for post ID: " + postId));

        // retrieve comment by id
        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new CommentNotFoundException("Comment not found for comment ID: " + commentId));
        

        comment.setName(commentRequest.getName());
        comment.setEmail(commentRequest.getEmail());
        comment.setContent(commentRequest.getContent());

        Comment updatedComment = commentRepository.save(comment);
        return mapToDTO(updatedComment);
    }

    @Override
    public void deleteComment(Long postId, Long commentId) throws CommentNotFoundException, PostNotFoundException {
        // retrieve post entity by id
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new PostNotFoundException("Post not found for post ID: " + postId));

        // retrieve comment by id
        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new CommentNotFoundException("Comment not found for comment ID: " + commentId));

        commentRepository.delete(comment);
    }
        
    private CommentDto mapToDTO(Comment comment){
            CommentDto commentDto = mapper.map(comment, CommentDto.class);

            return  commentDto;
    }

    private Comment mapToEntity(CommentDto commentDto){
            Comment comment = mapper.map(commentDto, Comment.class);
            
            return  comment;
    }
        

}
