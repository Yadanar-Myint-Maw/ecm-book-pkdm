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
import com.redbox.pkdm.entities.AccountUser;
import com.redbox.pkdm.entities.SecurityInfo;
import com.redbox.pkdm.services.AccountAdminService;
import com.redbox.pkdm.services.AccountUserService;
import com.redbox.pkdm.utilities.MessageUtility;


@Controller
@RequestMapping("admin/account/user")
public class AdminAccountUserController {
	
	@Autowired
	private AccountUserService accountUserService;
	
	@Autowired
	private AccountAdminService  accountAdminService;
	
	
	@GetMapping("/initialize/{id}")
	public String initialize(@PathVariable String id, @CookieValue("login_user_id") String cookieId, Model model, String keyword1, String keyword2, String keyword3) throws Exception {
		AccountAdmin loginaccount = accountAdminService.findByID(cookieId);
		if (loginaccount == null) {
			throw new Exception();
		}
		model.addAttribute("loginaccount", loginaccount);
		if(id.equals("0")) {
			model.addAttribute("user", new AccountUser());
		} else {
			model.addAttribute("user", accountUserService.findByID(id));
		}
		List<AccountUser> accountUsers = new ArrayList<>();
		List<AccountUser> accountUsers2 = new ArrayList<>();
		if (keyword1 == null && keyword2 == null && keyword3 == null) {
			accountUsers = accountUserService.findByErase(false);
		} else if (!keyword1.isEmpty() && !keyword2.isEmpty() && !keyword3.isEmpty()) {
			accountUsers2 = accountUserService.findByErase(false);
			for (AccountUser user : accountUsers2) {
				if (user.getId().equals(keyword1) 
						&& user.getName().toLowerCase().contains(keyword2.toLowerCase())
						&& user.getPhone().equals(keyword3)) {
					accountUsers.add(user);
				}
			}
		} else if (!keyword1.isEmpty() && !keyword2.isEmpty() && keyword3.isEmpty()) {
			accountUsers2 = accountUserService.findByErase(false);
			for (AccountUser user : accountUsers2) {
				if (user.getId().equals(keyword1) 
						&& user.getName().toLowerCase().contains(keyword2.toLowerCase())) {
					accountUsers.add(user);
				}
			}
		} else if (!keyword1.isEmpty() && keyword2.isEmpty() && !keyword3.isEmpty()) {
			accountUsers2 = accountUserService.findByErase(false);
			for (AccountUser user : accountUsers2) {
				if (user.getId().equals(keyword1) && user.getPhone().equals(keyword3)) {
					accountUsers.add(user);
				}
			}
		}  
		else if (keyword1.isEmpty() && !keyword2.isEmpty() && !keyword3.isEmpty()) {
			accountUsers2 = accountUserService.findByErase(false);
			for (AccountUser user : accountUsers2) {
				if (user.getName().toLowerCase().contains(keyword2.toLowerCase()) && user.getPhone().equals(keyword3)) {
					accountUsers.add(user);
				}
			}
		}  else if (!keyword1.isEmpty() && keyword2.isEmpty() && keyword3.isEmpty()) {
			accountUsers2 = accountUserService.findByErase(false);
			for (AccountUser user : accountUsers2) {
				if (user.getId().equals(keyword1)) {
					accountUsers.add(user);
				}
			}
		}  else if (keyword1.isEmpty() && !keyword2.isEmpty() && keyword3.isEmpty()) {
			accountUsers2 = accountUserService.findByErase(false);
			for (AccountUser user : accountUsers2) {
				if (user.getName().toLowerCase().contains(keyword2.toLowerCase())) {
					accountUsers.add(user);
				}
			}
		}  else if (keyword1.isEmpty() && keyword2.isEmpty() && !keyword3.isEmpty()) {
			accountUsers2 = accountUserService.findByErase(false);
			for (AccountUser user : accountUsers2) {
				if (user.getPhone().equals(keyword3)) {
					accountUsers.add(user);
				}
			}
		}  
		model.addAttribute("users", accountUsers);	
		return "adminaccountuser";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("user") AccountUser accountUser, @CookieValue("login_user_id") String cookieId, Model model, RedirectAttributes redirAttrs) {
		if(accountUser.getName().isEmpty() || accountUser.getPhone().isEmpty() || accountUser.getPassword().isEmpty() || accountUser.getLevel().isEmpty()) {
			redirAttrs.addFlashAttribute("validation", MessageUtility.getDataIsMissingMessage());
		} else {
			if(accountUser.getId().isEmpty()) {
				accountUser.setSecurityInfo(new SecurityInfo(cookieId));
				accountUserService.save(accountUser);
				redirAttrs.addFlashAttribute("save", MessageUtility.getSaveSuccessMessage("Account"));
			} else {
				AccountUser accountUser2 = accountUserService.findByID(accountUser.getId());
				accountUser2.setName(accountUser.getName());
				accountUser2.setDob(accountUser.getDob());
				accountUser2.setPhone(accountUser.getPhone());
				accountUser2.setPassword(accountUser.getPassword());
				accountUser2.setLevel(accountUser.getLevel());
				accountUser2.getSecurityInfo().setUpdateDate(LocalDate.now());
				accountUser2.getSecurityInfo().setUpdateTime(LocalTime.now().toString());
				accountUser2.getSecurityInfo().setUpdateUser(cookieId);
				accountUserService.save(accountUser2);
				redirAttrs.addFlashAttribute("update", MessageUtility.getUpdateSuccessMessage("Account"));
			}
		}
		return "redirect:/admin/account/user/initialize/0";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable String id, RedirectAttributes redirAttrs) {
		accountUserService.delete(accountUserService.findByID(id));
		redirAttrs.addFlashAttribute("validation", MessageUtility.getDeleteSuccesMessage("Account"));
		return "redirect:/admin/account/user/initialize/0";
		
	}

}
