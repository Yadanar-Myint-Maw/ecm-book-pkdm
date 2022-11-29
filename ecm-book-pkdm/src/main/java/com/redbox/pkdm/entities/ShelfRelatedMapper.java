package com.redbox.pkdm.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ShelfRelatedMapper {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	private ShelfRelated shelfRelated;
	
	@ManyToOne
	private Book book;
	
	private boolean erase;

	public ShelfRelatedMapper () {
		
	}
	
	public ShelfRelatedMapper (ShelfRelated shelfRelated, Book book) {
		this.shelfRelated = shelfRelated;
		this.book = book;
		this.erase = false;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ShelfRelated getShelfRelated() {
		return shelfRelated;
	}

	public void setShelfRelated(ShelfRelated shelfRelated) {
		this.shelfRelated = shelfRelated;
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

}
