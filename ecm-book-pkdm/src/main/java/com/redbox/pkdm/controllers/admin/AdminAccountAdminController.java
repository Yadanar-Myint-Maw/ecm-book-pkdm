 package com.redbox.pkdm.controllers.admin;

import java.time.LocalDate;
import java.time.LocalTime;

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
import com.redbox.pkdm.utilities.MessageUtility;

@Controller
@RequestMapping("admin/account/admin")
public class AdminAccountAdminController {
	
	@Autowired
	private AccountAdminService accountAdminService;
	
	@GetMapping("/initialize/{id}")
	public String initialize(@PathVariable String id, @CookieValue("login_user_id") String cookieId, Model model, String keyword) throws Exception {
		AccountAdmin loginaccount = accountAdminService.findByID(cookieId);
		if (loginaccount == null) {
			throw new Exception();
		}
		model.addAttribute("loginaccount", loginaccount);
		if (id.equals("0")) {
			model.addAttribute("admin", new AccountAdmin());
		} else {
			model.addAttribute("admin", accountAdminService.findByID(cookieId));
		}
		if (keyword == null) {
			model.addAttribute("admins", accountAdminService.findByErase(false));
		} else {
			model.addAttribute("admins", accountAdminService.findByNameAndErase(keyword, false));
		}
		return "adminaccountadmin";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("admin") AccountAdmin accountAdmin, @CookieValue("login_user_id") String cookieId, Model model, RedirectAttributes redirAttrs) {			
		if (accountAdmin.getName().isEmpty() || accountAdmin.getUsername().isEmpty() || accountAdmin.getPassword().isEmpty()) {
			redirAttrs.addFlashAttribute("validation", MessageUtility.getDataIsMissingMessage());
		} else {
			if (accountAdmin.getId().isEmpty()) {
				accountAdmin.setSecurityInfo(new SecurityInfo(cookieId));
				accountAdminService.save(accountAdmin);
				redirAttrs.addFlashAttribute("save", MessageUtility.getSaveSuccessMessage("Account"));
			} else {
				AccountAdmin accountAdmin2 = accountAdminService.findByID(accountAdmin.getId());
				accountAdmin2.setName(accountAdmin.getName());
				accountAdmin2.setUsername(accountAdmin.getUsername());
				accountAdmin2.setPassword(accountAdmin.getPassword());
				accountAdmin2.getSecurityInfo().setUpdateDate(LocalDate.now());
				accountAdmin2.getSecurityInfo().setUpdateTime(LocalTime.now().toString());
				accountAdmin2.getSecurityInfo().setUpdateUser(cookieId);
				accountAdminService.save(accountAdmin2);
				redirAttrs.addFlashAttribute("update", MessageUtility.getUpdateSuccessMessage("Account"));
			}
		}
		return "redirect:/admin/account/admin/initialize/0";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable String id, RedirectAttributes redirAttrs) {
		accountAdminService.delete(accountAdminService.findByID(id));
		redirAttrs.addFlashAttribute("validation", MessageUtility.getDeleteSuccesMessage("Account"));
		return "redirect:/admin/account/admin/initialize/0";
	}

	
}
