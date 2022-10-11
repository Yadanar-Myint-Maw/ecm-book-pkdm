package com.redbox.pkdm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.redbox.pkdm.entities.AccountAdmin;
import com.redbox.pkdm.services.AccountAdminService;

@Controller
@RequestMapping("admin/template")
public class TemplateController {
	
	@Autowired
	private AccountAdminService accountAdminService;
	
	@GetMapping("/loginadmin")
	public String getLoginAdminAccount(@CookieValue("login_user_id") String cookieId, Model model) {
		if (cookieId == null) {
			return "/";
		} else {
			AccountAdmin accountAdmin = accountAdminService.findByID(cookieId);
			if (accountAdmin == null) {
				return "/";
			} else {
				model.addAttribute("loginadmin", accountAdmin);
				return "";
			}
		}
	}
	
	@GetMapping("/template1")
	public String initialize() {
		return "template1";
	}

}
