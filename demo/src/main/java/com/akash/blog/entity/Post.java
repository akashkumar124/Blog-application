package com.akash.blog.entity;

import java.util.Date;

import org.hibernate.annotations.ManyToAny;

import com.akash.blog.payloads.CategoryDto;
import com.akash.blog.payloads.UserDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postId;
	
	
	private String pTitle;
	
	
	private String pContent;
	private String pImgName;
	private Date pDate;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Category category;

}
