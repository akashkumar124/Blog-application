package com.akash.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akash.blog.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{

}
