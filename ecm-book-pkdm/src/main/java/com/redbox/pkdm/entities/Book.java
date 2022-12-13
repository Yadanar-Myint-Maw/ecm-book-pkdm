package com.redbox.pkdm.entities;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class Book {
	
	@Id
	private String id;
	
	private String image;
	
	private String translatorImage;
	
	private String name;
	
	private String description;
	
	@Lob
	private String trailer;
	
	@Lob
	private String foreword;
		
	@Transient
	private MultipartFile translatorFile;
	
	@Transient
	private MultipartFile file;
	
	@Transient
	private String category;
	
	@Transient
	private String related;
	
	@Transient
	private String relatedDiscount;
	
	@Transient
	private String author;
	
	@ManyToOne
	private BookTag bookTag;
	
	private String bookType;
	
	private boolean electronicBook;
	
	private boolean physicalBook;
	
	private double e_price;
	
	private double p_price;
	
	private int e_discount;
	
	private int p_discount;
	
	private String d_discount;
	
	@Transient
	private double e_actual_price;
	
	@Transient
	private double p_actual_price;
	
	private boolean erase;
	
	@Embedded
	private SecurityInfo securityInfo;
	
	//Myosandikyaw
	
	private String authorName;
	
	private String categoryName;
	
	private String relatedName;
	
	private double relatedPrice;
	
	private String tagName;
	
	private double tagPrice;
	
	private long sortNo;
	
	public Book() {
		
	}

	public Book(String id, String image, String translatorImage, String name, String description, String trailer,
			String foreword, MultipartFile translatorFile, MultipartFile file, String category, String related,
			String bookType, boolean electronicBook, boolean physicalBook, double e_price, double p_price,
			boolean erase, SecurityInfo securityInfo) {
		super();
		this.id = id;
		this.image = image;
		this.translatorImage = translatorImage;
		this.name = name;
		this.description = description;
		this.trailer = trailer;
		this.foreword = foreword;
		this.translatorFile = translatorFile;
		this.file = file;
		this.category = category;
		this.related = related;
		this.bookType = bookType;
		this.electronicBook = electronicBook;
		this.physicalBook = physicalBook;
		this.e_price = e_price;
		this.p_price = p_price;
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

	public BookTag getBookTag() {
		return bookTag;
	}

	public void setBookTag(BookTag bookTag) {
		this.bookTag = bookTag;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
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

	public String getD_discount() {
		return d_discount;
	}

	public void setD_discount(String d_discount) {
		this.d_discount = d_discount;
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

	public String getRelatedDiscount() {
		return relatedDiscount;
	}

	public void setRelatedDiscount(String relatedDiscount) {
		this.relatedDiscount = relatedDiscount;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public double getRelatedPrice() {
		return relatedPrice;
	}

	public void setRelatedPrice(double relatedPrice) {
		this.relatedPrice = relatedPrice;
	}

	public double getTagPrice() {
		return tagPrice;
	}

	public void setTagPrice(double tagPrice) {
		this.tagPrice = tagPrice;
	}

	public String getRelatedName() {
		return relatedName;
	}

	public void setRelatedName(String relatedName) {
		this.relatedName = relatedName;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public long getSortNo() {
		return sortNo;
	}

	public void setSortNo(long sortNo) {
		this.sortNo = sortNo;
	}
	
	
	
	
	
	

}
