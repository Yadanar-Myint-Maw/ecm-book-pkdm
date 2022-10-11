package com.redbox.pkdm.entities;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class AccountAuthor {

	@Id
	private String id;

	private String name;
	
	private String username;
	
	private String password;
	
	private boolean erase;
	
	@ManyToOne
	private ShelfAuthor shelfAuthor;
	
	@Embedded
	private SecurityInfo securityInfo;
	
	public AccountAuthor() {
		
	}

	public ShelfAuthor getShelfAuthor() {
		return shelfAuthor;
	}



	public void setShelfAuthor(ShelfAuthor shelfAuthor) {
		this.shelfAuthor = shelfAuthor;
	}

	public AccountAuthor(String id, String name, String username, String password, boolean erase,
			ShelfAuthor shelfAuthor, SecurityInfo securityInfo) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.erase = erase;
		this.shelfAuthor = shelfAuthor;
		this.securityInfo = securityInfo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
