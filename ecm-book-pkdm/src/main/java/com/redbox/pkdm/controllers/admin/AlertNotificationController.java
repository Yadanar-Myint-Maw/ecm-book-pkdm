package com.redbox.pkdm.controllers.admin;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.redbox.pkdm.entities.AccountAdmin;
import com.redbox.pkdm.entities.AlertNotification;
import com.redbox.pkdm.entities.SecurityInfo;
import com.redbox.pkdm.services.AccountAdminService;
import com.redbox.pkdm.services.AlertNotificationService;

@Controller
@RequestMapping("admin/notification")
public class AlertNotificationController {
	
	@Autowired
	private AlertNotificationService alertNotificationService;
	
	@Autowired
	private AccountAdminService accountAdminService;
	
	@GetMapping("/alertNoti/{id}")
	public String initialize(@PathVariable String id,@CookieValue("login_user_id") String cookieId, Model model, String notificationType) throws Exception {
		
		AccountAdmin loginaccount = accountAdminService.findByID(cookieId);
		if (loginaccount == null) {
			throw new Exception();
		}
		model.addAttribute("loginaccount", loginaccount);
		
		if (id.equals("new")) {
			model.addAttribute("alertNotification", new AlertNotification());
		} else {
			model.addAttribute("alertNotification", alertNotificationService.findByID(Long.parseLong(id)));
		}
		
		if(notificationType == null ) {
			model.addAttribute("notifications", alertNotificationService.findByErase(false));
		}else {
			List<AlertNotification> notifications = alertNotificationService.findByErase(false);
			List<AlertNotification> notification2 = new ArrayList<>();
			for(AlertNotification a : notifications) {
				if(a.getNotificationType().equals(notificationType)) {
					notification2.add(a);
				}
			}
			model.addAttribute("notifications", notification2);
		}		
		return "adminnotification";
	}
	
	@PostMapping("/alertNoti/save")
	public String save(@ModelAttribute("alertNotification") AlertNotification alertNotification,String notificationType, @CookieValue("login_user_id") String cookieId, RedirectAttributes redirAttrs) {
		
		if(notificationType.equals("systemNoti")) {
			 alertNotification.setRedirectPage("System Notification");
		}else if(notificationType.equals("bookNoti")) {
			 alertNotification.setRedirectPage("Book Page");
		}else if(notificationType.equals("walletNoti")) {
			alertNotification.setRedirectPage("Wallet Page");
		}else if(notificationType.equals("purchaseNoti")) {
			 alertNotification.setRedirectPage("My Book Page");
		}
		
		
		
		if(alertNotification.getNotificationType().equals("notSelected") ) {
			redirAttrs.addFlashAttribute("validation", "Data is missing");
		}else {
			if(alertNotification.getId() == 0) {
				alertNotification.setSecurityInfo(new SecurityInfo(cookieId));
				alertNotification.setNotificationProcess("all");
				alertNotificationService.save(alertNotification);
				redirAttrs.addFlashAttribute("save", "Alert Notification save successfully");
			}else {
				AlertNotification alertNotification2 = alertNotificationService.findByID(alertNotification.getId());
				alertNotification2.setNotificationType(alertNotification.getNotificationType());
				alertNotification2.setTitle(alertNotification.getTitle());
				alertNotification2.setDescription(alertNotification.getDescription());
				alertNotification2.getSecurityInfo().setUpdateDate(LocalDate.now());
				alertNotification2.getSecurityInfo().setUpdateTime(LocalTime.now().toString());
				alertNotification2.getSecurityInfo().setUpdateUser(cookieId);
				alertNotificationService.save(alertNotification2);
				redirAttrs.addFlashAttribute("update" , "Alert Notification update successfully");
		    }
		}
		return "redirect:/admin/notification/alertNoti/new";
	}
	
	@GetMapping("/alertNoti/delete/{id}")
	public String delete(@PathVariable String id, RedirectAttributes redirAttrs) {
		alertNotificationService.delete(alertNotificationService.findByID(Long.parseLong(id)));
		redirAttrs.addFlashAttribute("validation","Alert Notification delete successfully");
		return "redirect:/admin/notification/alertNoti/new";
	}
}
