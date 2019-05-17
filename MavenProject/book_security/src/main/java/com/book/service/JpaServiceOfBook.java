package com.book.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.book.dao.*;
import com.book.entity.*;

@Service("bookService")
public class JpaServiceOfBook implements OperateBookTable{

	@Resource
	private BookRepository bookDao;
	
//	@PersistenceUnit
//	private EntityManagerFactory emf;
//	
//	private EntityManager em;
//	private void createEntityManager(){
//		if(this.em==null){
//			em = this.emf.createEntityManager();
//		}
//	}
	
	public boolean updateBook(Book book) {
		// TODO Auto-generated method stub
		bookDao.save(book);
		return true;
	}

	public List<Book> findAllBooks() {

		List<Book> books= bookDao.findAll();
		System.out.println(books);
		return books;
	}

	public boolean addBook(Book book) {
		System.out.println("Enter save()");
		bookDao.save(book);
		System.out.println(book);
		System.out.println("Exit save()");
		return true;
	}

	public List<Book> findBookById(long id) {
		List<Book> books = new ArrayList<Book>();
		books.add(bookDao.findOne(id));
		return books;
	}

	public List<Book> findBookByTitle(String title) {
		// TODO Auto-generated method stub
		
		return bookDao.findByTitle(title);
	}

	public List<Book> findBookByAuthor(String author) {
		// TODO Auto-generated method stub
		return bookDao.findByAuthor(author);
	}

	
	public long maxIdOfBook() {
		return bookDao.findTopByOrderByIdDesc().getId();
	}

	public boolean deleteBookById(long id) {
		bookDao.delete(id);;
		return true;
	}

}
