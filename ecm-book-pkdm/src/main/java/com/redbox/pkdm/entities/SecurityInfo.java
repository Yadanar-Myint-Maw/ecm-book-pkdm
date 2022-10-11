package com.redbox.pkdm.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Embeddable;

/**
 * 
 * @author kgsithu
 * @since 31-August-22
 * @version 0.0.1
 *
 */

@Embeddable
public class SecurityInfo {
	
	private String createUser;
	
	private String createTime;
	
	private LocalDate createDate;
	
	private String updateUser;
	
	private String updateTime;
	
	private LocalDate updateDate;
	
	public SecurityInfo () {
		
	}
	
	public SecurityInfo (String user) {
		this.createUser = this.updateUser = user;
		this.createTime = this.updateTime = LocalTime.now().toString();
		this.createDate = this.updateDate = LocalDate.now();
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public LocalDate getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDate updateDate) {
		this.updateDate = updateDate;
	}

}
