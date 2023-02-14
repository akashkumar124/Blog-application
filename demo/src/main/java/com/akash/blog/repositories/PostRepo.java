package com.akash.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akash.blog.entity.Category;
import com.akash.blog.entity.Post;
import com.akash.blog.entity.User;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer>{
	
	
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category user);
	
//	List<Post> findByTitleContaining(String title);

}
