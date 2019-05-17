package com.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.book.entity.Book;
import com.book.service.OperateBookTable;



@Controller
@RequestMapping("/home")
public class HomeController {
	@Autowired
	@Qualifier("bookService")
	private OperateBookTable operateDatabase;
	@RequestMapping(method = RequestMethod.GET)
	public String home(Model model){
		System.out.println("home");
		List<Book> books = operateDatabase.findAllBooks();
//		List<Book> books = operateDatabase.findBookByTitle("Math");
//		for(Book book: books){
//			System.out.println(book);
//		}
		model.addAttribute("books", books);
//		System.out.println(model.containsAttribute("books"));
		return "home";
	}
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public String delete(
			@PathVariable long id){
		System.out.println(id);
		operateDatabase.deleteBookById(id);
		return "redirect:/home";
	}
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(Book book){
		
		book.setId(operateDatabase.maxIdOfBook()+1);
		book.changeEncoding();
		operateDatabase.addBook(book);
		return "redirect:/home";
	}
	@RequestMapping(value="/{findBy}/{keyword}",method=RequestMethod.GET)
	public String query(
			@PathVariable String findBy,
			@PathVariable String keyword,
			Model model){
		
		List<Book> books=null;
		if("title".equals(findBy)){
			books = operateDatabase.findBookByTitle(keyword);
		}else if("id".equals(findBy)){
			books = operateDatabase.findBookById(Long.parseLong(keyword));
		}else{
			books = operateDatabase.findBookByAuthor(keyword);
		}
		model.addAttribute("books", books);
		return "home";
	}
	@RequestMapping(value="/modify",method=RequestMethod.POST)
	public String modify(Book book){
		book.changeEncoding();
		System.out.println(book);
		
		operateDatabase.updateBook(book);
		return "redirect:/home";
	}
	
	
}
