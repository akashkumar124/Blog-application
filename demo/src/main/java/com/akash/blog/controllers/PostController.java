package com.akash.blog.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.akash.blog.config.AppConstant;
import com.akash.blog.payloads.ApiResonse;
import com.akash.blog.payloads.PostDto;
import com.akash.blog.payloads.PostResponse;
import com.akash.blog.services.FileService;
import com.akash.blog.services.PostService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
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
			@RequestParam(value="pageNumber",defaultValue=AppConstant.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue=AppConstant.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value="sortBy",defaultValue=AppConstant.SORT_BY, required = false) String sortBy,
			@RequestParam(value="sortDir",defaultValue=AppConstant.SORT_DIR, required = false) String sortDir
			){
		
		PostResponse allPost = this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
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
	
	@GetMapping("/posts/serch/{keyword}")
	public ResponseEntity<List<PostDto>> serchPostByTitle(@PathVariable String keyword){
		List<PostDto> posts=this.postService.serchPostBykeyword(keyword);
		
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}
	
	
//	post image upload
	
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadImage(
			@RequestParam("image") MultipartFile image,
			@PathVariable Integer postId
			) throws IOException{
		
		
		PostDto postdto=this.postService.getPostByPostId(postId);
		String fileName=this.fileService.uploadImage(path, image);
		
		
		postdto.setPImgName(fileName);
		PostDto updatetPost=this.postService.updatePost(postdto, postId);
		
		return new ResponseEntity<PostDto>(updatetPost,HttpStatus.OK);
		
	}
	
//	method for getting images
	
	@GetMapping(value = "/post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void dwonlaodImage(@PathVariable String imageName, HttpServletResponse response) throws IOException {
	
		
		InputStream resource = this.fileService.getResource(path, imageName);
		
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		
		StreamUtils.copy(resource, response.getOutputStream());
	}
	
	
	
	
	

}
