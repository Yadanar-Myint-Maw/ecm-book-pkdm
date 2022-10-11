package com.redbox.pkdm.entities;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class TermAndCondition {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	
	private long sortNumber;
	
	private String title;
	
	@Lob
	private String description;
	
	private boolean erase;
	
	@Embedded
	private SecurityInfo securityInfo;
	
	public TermAndCondition() {
		
	}

	public TermAndCondition(long id, long sortNumber, String title, String description, boolean erase,
			SecurityInfo securityInfo) {
		super();
		this.id = id;
		this.sortNumber = sortNumber;
		this.title = title;
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

	public long getSortNumber() {
		return sortNumber;
	}

	public void setSortNumber(long sortNumber) {
		this.sortNumber = sortNumber;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

}
