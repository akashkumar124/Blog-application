package com.akash.blog.services.impl;

import javax.xml.stream.events.Comment;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akash.blog.entity.Comments;
import com.akash.blog.entity.Post;
import com.akash.blog.exception.ResourceNotFoundException;
import com.akash.blog.payloads.CommentDto;
import com.akash.blog.repositories.CommentRepo;
import com.akash.blog.repositories.PostRepo;
import com.akash.blog.services.CommentServices;


@Service
public class CommentServiceImpl implements CommentServices{
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		// TODO Auto-generated method stub
		
		Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post", "Post ID", postId));
		
		Comments comment=this.modelMapper.map(commentDto, Comments.class);
		
		comment.setPost(post);
		
		Comments savedComment=this.commentRepo.save(comment);
		
		return this.modelMapper.map(savedComment, CommentDto.class);
		
		
		
	}

	@Override
	public void deleteComment(Integer commentId) {
		// TODO Auto-generated method stub
		
		Comments comment=this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("commentId", "commentId", commentId));
		
		this.commentRepo.delete(comment);
	}

}
