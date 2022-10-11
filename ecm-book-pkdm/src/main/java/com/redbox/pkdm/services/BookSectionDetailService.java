package com.redbox.pkdm.services;

import java.util.List;

import com.redbox.pkdm.entities.Book;
import com.redbox.pkdm.entities.BookSection;
import com.redbox.pkdm.entities.BookSectionDetail;

public interface BookSectionDetailService {
	
	void save(BookSectionDetail bookSectionDetail, long Id);
	
	void delete(BookSectionDetail bookSectionDetail);

	BookSectionDetail findByID(long id);
	
	List<BookSectionDetail> findByErase(boolean erase);

	List<Book> findBooks(boolean erase);
	
	List<BookSection> findBookSectionsByBookType(boolean erase, String bookType);
	
	List<BookSectionDetail> findByBookId(boolean erase, String bookId);
	
	List<BookSectionDetail> findByBookSectionId(boolean erase, long bookSectionId);
	
	List<BookSectionDetail> findByBookIdAndBookSectionId(boolean erase,String bookId, long bookSectionId);




}
