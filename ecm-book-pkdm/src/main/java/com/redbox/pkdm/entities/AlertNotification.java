package com.redbox.pkdm.entities;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class AlertNotification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String title;
	
	@Lob
	private String description;

	private String redirectPage;
	
	private String notificationType;
	
	private boolean active;
	
	private boolean erase;
	
	@ManyToOne
	private AccountUser accountUser;
	
	private String notificationProcess;
	
	@Embedded
	private SecurityInfo securityInfo;
	
	public AlertNotification() {
		
	}

	public long getId() {
		return id;
	}

	public AlertNotification(long id, String title, String description, String redirectPage, String notificationType,
			boolean active, boolean erase, AccountUser accountUser, String notificationProcess,
			SecurityInfo securityInfo) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.redirectPage = redirectPage;
		this.notificationType = notificationType;
		this.active = active;
		this.erase = erase;
		this.accountUser = accountUser;
		this.notificationProcess = notificationProcess;
		this.securityInfo = securityInfo;
	}

	public AccountUser getAccountUser() {
		return accountUser;
	}

	public void setAccountUser(AccountUser accountUser) {
		this.accountUser = accountUser;
	}

	public String getNotificationProcess() {
		return notificationProcess;
	}

	public void setNotificationProcess(String notificationProcess) {
		this.notificationProcess = notificationProcess;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getRedirectPage() {
		return redirectPage;
	}

	public void setRedirectPage(String redirectPage) {
		this.redirectPage = redirectPage;
	}

	public String getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
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