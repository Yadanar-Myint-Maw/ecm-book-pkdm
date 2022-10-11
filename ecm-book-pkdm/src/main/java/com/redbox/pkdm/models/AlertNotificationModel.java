package com.redbox.pkdm.models;

public class AlertNotificationModel {
	
	private long id;
	private String title;
	private String description;
	private String redirectPage;
	private String notificationType;
	private String notificationProcess;
	
	public AlertNotificationModel() {
		
	}

	public AlertNotificationModel(long id, String title, String description, String redirectPage,
			String notificationType, String notificationProcess) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.redirectPage = redirectPage;
		this.notificationType = notificationType;
		this.notificationProcess = notificationProcess;
	}

	public long getId() {
		return id;
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

	public String getNotificationProcess() {
		return notificationProcess;
	}

	public void setNotificationProcess(String notificationProcess) {
		this.notificationProcess = notificationProcess;
	}

	@Override
	public String toString() {
		return "AlertNotificationModel [id=" + id + ", title=" + title + ", description=" + description
				+ ", redirectPage=" + redirectPage + ", notificationType=" + notificationType + ", notificationProcess="
				+ notificationProcess + "]";
	}

	
}
