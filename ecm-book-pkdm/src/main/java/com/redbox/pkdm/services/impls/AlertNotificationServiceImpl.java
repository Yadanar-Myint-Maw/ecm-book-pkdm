package com.redbox.pkdm.services.impls;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redbox.pkdm.entities.AlertNotification;
import com.redbox.pkdm.models.AlertNotificationModel;
import com.redbox.pkdm.repositories.AlertNotificationRepository;
import com.redbox.pkdm.services.AlertNotificationService;

@Service
public class AlertNotificationServiceImpl implements AlertNotificationService{
	
	@Autowired
	private AlertNotificationRepository alertRepo;
	
	@Override
	public void save(AlertNotification alertNotification) {
		//alertNotification.setRedirectPage(rePage);
		alertRepo.save(alertNotification);
		
	}

	@Override
	public void delete(AlertNotification alertNotification) {
		alertNotification.setErase(true);
		alertRepo.save(alertNotification);
		
	}

	@Override
	public AlertNotification findByID(long id) {
		return alertRepo.findById(id).get();
	}

	@Override
	public List<AlertNotification> findAll() {
		return alertRepo.findAll();
	}

	@Override
	public List<AlertNotification> findByErase(boolean erase) {
		return alertRepo.findByErase(false);
	}

	@Override
	public List<AlertNotification> getAlertNotificationList(String id) {
		List<AlertNotificationModel> alertNotificationModels = alertRepo.getAlertNotificationList(id).stream()
				.map(a -> {
					return new AlertNotificationModel(a.getId(),
							a.getTitle(),
							a.getDescription(),
							a.getRedirectPage(),
							a.getNotificationType(),
							a.getDescription());
				}).collect(Collectors.toList());
		
		alertNotificationModels.forEach( a ->{
			a.getId();
			a.getTitle();
			a.getDescription();
			a.getRedirectPage();
			a.getNotificationType();
			a.getNotificationProcess();
		});
		return alertRepo.getAlertNotificationList(id);
	}
}
