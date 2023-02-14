package com.akash.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.akash.blog.payloads.ApiResonse;
import com.akash.blog.payloads.PostDto;
import com.akash.blog.payloads.PostResponse;
import com.akash.blog.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@PostMapping("/user/{userId}/categories/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(
			@RequestBody PostDto postDto, @PathVariable Integer userId,@PathVariable Integer categoryId){
		
		PostDto createPost=this.postService.createPost(postDto, userId, categoryId);
		
		return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED); 
		
		
	}
	
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
		
		
		List<PostDto> post=this.postService.getPostByUserId(userId);
		
		return new ResponseEntity<List<PostDto>>(post,HttpStatus.OK);
		
	}
	
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId){
		
		
		List<PostDto> post=this.postService.getPostByCategoryId(categoryId);
		
		return new ResponseEntity<List<PostDto>>(post,HttpStatus.OK);
		
	}
	
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value="pageNumber",defaultValue="0", required = false) Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue="1", required = false) Integer pageSize
			){
		
		PostResponse allPost = this.postService.getAllPost(pageNumber,pageSize);
		return new ResponseEntity<PostResponse>(allPost,HttpStatus.OK);
	}
	
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
		
		PostDto posts=this.postService.getPostByPostId(postId);
		return new ResponseEntity<PostDto>(posts,HttpStatus.OK);
	}
	
	@DeleteMapping("/posts/{postId}")
	public ApiResonse deletPostById(@PathVariable Integer postId){
		
		this.postService.deletePost(postId);
		return new ApiResonse("Post is succesfull Deleted", true);
	}
	
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePostById(@RequestBody PostDto postdto,@PathVariable Integer postId){
		
		PostDto post=this.postService.updatePost(postdto, postId);
		return new ResponseEntity<PostDto>(post,HttpStatus.OK);
	}

}
