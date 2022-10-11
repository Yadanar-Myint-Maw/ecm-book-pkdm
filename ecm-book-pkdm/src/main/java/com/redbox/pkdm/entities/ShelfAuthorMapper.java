package com.redbox.pkdm.entities;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ShelfAuthorMapper {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	private ShelfAuthor shelfAuthor;
	
	@ManyToOne
	private Book book;
	
	private boolean erase;
	
	@Embedded
	private SecurityInfo securityInfo;
	
	public ShelfAuthorMapper() {
		
	}

	public ShelfAuthorMapper(long id, ShelfAuthor shelfAuthor, Book book, boolean erase, SecurityInfo securityInfo) {
		super();
		this.id = id;
		this.shelfAuthor = shelfAuthor;
		this.book = book;
		this.erase = erase;
		this.securityInfo = securityInfo;
	}
	
	public ShelfAuthorMapper(ShelfAuthor shelfAuthor, Book book) {
		super();
		this.shelfAuthor = shelfAuthor;
		this.book = book;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ShelfAuthor getShelfAuthor() {
		return shelfAuthor;
	}

	public void setShelfAuthor(ShelfAuthor shelfAuthor) {
		this.shelfAuthor = shelfAuthor;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public boolean isErase() {
		return erase;
	}

	public void setErase(boolean erase) {
		this.erase = erase;
	}

	public SecurityInfo getSecurityInfo() {
		return securityInfo;
	}

	public void setSecurityInfo(SecurityInfo securityInfo) {
		this.securityInfo = securityInfo;
	}
	
}
