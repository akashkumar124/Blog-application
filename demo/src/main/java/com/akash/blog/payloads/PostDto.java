package com.akash.blog.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.akash.blog.entity.Category;
import com.akash.blog.entity.Comments;
import com.akash.blog.entity.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	
	
	private Integer postId;
	private String pTitle;
	
	
	private String pContent;
	private String pImgName;
	private Date pDate;
	
	
	private UserDto user;
	
	
	private CategoryDto category;
	
	
	private Set<CommentDto> comments = new HashSet<>();
	

}
