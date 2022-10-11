package com.redbox.pkdm.models;

public class BookModel {
	
	private String id;
	
	private String image;
	
	private String translatorImage;
	
	private String name;
	
	private String description;
	
	private String trailer;
	
	private String foreword;
	
	private String bookType;
	
	private String author;
	
	private String feature;
	
	private String category;
	
	private boolean electronicBook;
	
	private boolean physicalBook;
	
	private double price;
	
	public BookModel() {
		
	}

	public BookModel(String id, String image, String translatorImage, String name, String description, String trailer,
			String foreword, String bookType, boolean electronicBook, boolean physicalBook, double price) {
		super();
		this.id = id;
		this.image = image;
		this.translatorImage = translatorImage;
		this.name = name;
		this.description = description;
		this.trailer = trailer;
		this.foreword = foreword;
		this.bookType = bookType;
		this.electronicBook = electronicBook;
		this.physicalBook = physicalBook;
		this.price = price;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getTranslatorImage() {
		return translatorImage;
	}

	public void setTranslatorImage(String translatorImage) {
		this.translatorImage = translatorImage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTrailer() {
		return trailer;
	}

	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}

	public String getForeword() {
		return foreword;
	}

	public void setForeword(String foreword) {
		this.foreword = foreword;
	}

	public String getBookType() {
		return bookType;
	}

	public void setBookType(String bookType) {
		this.bookType = bookType;
	}

	public boolean isElectronicBook() {
		return electronicBook;
	}

	public void setElectronicBook(boolean electronicBook) {
		this.electronicBook = electronicBook;
	}

	public boolean isPhysicalBook() {
		return physicalBook;
	}

	public void setPhysicalBook(boolean physicalBook) {
		this.physicalBook = physicalBook;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
