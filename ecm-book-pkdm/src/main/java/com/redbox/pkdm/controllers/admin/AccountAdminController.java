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
import com.redbox.pkdm.entities.SecurityInfo;
import com.redbox.pkdm.services.AccountAdminService;

@Controller
@RequestMapping("admin/account")
public class AccountAdminController {
	
	@Autowired
	private AccountAdminService accountAdminService;
	
	@GetMapping("/admins/{id}")
	public String initialize(@PathVariable String id, @CookieValue("login_user_id") String cookieId, Model model, String keyword) throws Exception {
		AccountAdmin loginaccount = accountAdminService.findByID(cookieId);
		if (loginaccount == null) {
			throw new Exception();
		}
		model.addAttribute("loginaccount", loginaccount);
		if (id.equals("new")) {
			model.addAttribute("admin", new AccountAdmin());
		} else {
			model.addAttribute("admin", accountAdminService.findByID(id));
		}
		if (keyword == null) {
			model.addAttribute("admins", accountAdminService.findByErase(false));
		} else {
			model.addAttribute("admins", accountAdminService.findByNameAndErase(keyword, false));
		}
		return "adminaccountadmins";
	}
	
	@PostMapping("/admins/save")
	public String save(@ModelAttribute("admin") AccountAdmin accountAdmin, @CookieValue("login_user_id") String cookieId, Model model, RedirectAttributes redirAttrs) {			
		if (accountAdmin.getName().isEmpty() || accountAdmin.getUsername().isEmpty() || accountAdmin.getPassword().isEmpty()) {
			redirAttrs.addFlashAttribute("validation", "Data is missing!");
		} else {
			if (accountAdmin.getId().isEmpty()) {
				accountAdmin.setSecurityInfo(new SecurityInfo(cookieId));
				accountAdminService.save(accountAdmin);
				redirAttrs.addFlashAttribute("save", "Admin Account save successfully.");
			} else {
				AccountAdmin accountAdmin2 = accountAdminService.findByID(accountAdmin.getId());
				accountAdmin2.setName(accountAdmin.getName());
				accountAdmin2.setUsername(accountAdmin.getUsername());
				accountAdmin2.setPassword(accountAdmin.getPassword());
				accountAdmin2.getSecurityInfo().setUpdateDate(LocalDate.now());
				accountAdmin2.getSecurityInfo().setUpdateTime(LocalTime.now().toString());
				accountAdmin2.getSecurityInfo().setUpdateUser(cookieId);
				accountAdminService.save(accountAdmin2);
				redirAttrs.addFlashAttribute("update", "Admin Account update successfully.");
			}
		}
		return "redirect:/admin/account/admins/new";
	}
	
	@GetMapping("/admins/delete/{id}")
	public String delete(@PathVariable String id, RedirectAttributes redirAttrs) {
		accountAdminService.delete(accountAdminService.findByID(id));
		redirAttrs.addFlashAttribute("validation", "Admin Account delete successfully!");
		return "redirect:/admin/account/admins/new";
	}

	
}
