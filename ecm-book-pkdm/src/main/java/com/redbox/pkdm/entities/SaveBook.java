package com.redbox.pkdm.entities;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class SaveBook {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	private Book book;
	
	@ManyToOne
	private AccountUser accountUser;
	
	@Embedded
	private SecurityInfo securityInfo;
	
	public SaveBook() {
		
	}

	public SaveBook(long id, Book book, AccountUser accountUser, SecurityInfo securityInfo) {
		super();
		this.id = id;
		this.book = book;
		this.accountUser = accountUser;
		this.securityInfo = securityInfo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Book getBookInfo() {
		return book;
	}

	public void setBookInfo(Book book) {
		this.book = book;
	}

	public AccountUser getAccountUser() {
		return accountUser;
	}

	public void setAccountUser(AccountUser accountUser) {
		this.accountUser = accountUser;
	}

	public SecurityInfo getSecurityInfo() {
		return securityInfo;
	}

	public void setSecurityInfo(SecurityInfo securityInfo) {
		this.securityInfo = securityInfo;
	}
	
}
