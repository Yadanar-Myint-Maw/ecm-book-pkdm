package com.redbox.pkdm.models;

import java.util.List;

import com.redbox.pkdm.entities.Book;
import com.redbox.pkdm.entities.BookSection;
import com.redbox.pkdm.entities.BookSectionDetail;

public class BookInfoModel {

private Book book;
	
	private List<BookSection> bookSections;
	
	private List<BookSectionDetail> bookSectionDetails;
	

	public BookInfoModel(Book book, List<BookSection> bookSections, List<BookSectionDetail> bookSectionDetails) {
		super();
		this.book = book;
		this.bookSections = bookSections;
		this.bookSectionDetails = bookSectionDetails;
	}

	public BookInfoModel () {
		
	}

	public BookInfoModel(Book book, List<BookSection> bookSections) {
		super();
		this.book = book;
		this.bookSections = bookSections;
	}


	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public List<BookSection> getBookSections() {
		return bookSections;
	}

	public void setBookSections(List<BookSection> bookSections) {
		this.bookSections = bookSections;
	}

	public List<BookSectionDetail> getBookSectionDetails() {
		return bookSectionDetails;
	}

	public void setBookSectionDetails(List<BookSectionDetail> bookSectionDetails) {
		this.bookSectionDetails = bookSectionDetails;
	}
	
}
