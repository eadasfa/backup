package com.book.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.swing.tree.TreePath;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;


import com.book.entity.*;

@Component("dataService")
public class DataService implements OperateUserTable,OperateBookTable{

	private String ADD_USER="INSERT INTO USER(username,password,sex,fullname) VALUES (?,?,?,?)";
	private String ADD_BOOK="INSERT INTO BOOK(id,title,author,page,price) VALUES (?,?,?,?,?)";
	private String FIND_USER_BY_USERNAME="select username,password from user where username=? and password=?";
	private String FIND_ALL_BOOK="SELECT id,title,author,page,price "
			+ "FROM book " ;
	private String DELETE_BY_ID="DELETE FROM BOOK WHERE id=?";
	private String FIND_MAX_ID="SELECT max(ID) FROM BOOK";
	private String UPDATE_BOOK="UPDATE book SET title=?, author=?,"
			+ " page=?, price=? WHERE id=?;";
	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcOperations jdbcOperations;
	
	public boolean checkLogin(Login login) {
		User user = null;
		try {
			user = jdbcOperations.queryForObject(FIND_USER_BY_USERNAME,
					new UserRowMapper(),login.getUsername(),login.getPassword());
		} catch (EmptyResultDataAccessException e) {
			user = null;
		}
		if(user!=null)
			return true;
		else 
			return false;
	}
	public boolean updateBook(Book book){
		return 1==jdbcOperations.update(UPDATE_BOOK, book.getTitle(),book.getAuthor(),book.getPage(),
				book.getPrice(),book.getId());
	}
	public boolean addUser(User user){
		return 1==jdbcOperations.update(ADD_USER,user.getUsername(),
				user.getPassword(),user.getSex(),user.getFullname());
	}
	public List<Book> findAllBooks(){
		return findBook("1=1", new String[]{});
	}
	public boolean addBook(Book book){
		return 1==jdbcOperations.update(ADD_BOOK, book.getId(),book.getTitle(),
				book.getAuthor(),book.getPage(),book.getPrice());
	}
	public List<Book> findBookById(long id){
		
		return findBook("id=?", new String[]{id+""});
	
	}
	public List<Book> findBookByTitle(String title){
		return findBook("title=?", new String[]{title});
	}
	public List<Book> findBookByAuthor(String author){
		return findBook("author=?", new String[]{author});
	}
	public List<Book> findBook(String where,String[] para){
		return jdbcOperations.query(FIND_ALL_BOOK+" WHERE "+where, 
				para,new BookRowMapper());
	}
	public long maxIdOfBook(){
		return jdbcOperations.queryForObject(FIND_MAX_ID, Long.class);
	}
	public boolean deleteBookById(long id){
		return 1==jdbcOperations.update(DELETE_BY_ID, id);
	}
	
	private static final class UserRowMapper implements RowMapper<User>{

		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user=new User();
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			return user;
		}
		
	}
	private static final class BookRowMapper implements RowMapper<Book>{

		public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
			Book book = new Book(rs.getLong("id"),rs.getString("title"),
					rs.getString("author"),rs.getInt("page"),rs.getDouble("price"));
//			book.setId(rs.getLong("id"));
//			book.setTitle(rs.getString("title"));
//			book.setAuthor(rs.getString("author"));
//			book.setPrice(rs.getDouble("price"));
//			book.setPage(rs.getInt("page"));
			return book;
		}
		
	}
}
