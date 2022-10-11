package com.redbox.pkdm.models;

import java.time.LocalDate;


public class AccountUserModel{
	
	private String id;
	
	private String image;

	private String name;
	
	private String age;
	
	private String gender;
	
	private String dob;
	
	private String phone;
	
	private String email;
	
	private String password;
	
	private String level;
	
	private boolean erase;
	
	public AccountUserModel() {
		
	}

	public AccountUserModel(String id, String image, String name, String age, String gender, String dob, String phone,
			String email, String password, String level, boolean erase) {
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

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
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

	@Override
	public String toString() {
		return "AccountUserModel [id=" + id + ", image=" + image + ", name=" + name + ", age=" + age + ", gender="
				+ gender + ", dob=" + dob + ", phone=" + phone + ", email=" + email + ", password=" + password
				+ ", level=" + level + ", erase=" + erase + "]";
	}
}
