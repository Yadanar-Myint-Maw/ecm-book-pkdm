package com.redbox.pkdm.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.redbox.pkdm.entities.AccountAdmin;
import com.redbox.pkdm.services.AccountAdminService;

@Controller
@RequestMapping("admin/profile")
public class AdminProfileController {
	
	@Autowired
	private AccountAdminService accountAdminService;

	@GetMapping("/account")
	public String initialize(Model model, @CookieValue("login_user_id") String cookieId) throws Exception {
		AccountAdmin loginaccount = accountAdminService.findByID(cookieId);
		if (loginaccount == null) {
			throw new Exception();
		}
		model.addAttribute("loginaccount", loginaccount);
		model.addAttribute("accountprofile", accountAdminService.findByID(cookieId));
		return "adminaccountprofile";
	}
	
	@PostMapping("/edit/update")
	public String save(@ModelAttribute("accountprofile") AccountAdmin accountAdmin, @CookieValue("login_user_id") String cookieId) {
		AccountAdmin accountadmin2 = accountAdminService.findByID(cookieId);
		accountadmin2.setName(accountAdmin.getName());
		accountadmin2.setUsername(accountAdmin.getUsername());
		accountadmin2.setPassword(accountAdmin.getPassword());
		accountadmin2.getSecurityInfo().setUpdateUser(cookieId);
		
		accountAdminService.save(accountadmin2);
		return "redirect:/admin/profile/account";
	}
	
	
}
