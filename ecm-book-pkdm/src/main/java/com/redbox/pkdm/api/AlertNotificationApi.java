package com.redbox.pkdm.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.redbox.pkdm.entities.AlertNotification;
import com.redbox.pkdm.services.AlertNotificationService;

@RestController
@RequestMapping("admin/alertnotification/api")
public class AlertNotificationApi {
	
	@Autowired
	private AlertNotificationService alertNotificationService;
	
	@GetMapping("/alertnotificationlist")
	public List<AlertNotification> getAlertNotificationList(@RequestParam String id){
		return alertNotificationService.getAlertNotificationList(id);
	}

}
