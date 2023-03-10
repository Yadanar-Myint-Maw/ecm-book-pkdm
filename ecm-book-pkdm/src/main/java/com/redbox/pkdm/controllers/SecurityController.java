package com.redbox.pkdm.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.redbox.pkdm.entities.AccountAdmin;
import com.redbox.pkdm.models.AccountModel;
import com.redbox.pkdm.services.AccountAdminService;
import com.redbox.pkdm.services.AccountAuthorService;
import com.redbox.pkdm.services.BookService;
import com.redbox.pkdm.utilities.CookieUtility;

@Controller
public class SecurityController {
	
	@Autowired
	private AccountAdminService accountAdminService;
	
	@Autowired
	private AccountAuthorService accountAuthorService;
	
	@Autowired
	private BookService bookService;

	@RequestMapping("/signin")
	public String initialize(Model model) {
		model.addAttribute("account", new AccountModel());
		return "signin";
	}
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("seriesBooks", bookService.findByBookType("SERIE"));
		model.addAttribute("loneChinBooks", bookService.findByBookType("ENTIRE"));
		return "homepage";
	}
	

	@PostMapping("/signin")
	public String save(@ModelAttribute("account") AccountModel accountModel, HttpServletResponse response, RedirectAttributes redirAttrs) {
		if (!accountModel.getUsername().isEmpty() || !accountModel.getPassword().isEmpty()) {
			AccountAdmin accountAdmin = accountAdminService.findByUsernameAndPassword(accountModel.getUsername(), accountModel.getPassword());
			if (accountAdmin != null) {				
				CookieUtility.saveLoginUserIDCookie(accountAdmin.getId(), response);					
				return "redirect:/admin/general/dashboard";	
			} else {
				redirAttrs.addFlashAttribute("validation", "Username Or Password Incorrect");	
				return "redirect:/signin";	
			}
//			if (accountModel.getRole().equals("Admin")) {
//				AccountAdmin accountAdmin = accountAdminService.findByUsernameAndPassword(accountModel.getUsername(), accountModel.getPassword());
//				if (accountAdmin != null) {				
//					CookieUtility.saveLoginUserIDCookie(accountAdmin.getId(), response);					
//					return "redirect:/admin/general/dashboard";	
//				}
//			} else if( accountModel.getRole().equals("Author")) {
//				AccountAuthor accountAuthor = accountAuthorService.findByUsernameAndPassword(accountModel.getUsername(), accountModel.getPassword());
//				if(accountAuthor != null) {
//					CookieUtility.saveLoginUserIDCookie(accountAuthor.getId(), response);
//					return "redirect:/author/general/dashboard";
//				}
//			}
		}
		return "redirect:/";
	}
	
	@RequestMapping("/signout")
	public String signout(@ModelAttribute("account") AccountModel accountModel, HttpServletResponse response) {	
		return "signin";	
	}
	
}
