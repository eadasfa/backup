package com.book.service;

import com.book.entity.Login;
import com.book.entity.User;

public interface OperateUserTable {
	boolean checkLogin(Login login);
	boolean addUser(User user);
}
