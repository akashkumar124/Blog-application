package com.akash.blog.payloads;

import java.util.Date;

import com.akash.blog.entity.Category;
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
	

}
