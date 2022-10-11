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
public class ShelfFeature {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Lob
	private String iamge;
	
	private String name;
	
	@Lob
	private String description;
	
	@Transient
	private MultipartFile file;
	
	private boolean erase;
	
	@Embedded
	private SecurityInfo securityInfo;
	
	public ShelfFeature() {
		
	}

	public ShelfFeature(long id, String iamge, String name, String description, boolean erase,
			SecurityInfo securityInfo) {
		super();
		this.id = id;
		this.iamge = iamge;
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

	public String getIamge() {
		return iamge;
	}

	public void setIamge(String iamge) {
		this.iamge = iamge;
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
