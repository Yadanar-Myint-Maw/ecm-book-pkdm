package com.redbox.pkdm.entities;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ShelfFeatureMapper {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private boolean erase;
	
	@ManyToOne
	private ShelfFeature shelfFeature;
	
	@ManyToOne
	private Book book;
	
	@Embedded
	private SecurityInfo securityInfo;
	
	public ShelfFeatureMapper() {
		
	}

	public ShelfFeatureMapper(long id, boolean erase, ShelfFeature shelfFeature, Book book, SecurityInfo securityInfo) {
		super();
		this.id = id;
		this.erase = erase;
		this.shelfFeature = shelfFeature;
		this.book = book;
		this.securityInfo = securityInfo;
	}
	
	public ShelfFeatureMapper(ShelfFeature shelfFeature, Book book) {
		super();
		this.shelfFeature = shelfFeature;
		this.book = book;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public ShelfFeature getShelfFeature() {
		return shelfFeature;
	}

	public void setShelfFeature(ShelfFeature shelfFeature) {
		this.shelfFeature = shelfFeature;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
	
	
	
}
