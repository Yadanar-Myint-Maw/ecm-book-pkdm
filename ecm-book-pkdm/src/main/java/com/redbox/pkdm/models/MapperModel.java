package com.redbox.pkdm.models;

import java.util.List;

public class MapperModel {
	
	private String name;
	
	private String bookType;
	
	private String bookID;
	
	private List<String> bookSectionID;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBookType() {
		return bookType;
	}

	public void setBookType(String bookType) {
		this.bookType = bookType;
	}

	public String getBookID() {
		return bookID;
	}

	public void setBookID(String bookID) {
		this.bookID = bookID;
	}

	public List<String> getBookSectionID() {
		return bookSectionID;
	}

	public void setBookSectionID(List<String> bookSectionID) {
		this.bookSectionID = bookSectionID;
	}

}
