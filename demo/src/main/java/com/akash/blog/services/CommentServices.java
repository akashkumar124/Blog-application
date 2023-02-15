package com.akash.blog.services;

import com.akash.blog.payloads.CommentDto;

public interface CommentServices {
	
	CommentDto createComment(CommentDto commentDto, Integer postId);
	
	void deleteComment(Integer commentId);

}
