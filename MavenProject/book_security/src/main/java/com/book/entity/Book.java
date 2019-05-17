package com.book.entity;

import java.io.UnsupportedEncodingException;

import javax.persistence.*;

@Entity
@Table(name="book")
public class Book {
	@Id
	@Column(name="id")
	private Long id;
	@Column(name="title")
	private String title;
	@Column(name="author")
	private String author;
	@Column(name="page")
	private int page;
	@Column(name="price")
	private double price;
	
	public Book(long id, String title,String author,int page,double price){
		this.id = id;
		this.title = title;
		this.author = author;
		this.page = page;
		this.price = price;
	}
	public Book(String title,String author,int page,double price){
			
			this(0,title,author,page,price);
	}
	public Book(){
		this.id = 0L;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
//		this.title = changeEncodingToUtf8(title);
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
//		this.author = changeEncodingToUtf8(author);
		this.author = author;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", page=" + page + ", price=" + price
				+ "]";
	}
	public void changeEncoding(){
		try {
			this.title = new String(this.title.getBytes("iso8859-1"),"utf-8");
			this.author = new String(this.author.getBytes("iso8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private String getEncoding(String str){
		
		String encode="utf-8";
		try{
			if(str.equals(new String(str.getBytes(),encode))){
				return encode;
			}
		}catch(Exception e){}
		encode="gbk";
		try{
			if(str.equals(new String(str.getBytes(),encode))){
				return encode;
			}
		}catch(Exception e){}
		encode="iso-8859-1";
		try{
			if(str.equals(new String(str.getBytes(),encode))){
				return encode;
			}
		}catch(Exception e){}
		return null;		
	}
	private String changeEncoding(String str,String encoding){
		try {
			String encodingNow = getEncoding(str);
			System.out.println(encodingNow);
			if(!encoding.equals(encodingNow))
				return new String(str.getBytes(encodingNow),encoding);
			return str;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	private String changeEncodingToUtf8(String str){
		return changeEncoding(str,"utf-8");
	}
	
}
