package com.redbox.pkdm.entities;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ShelfCategoryMapper {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	private ShelfCategory shelfCategory;

	@ManyToOne
	private Book book;
	
	private boolean erase;
	
	@Embedded
	private SecurityInfo securityInfo;
	
	public ShelfCategoryMapper() {
		
	}

	public ShelfCategoryMapper(long id, ShelfCategory shelfCategory, Book book, boolean erase,
			SecurityInfo securityInfo) {
		super();
		this.id = id;
		this.shelfCategory = shelfCategory;
		this.book = book;
		this.erase = erase;
		this.securityInfo = securityInfo;
	}
	
	

	public ShelfCategoryMapper(ShelfCategory shelfCategory, Book book) {
		super();
		this.shelfCategory = shelfCategory;
		this.book = book;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ShelfCategory getShelfCategory() {
		return shelfCategory;
	}

	public void setShelfCategory(ShelfCategory shelfCategory) {
		this.shelfCategory = shelfCategory;
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
