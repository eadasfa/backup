package com.book.service;

import java.util.List;

import com.book.entity.Book;
import com.book.entity.User;

public interface OperateBookTable {

	boolean updateBook(Book book);
	public List<Book> findAllBooks();
	boolean addBook(Book book);
	List<Book> findBookById(long id);
	List<Book> findBookByTitle(String title);
	List<Book> findBookByAuthor(String author);
	long maxIdOfBook();
	boolean deleteBookById(long id);
}
