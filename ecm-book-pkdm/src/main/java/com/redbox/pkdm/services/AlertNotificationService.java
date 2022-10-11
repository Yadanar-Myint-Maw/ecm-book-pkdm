package com.redbox.pkdm.services;

import java.util.List;

import com.redbox.pkdm.entities.AlertNotification;

public interface AlertNotificationService {
	
	void save(AlertNotification alertNotification);
	
	void delete(AlertNotification alertNotification);
	
	AlertNotification findByID(long id);
	
	List<AlertNotification> findAll();
	
	List<AlertNotification> findByErase(boolean erase);

	List<AlertNotification> getAlertNotificationList(String id);

}
