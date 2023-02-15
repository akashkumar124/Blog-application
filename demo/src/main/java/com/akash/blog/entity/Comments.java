package com.akash.blog.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter

public class Comments {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cId;
	
	private String content;
	
	@ManyToOne
	private Post post;

}
