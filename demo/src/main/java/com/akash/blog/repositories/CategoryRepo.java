package com.akash.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akash.blog.entity.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
