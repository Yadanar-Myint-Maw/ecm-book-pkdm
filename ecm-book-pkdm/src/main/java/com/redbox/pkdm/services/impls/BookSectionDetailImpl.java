package com.redbox.pkdm.services.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redbox.pkdm.entities.Book;
import com.redbox.pkdm.entities.BookSection;
import com.redbox.pkdm.entities.BookSectionDetail;
import com.redbox.pkdm.repositories.BookSectionDetailRepository;
import com.redbox.pkdm.services.BookSectionDetailService;
import com.redbox.pkdm.services.BookSectionService;

@Service
public class BookSectionDetailImpl implements BookSectionDetailService{
	
	@Autowired
	private BookSectionDetailRepository repository;
	
	@Autowired
	private BookSectionService bookSectionService;

	@Override
	public void save(BookSectionDetail bookSectionDetail, long id) {
		bookSectionDetail.setBookSection(bookSectionService.findByID(id));
		repository.save(bookSectionDetail);
	}

	@Override
	public void delete(BookSectionDetail bookSectionDetail) {
		bookSectionDetail.setErase(true);
		repository.save(bookSectionDetail);
	}

	@Override
	public BookSectionDetail findByID(long id) {
		return repository.findById(id).get();
	}

	@Override
	public List<BookSectionDetail> findByErase(boolean erase) {
		return repository.findByErase(erase);
	}

	@Override
	public List<Book> findBooks(boolean erase) {
		return repository.findBooks(erase);
	}

	@Override
	public List<BookSection> findBookSectionsByBookType(boolean erase, String bookType) {
		return repository.findBookSectionsByBookType(erase, bookType);
	}

	@Override
	public List<BookSectionDetail> findByBookId(boolean erase, String bookId) {
		return repository.findByBookId(erase, bookId);
	}

	@Override
	public List<BookSectionDetail> findByBookSectionId(boolean erase, long bookSectionId) {
		return repository.findByBookSectionId(erase, bookSectionId);
	}

	@Override
	public List<BookSectionDetail> findByBookIdAndBookSectionId(boolean erase, String bookId, long bookSectionId) {
		return repository.findByBookIdAndBookSectionId(erase, bookId, bookSectionId);
	}

}
