package com.redbox.pkdm.services;

import java.util.List;

import com.redbox.pkdm.entities.Book;
import com.redbox.pkdm.entities.BookSection;

public interface BookSectionService {
	
	void save(BookSection bookSection, String bookId);
	
	void delete(BookSection bookSection);

	BookSection findByID(long id);
	
	List<BookSection> findByErase(boolean erase);
	
	List<BookSection> findByBookType(String bookType);
	
	List<BookSection> findByBookId(String bookId, boolean erase);
	
	List<Book> findBooks(boolean erase);
	
	List<BookSection> findBookSectionsByBookId();
	



}
