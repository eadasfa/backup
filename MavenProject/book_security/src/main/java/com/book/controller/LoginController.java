package com.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.book.entity.Login;
import com.book.entity.User;
import com.book.service.DataService;
import com.book.service.OperateBookTable;
import com.book.service.OperateUserTable;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	@Qualifier("loginService")
	private OperateUserTable loginCheck;

	@RequestMapping(method=RequestMethod.POST)
	public String loginPost(Login login){
		if(loginCheck.checkLogin(login)){
			//登录成功
			return "redirect:/home";
		}else{
			return "login";
		}
	}
	@RequestMapping(method=RequestMethod.GET)
	public String login(Login login){
		System.out.println("login");
		return "login";
	}
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public String register(){
		return "register";
	}
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String registerPost(User user){
		System.out.println(user);
		if(loginCheck.addUser(user)){
			return "redirect:/login";
		}
		return "register";
	}
}
