package com.redbox.pkdm.entities;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class ShelfAuthor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Lob
	private String image;
	
	@Transient
	private MultipartFile file;
	
	private String name;
	
	@Lob
	private String description;
	
	private boolean erase;
	
	@Embedded
	private SecurityInfo securityInfo;
	
	public ShelfAuthor() {
		
	}

	public ShelfAuthor(long id, String image, String name, String description, boolean erase,
			SecurityInfo securityInfo) {
		super();
		this.id = id;
		this.image = image;
		this.name = name;
		this.description = description;
		this.erase = erase;
		this.securityInfo = securityInfo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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
