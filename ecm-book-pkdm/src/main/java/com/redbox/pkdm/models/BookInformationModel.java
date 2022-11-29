package com.redbox.pkdm.models;

public class BookInformationModel {
	
	private String id;
	
	private String image;
	
	private String name;
	
	private String description;
	
	private String category;
	
	private String related;
	
	private double relatedDiscount;
	
	private String author;
	
	private String tag;
	
	private String bookType;
	
	private boolean electronicBook;
	
	private boolean physicalBook;
	
	private double e_price;
	
	private double p_price;
	
	private int e_discount;
	
	private int p_discount;
	
	private double e_actual_price;
	
	private double p_actual_price;
	
	private boolean e_purchased;
	
	private boolean p_purchased;

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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getRelated() {
		return related;
	}

	public void setRelated(String related) {
		this.related = related;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
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

	public double getE_price() {
		return e_price;
	}

	public void setE_price(double e_price) {
		this.e_price = e_price;
	}

	public double getP_price() {
		return p_price;
	}

	public void setP_price(double p_price) {
		this.p_price = p_price;
	}

	public int getE_discount() {
		return e_discount;
	}

	public void setE_discount(int e_discount) {
		this.e_discount = e_discount;
	}

	public int getP_discount() {
		return p_discount;
	}

	public void setP_discount(int p_discount) {
		this.p_discount = p_discount;
	}

	public double getE_actual_price() {
		return e_actual_price;
	}

	public void setE_actual_price(double e_actual_price) {
		this.e_actual_price = e_actual_price;
	}

	public double getP_actual_price() {
		return p_actual_price;
	}

	public void setP_actual_price(double p_actual_price) {
		this.p_actual_price = p_actual_price;
	}

	public boolean isE_purchased() {
		return e_purchased;
	}

	public void setE_purchased(boolean e_purchased) {
		this.e_purchased = e_purchased;
	}

	public boolean isP_purchased() {
		return p_purchased;
	}

	public void setP_purchased(boolean p_purchased) {
		this.p_purchased = p_purchased;
	}

	public double getRelatedDiscount() {
		return relatedDiscount;
	}

	public void setRelatedDiscount(double relatedDiscount) {
		this.relatedDiscount = relatedDiscount;
	}

}

