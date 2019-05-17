package com.book.service;

import javax.annotation.Resource;

  
import org.springframework.stereotype.Service;

import com.book.dao.UserRepsitory;
import com.book.entity.Login;
import com.book.entity.User;

@Service("loginService")
public class JpaServiceOfUser implements OperateUserTable{

	@Resource
	private UserRepsitory userDao;
	public boolean checkLogin(Login login) {
		// TODO Auto-generated method stub
		return null!=userDao.findByUsernameAndPassword(
				login.getUsername(), login.getPassword());
	}

	public boolean addUser(User user) {
		userDao.save(user);
		return true;
	}
	
}
