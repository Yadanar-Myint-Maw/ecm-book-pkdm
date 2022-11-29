package com.redbox.pkdm.models;

public class MyBookModel {
	
	private String bookID;
	
	private String bookType;
	
	private String address;
	
	public MyBookModel () {
		
	}
	
	public MyBookModel (String bookID, String bookType) {
		this.bookID = bookID;
		this.bookType = bookType;
	}

	public String getBookID() {
		return bookID;
	}

	public void setBookID(String bookID) {
		this.bookID = bookID;
	}

	public String getBookType() {
		return bookType;
	}

	public void setBookType(String bookType) {
		this.bookType = bookType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	

}
