package com.redbox.pkdm.models;

import java.util.ArrayList;
import java.util.List;

public class BookModel {
	
	public BookInformationModel information;
	
	public List<BookSectionModel> sections;
	
	public BookModel () {
		sections = new ArrayList<>();
	}

	public BookModel(BookInformationModel information, List<BookSectionModel> sections) {
		super();
		this.information = information;
		this.sections = sections;
	}

	public BookInformationModel getInformation() {
		return information;
	}

	public void setInformation(BookInformationModel information) {
		this.information = information;
	}

	public List<BookSectionModel> getSections() {
		return sections;
	}

	public void setSections(List<BookSectionModel> sections) {
		this.sections = sections;
	}
	
}

