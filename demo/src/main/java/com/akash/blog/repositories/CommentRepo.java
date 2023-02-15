package com.akash.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akash.blog.entity.Comments;

public interface CommentRepo extends JpaRepository<Comments, Integer>{

}
