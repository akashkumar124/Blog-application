package com.akash.blog.payloads;


import org.hibernate.validator.constraints.Email;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private Integer id;
	
	@NotBlank
	@Size(min=4,message="username not emty")
	private String name;
	@Email(message="Email not emty")
	private String email;
	@NotBlank
	@Size(min=3,max=10,message="masseage fiend not ematy")
	private String password;
	@NotBlank
	private String about;
	

}
