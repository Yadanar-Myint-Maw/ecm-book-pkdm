package com.redbox.pkdm.models;

public class MyBookModel {
	
	private String bookID;
	
	private boolean bookType;
	
	private String address;
	
	public MyBookModel () {
		
	}
	
	public MyBookModel(String bookID, boolean bookType, String address) {
		super();
		this.bookID = bookID;
		this.bookType = bookType;
		this.address = address;
	}

	public String getBookID() {
		return bookID;
	}

	public void setBookID(String bookID) {
		this.bookID = bookID;
	}

	
	public boolean isBookType() {
		return bookType;
	}


	public void setBookType(boolean bookType) {
		this.bookType = bookType;
	}


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	

}
