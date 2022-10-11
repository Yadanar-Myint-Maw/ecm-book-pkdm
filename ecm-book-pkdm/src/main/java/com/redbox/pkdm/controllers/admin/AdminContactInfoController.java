package com.redbox.pkdm.controllers.admin;

import java.time.LocalDate;
import java.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.redbox.pkdm.entities.AccountAdmin;
import com.redbox.pkdm.entities.ContactInfo;
import com.redbox.pkdm.services.AccountAdminService;
import com.redbox.pkdm.services.ContactInfoService;

@Controller
@RequestMapping("/admin/contact")
public class AdminContactInfoController {
	
	@Autowired
	private ContactInfoService contactInfoService;
	
	@Autowired
	private AccountAdminService accountAdminService;
	
	@GetMapping("/edit")
	public String initialize(Model model, String keyword,  @CookieValue("login_user_id") String cookieId) throws Exception {
		AccountAdmin loginaccount = accountAdminService.findByID(cookieId);
		if (loginaccount == null) {
			throw new Exception();
		}
		model.addAttribute("loginaccount", loginaccount);	
		
		model.addAttribute("contactInfo", contactInfoService.findById(1));	
		return "admincontact";
	}
	
	@PostMapping("/edit/update")
	public String save(@ModelAttribute("contactInfo") ContactInfo contactInfo, @CookieValue("login_user_id") String cookieId, RedirectAttributes redirAttrs) {		
		
			ContactInfo contactInfo2 = contactInfoService.findById(1);
			contactInfo2.setPhone1(contactInfo.getPhone1());
			contactInfo2.setPhone2(contactInfo.getPhone2());
			contactInfo2.setEmail1(contactInfo.getEmail1());
			contactInfo2.setEmail2(contactInfo.getEmail2());
			contactInfo2.setViber(contactInfo.getViber());
			contactInfo2.setTelegram(contactInfo.getTelegram());
			contactInfo2.setFacebook(contactInfo.getFacebook());
			contactInfo2.getSecurityInfo().setUpdateDate(LocalDate.now());
			contactInfo2.getSecurityInfo().setUpdateTime(LocalTime.now().toString());
			contactInfo2.getSecurityInfo().setUpdateUser(cookieId);
			redirAttrs.addFlashAttribute("update","Contact Information update successfully");
			
			contactInfoService.save(contactInfo2);					
		return "redirect:/admin/contact/edit";
	}
	
	
	
	
	
	
	
	
	

}
