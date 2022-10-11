package com.redbox.pkdm.controllers.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.redbox.pkdm.entities.AccountAuthor;
import com.redbox.pkdm.services.AccountAuthorService;
import com.redbox.pkdm.services.ShelfAuthorService;

@Controller
@RequestMapping("author/shelf")
public class AuthorShelfAuthorController {
	
	@Autowired
	private ShelfAuthorService shelfAuthorService;
	
	@Autowired
	private AccountAuthorService accountAuthorService;
	
	
	@GetMapping("/authors")
	public String showData(Model model, @CookieValue("login_user_id") String cookieId) throws Exception{
		
		AccountAuthor loginaccount = accountAuthorService.findByID(cookieId);
		if (loginaccount == null) {
			throw new Exception();
		}
		model.addAttribute("loginaccount", loginaccount);
		
	
		model.addAttribute("shelfauthors", shelfAuthorService.findByEraseAndOrderId(false));
		
		return "authorshelfauthor";
	}

}
