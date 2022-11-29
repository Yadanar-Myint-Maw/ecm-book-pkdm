package com.redbox.pkdm.models;

import java.util.ArrayList;
import java.util.List;

public class ShelfModel {
	
	private String name;
	
	private List<String> books;
	
	public ShelfModel () {
		books = new ArrayList<>();
	}
	
	public ShelfModel (String name, List<String> books) {
		this.name = name;
		this.books = books;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getBooks() {
		return books;
	}

	public void setBooks(List<String> books) {
		this.books = books;
	}


}
