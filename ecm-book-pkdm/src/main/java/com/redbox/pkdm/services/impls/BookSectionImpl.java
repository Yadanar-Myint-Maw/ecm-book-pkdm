package com.redbox.pkdm.services.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redbox.pkdm.entities.Book;
import com.redbox.pkdm.entities.BookSection;
import com.redbox.pkdm.repositories.BookSectionRepository;
import com.redbox.pkdm.services.BookSectionService;
import com.redbox.pkdm.services.BookService;

@Service
public class BookSectionImpl implements BookSectionService{
	
	@Autowired
	private BookSectionRepository repository;
	
	@Autowired
	private BookService bookService;

	@Override
	public void save(BookSection bookSection, String bookId) {
		bookSection.setBook(bookService.findByID(bookId));
		repository.save(bookSection);
	}

	@Override
	public void delete(BookSection bookSection) {
		bookSection.setErase(true);
		repository.save(bookSection);
	}

	@Override
	public BookSection findByID(long id) {
		return repository.findById(id).get();
	}

	@Override
	public List<BookSection> findByErase(boolean erase) {
		return repository.findByErase(erase);
	}

	@Override
	public List<BookSection> findByBookId(String bookId, boolean erase) {
		return repository.findByBookId(bookId, erase);
	}

	@Override
	public List<Book> findBooks(boolean erase) {
		return repository.findBooks(erase);
	}

	@Override
	public List<BookSection> findBookSectionsByBookId() {
		return repository.findBookSectionsByBookId();
	}

	@Override
	public List<BookSection> findByBookType(String bookType) {
		return repository.findByBookType(bookType);
	}

	
	
	
	

}
