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
import com.redbox.pkdm.entities.AccountAuthor;
import com.redbox.pkdm.entities.SecurityInfo;
import com.redbox.pkdm.services.AccountAdminService;
import com.redbox.pkdm.services.AccountAuthorService;
import com.redbox.pkdm.services.ShelfAuthorService;

@Controller
@RequestMapping("admin/account")
public class AccountAuthorController {
	
	@Autowired
	private AccountAuthorService accountAuthorService;
	
	@Autowired
	private ShelfAuthorService shelfAuthorService;
	
	@Autowired
	private AccountAdminService accountAdminService;
	
	@GetMapping("/authors/{id}")
	public String initialize(@PathVariable String id, Model model, String shelfId, @CookieValue("login_user_id") String cookieId) throws Exception{
		
		AccountAdmin loginaccount = accountAdminService.findByID(cookieId);
		if (loginaccount == null) {
			throw new Exception();
		}
		model.addAttribute("loginaccount", loginaccount);
		
		model.addAttribute("shelfAuthors", shelfAuthorService.findByEraseAndOrderId(false));
		
		
		if (id.equals("new")) {
			model.addAttribute("author", new AccountAuthor());
		} else {
			model.addAttribute("author", accountAuthorService.findByID(id));
		}
		
		if(shelfId == null) {
			model.addAttribute("authors", accountAuthorService.findByErase(false));
		}else {
			List<AccountAuthor> authors = accountAuthorService.findByErase(false);
			List<AccountAuthor> authors2 = new ArrayList<>();
			for(AccountAuthor a : authors) {
				if(a.getShelfAuthor().getId() == Long.parseLong(shelfId)) {
					authors2.add(a);
				}
			}
			model.addAttribute("authors", authors2);
		}

		
		return "adminaccountauthors";
	}
	
	@PostMapping("/authors/save")
	public String save(@ModelAttribute("author") AccountAuthor accountAuthor, String shelfId, @CookieValue("login_user_id") String cookieId, RedirectAttributes redirAttrs) {	
		System.out.println("Shelf ID " + shelfId);
		System.out.println("Name " + accountAuthor.getName());
		System.out.println("Username " + accountAuthor.getUsername());
		System.out.println("Password " + accountAuthor.getPassword());
		
		if(shelfId == null || accountAuthor.getName().isEmpty() || accountAuthor.getUsername().isEmpty() || accountAuthor.getPassword().isEmpty()){
			redirAttrs.addFlashAttribute("validation","Data is missing");		
		}else {
			if(accountAuthor.getId().isEmpty()) {
				accountAuthor.setSecurityInfo(new SecurityInfo(cookieId));
				accountAuthorService.save(accountAuthor, Long.parseLong(shelfId));
				redirAttrs.addFlashAttribute("save","Account Author save successfully");
			}else {
					AccountAuthor accountAuthor2 = accountAuthorService.findByID(accountAuthor.getId());
					accountAuthor2.setName(accountAuthor.getName());
					System.out.println(accountAuthor.getName());
					accountAuthor2.setUsername(accountAuthor.getUsername());
					accountAuthor2.setPassword(accountAuthor.getPassword());
					accountAuthor2.getSecurityInfo().setUpdateDate(LocalDate.now());
					accountAuthor2.getSecurityInfo().setUpdateTime(LocalTime.now().toString());
					accountAuthor2.getSecurityInfo().setUpdateUser(cookieId);
					accountAuthorService.save(accountAuthor2,Long.parseLong(shelfId));	
					redirAttrs.addFlashAttribute("update", "Account Author update successfully");	
			}
		}
		
		return "redirect:/admin/account/authors/new";
	}
	
	@GetMapping("/authors/delete/{id}")
	public String delete(@PathVariable String id , RedirectAttributes redirAttrs) {
		accountAuthorService.delete(accountAuthorService.findByID(id));
		redirAttrs.addFlashAttribute("validation","Account Author delete successfully");
		return "redirect:/admin/account/authors/new";

	}
	
}
