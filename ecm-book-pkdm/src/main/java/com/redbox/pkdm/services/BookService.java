package com.redbox.pkdm.services;

import java.util.List;

import com.redbox.pkdm.entities.Book;

public interface BookService {
	
	void save(Book book);
	
	void delete(Book book);
	
	Book findByID(String id);
	
	List<Book> findByErase(boolean erase);
	
	List<Book> findByBookType(String bookType);
	
	List<Book> findByEBook();
	
	List<Book> findByPBook();
	
	String findByLast();
	
	List<Book> findByNameLikeAndBookType(String name, String bookType);
	
	long findByCountEBook(String bookType);
	
	long findByCountPBook(String bookType);

}
