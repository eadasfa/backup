package com.book.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.book.entity.User;

@Repository
public interface UserRepsitory extends JpaRepository<User, String>{
	User findByUsernameAndPassword(String username,String password);
}
