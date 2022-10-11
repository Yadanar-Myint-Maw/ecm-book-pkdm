package com.redbox.pkdm.entities;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class Book {
	
	@Id
	private String id;
	
	private String image;
	
	private String translatorImage;
	
	private String name;
	
	@Lob
	private String description;
	
	@Lob
	private String trailer;
	
	@Lob
	private String foreword;
		
	@Transient
	private MultipartFile translatorFile;
	
	@Transient
	private MultipartFile file;
	
	private String bookType;
	
	private boolean electronicBook;
	
	private boolean physicalBook;
	
	private double price;
	
	private boolean erase;
	
	@Embedded
	private SecurityInfo securityInfo;
	
	public Book() {
		
	}

	public Book(String id, String image, String name, String description, String trailer, String bookType,
			boolean electronicBook, boolean physicalBook, double price, boolean erase, SecurityInfo securityInfo) {
		super();
		this.id = id;
		this.image = image;
		this.name = name;
		this.description = description;
		this.trailer = trailer;
		this.bookType = bookType;
		this.electronicBook = electronicBook;
		this.physicalBook = physicalBook;
		this.price = price;
		this.erase = erase;
		this.securityInfo = securityInfo;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
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

	public String getTranslatorImage() {
		return translatorImage;
	}

	public void setTranslatorImage(String translatorImage) {
		this.translatorImage = translatorImage;
	}

	public String getForeword() {
		return foreword;
	}

	public void setForeword(String foreword) {
		this.foreword = foreword;
	}

	public MultipartFile getTranslatorFile() {
		return translatorFile;
	}

	public void setTranslatorFile(MultipartFile translatorFile) {
		this.translatorFile = translatorFile;
	}
	
	
	
	

}
