package com.akash.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.akash.blog.entity.Category;
import com.akash.blog.entity.Post;
import com.akash.blog.entity.User;
import com.akash.blog.exception.ResourceNotFoundException;
import com.akash.blog.payloads.PostDto;
import com.akash.blog.payloads.PostResponse;
import com.akash.blog.repositories.CategoryRepo;
import com.akash.blog.repositories.PostRepo;
import com.akash.blog.repositories.UserRepo;
import com.akash.blog.services.PostService;

@Service
public class PostServiceImppl implements PostService{
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "User not Found", userId));
		
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Caegory","Category not found",categoryId));
		
		Post post=this.modelMapper.map(postDto, Post.class);
		
		post.setPImgName("defaut.png");
//		post.setPContent(postDto.getPContent());
//		post.setPTitle(postDto.getPTitle());
		post.setPDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post newPost=this.postRepo.save(post);
		
		
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		// TODO Auto-generated method stub
		
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId", postId));
		
		post.setPTitle(postDto.getPTitle());
		post.setPContent(postDto.getPContent());
		post.setPImgName(postDto.getPImgName());
		
		Post updatePost=this.postRepo.save(post);
		return this.modelMapper.map(updatePost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		// TODO Auto-generated method stub
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId", postId));
		
		this.postRepo.delete(post);
		
	}

	@Override
	public PostDto getPostByPostId(Integer postId) {
		// TODO Auto-generated method stub
		
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId", postId));
		
		return this.modelMapper.map(post, PostDto.class);
		
	}

	@Override
	public List<PostDto> serchPostBykeyword(String keyword) {
		// TODO Auto-generated method stub
//		List<Post> posts=this.postRepo.findBypTitleContaining(keyword);
		
		List<Post> posts=this.postRepo.serachByPostTitle("%"+keyword+"%");
		
		List<PostDto> allPost=posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return allPost;
	}

	@Override
	public PostResponse getAllPost(Integer pagenumber, Integer pageSize, String sortBy,String sortDir) {
		// TODO Auto-generated method stub
		
		Sort sort = null;
		
		if(sortDir.equalsIgnoreCase("asc")) {
			sort=Sort.by(sortBy).ascending();
		}else {
			sort=Sort.by(sortBy).descending();
		}
		
		Pageable p=PageRequest.of(pagenumber, pageSize, sort);
		
		
		Page<Post> pagePost=this.postRepo.findAll(p);
		
		List<Post> allPost=pagePost.getContent();
		
		List<PostDto> allDto=allPost.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		
		postResponse.setContent(allDto);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElement(pagePost.getTotalElements());
		
		postResponse.setTotalPages(pagePost.getTotalPages());
		
		postResponse.setLastPage(pagePost.isLast());
		
		
		return postResponse;
	}

	@Override
	public List<PostDto> getPostByUserId(Integer userId) {
		// TODO Auto-generated method stub
       User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","User Id",userId));
		
		List<Post> uposts=this.postRepo.findByUser(user);
		   
		List<PostDto> collect=uposts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
//		System.out.println(collect);
		
		return collect;
		
	}

	@Override
	public List<PostDto> getPostByCategoryId(Integer categoryId) {
		// TODO Auto-generated method stub
		
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));
		
		List<Post> cposts=this.postRepo.findByCategory(cat);
		   
		List<PostDto> collect=cposts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
//		System.out.println(collect);
		
		
		return collect;
	}

}
