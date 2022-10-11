package com.redbox.pkdm.entities;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class BookSectionDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private int sort_no;
	
	private String image;
	
	private String name;
	
	@Transient
	private MultipartFile file;
	
	
	@Lob
	private String description;
	
	@ManyToOne
	private BookSection bookSection;
	
	private boolean erase;
	
	@Embedded
	private SecurityInfo securityInfo;
	
	public BookSectionDetail() {
		
	}
	
	public BookSectionDetail(BookSection bookSection) {
		this.bookSection = bookSection;
	}

	public BookSectionDetail(long id, int sort_no, String image, String name, String description,
			BookSection bookSection, boolean erase, SecurityInfo securityInfo) {
		super();
		this.id = id;
		this.sort_no = sort_no;
		this.image = image;
		this.name = name;
		this.description = description;
		this.bookSection = bookSection;
		this.erase = erase;
		this.securityInfo = securityInfo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getSort_no() {
		return sort_no;
	}

	public void setSort_no(int sort_no) {
		this.sort_no = sort_no;
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

	public BookSection getBookSection() {
		return bookSection;
	}

	public void setBookSection(BookSection bookSection) {
		this.bookSection = bookSection;
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

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	
	
}
