package com.akash.blog.services;

import java.util.List;

import com.akash.blog.entity.Post;
import com.akash.blog.payloads.PostDto;
import com.akash.blog.payloads.PostResponse;

public interface PostService {
	
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	
	PostDto updatePost(PostDto postDto, Integer postId);
	
	void deletePost(Integer postId);
	
	PostDto getPostByPostId(Integer postId);
	
	List<Post> serchPostBykeyword(String keyword);
	
	PostResponse getAllPost(Integer pagenumber, Integer pageSize);
	
	List<PostDto> getPostByUserId(Integer userId);
	
	List<PostDto> getPostByCategoryId(Integer categoryId);
	
	
	
	

}
