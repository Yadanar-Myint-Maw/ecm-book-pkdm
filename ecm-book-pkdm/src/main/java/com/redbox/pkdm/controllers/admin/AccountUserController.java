package com.redbox.pkdm.controllers.admin;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.Year;
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


@Controller
@RequestMapping("admin/account")
public class AccountUserController {
	
	@Autowired
	private AccountUserService accountUserService;
	
	@Autowired
	private AccountAdminService  accountAdminService;
	
	
	@GetMapping("/users/{id}")
	public String initialize(@PathVariable String id, @CookieValue("login_user_id") String cookieId, Model model, String keyword1, String keyword2, String keyword3) throws Exception {
		AccountAdmin loginaccount = accountAdminService.findByID(cookieId);
		if (loginaccount == null) {
			throw new Exception();
		}
		model.addAttribute("loginaccount", loginaccount);
		if(id.equals("new")) {
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
		return "adminaccountusers";
	}
	
	@PostMapping("/users/save")
	public String save(@ModelAttribute("user") AccountUser accountUser, @CookieValue("login_user_id") String cookieId, Model model, RedirectAttributes redirAttrs) {
		if(accountUser.getName().isEmpty() || 
			accountUser.getGender().isEmpty() || accountUser.getPhone().isEmpty() || accountUser.getEmail().isEmpty() || 
			accountUser.getPassword().isEmpty() || accountUser.getLevel().isEmpty()) {
			redirAttrs.addFlashAttribute("validation", "Data is missing!");
		}else {
			if(accountUser.getId().isEmpty()) {
				accountUser.setSecurityInfo(new SecurityInfo(cookieId));
				accountUser.setImage("/img/user.png");
				accountUser.setAge(String.valueOf(Math.abs(Period.between(LocalDate.now(), accountUser.getDob()).getYears())));
				accountUserService.save(accountUser);
				redirAttrs.addFlashAttribute("save", "User Account save successfully.");
			} else {
				AccountUser accountUser2 = accountUserService.findByID(accountUser.getId());
				accountUser2.setImage(accountUser.getImage());
				accountUser2.setName(accountUser.getName());
				accountUser2.setAge(accountUser.getAge());
				accountUser2.setGender(accountUser.getGender());
				accountUser2.setDob(accountUser.getDob());
				accountUser2.setPhone(accountUser.getPhone());
				accountUser2.setEmail(accountUser.getEmail());
				accountUser2.setPassword(accountUser.getPassword());
				accountUser2.setLevel(accountUser.getLevel());
				accountUser2.setAge(String.valueOf(Math.abs(Period.between(LocalDate.now(), accountUser.getDob()).getYears())));
				accountUser2.setImage("/img/user.png");
				accountUser2.getSecurityInfo().setUpdateDate(LocalDate.now());
				accountUser2.getSecurityInfo().setUpdateTime(LocalTime.now().toString());
				accountUser2.getSecurityInfo().setUpdateUser(cookieId);
				accountUserService.save(accountUser2);
				redirAttrs.addFlashAttribute("update", "User Account update successfully.");
			}
		}
		return "redirect:/admin/account/users/new";
	}
	
	@GetMapping("/users/delete/{id}")
	public String delete(@PathVariable String id, RedirectAttributes redirAttrs) {
		accountUserService.delete(accountUserService.findByID(id));
		redirAttrs.addFlashAttribute("validation", "User Account delete successfully!");
		return "redirect:/admin/account/users/new";
		
	}

}
