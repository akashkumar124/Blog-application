package com.akash.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.akash.blog.payloads.ApiResonse;
import com.akash.blog.payloads.CommentDto;

import com.akash.blog.services.CommentServices;

@RestController
@RequestMapping("/api/")
public class CommentController {
	
	@Autowired
	private CommentServices commentSerivces;
	
	
	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto comment, @PathVariable Integer postId){
		
		CommentDto cmDto=this.commentSerivces.createComment(comment, postId);
		
		return new ResponseEntity<CommentDto>(cmDto,HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<ApiResonse> deleteComment( @PathVariable Integer commentId){
		
		this.commentSerivces.deleteComment(commentId);
		
		return new ResponseEntity<ApiResonse>(new ApiResonse("COmments Deleted Succesfully", true),HttpStatus.OK);
		
	}

}
