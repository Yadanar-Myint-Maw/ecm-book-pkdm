package com.redbox.pkdm.entities;


import java.time.LocalDate;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class AccountUser {

	@Id
	private String id;
	
	@Lob
	private String image;

	private String name;
	
	private String age;
	
	private String gender;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dob;
	
	private String phone;
	
	private String email;
	
	private String password;
	
	private String level;
	
	private boolean erase;
	
	@Embedded
	private SecurityInfo securityInfo;
	
	public AccountUser () {
		
	}

	public AccountUser(String id, String image, String name, String age, String gender, LocalDate dob, String phone,
			String email, String password, String level, boolean erase, SecurityInfo securityInfo) {
		super();
		this.id = id;
		this.image = image;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.dob = dob;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.level = level;
		this.erase = erase;
		this.securityInfo = securityInfo;
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

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
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
