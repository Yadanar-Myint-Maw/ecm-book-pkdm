package com.redbox.pkdm.models;

import java.util.ArrayList;
import java.util.List;

public class ShelfFeatureModel {
	
	private long id;
	
	private String name;
	
	private List<String> books;
	
	public ShelfFeatureModel () {
		books = new ArrayList<>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
