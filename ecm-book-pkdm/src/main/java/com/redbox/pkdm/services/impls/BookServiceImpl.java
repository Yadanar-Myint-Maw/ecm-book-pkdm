package com.redbox.pkdm.services.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redbox.pkdm.entities.Book;
import com.redbox.pkdm.repositories.BookRepository;
import com.redbox.pkdm.services.BookService;
import com.redbox.pkdm.utilities.IDGeneratorUtility;

@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	private BookRepository repository;

	@Override
	public void save(Book book) {
		if (book.getId().isEmpty()) {
			book.setId(IDGeneratorUtility.generate(findByLast()));		
		}
		repository.save(book);
	}

	@Override
	public Book findByID(String id) {
		return repository.findById(id).get();
	}

	@Override
	public String findByLast() {
		return repository.findByLast();
	}

	@Override
	public void delete(Book book) {
		book.setErase(true);
		repository.save(book);
	}

	@Override
	public List<Book> findByErase(boolean erase) {
		return repository.findByErase(false);
	}

	@Override
	public List<Book> findByBookType(String bookType) {
		return repository.findbyBookType(bookType);
	}

	@Override
	public List<Book> findByNameLikeAndBookType(String name, String bookType) {
		return repository.findByNameLikeAndBookType(name, bookType);
	}

	@Override
	public List<Book> findByEBook() {
		return repository.findbyEBook();
	}

	@Override
	public List<Book> findByPBook() {
		return repository.findbyPBook();
	}

}
