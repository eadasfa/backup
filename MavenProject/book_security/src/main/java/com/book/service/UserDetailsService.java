package com.book.service;

import com.book.entity.UserDetails;

public interface UserDetailsService {
	UserDetails loadUserByUsername(String username);
}
