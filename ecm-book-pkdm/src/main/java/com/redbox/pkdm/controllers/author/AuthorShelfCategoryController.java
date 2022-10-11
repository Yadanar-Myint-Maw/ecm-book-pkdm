package com.redbox.pkdm.controllers.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.redbox.pkdm.entities.AccountAuthor;
import com.redbox.pkdm.services.AccountAuthorService;
import com.redbox.pkdm.services.ShelfCategoryService;

@Controller
@RequestMapping("author/shelf")
public class AuthorShelfCategoryController {
	
	@Autowired
	private ShelfCategoryService shelfCategoryService;
	
	@Autowired
	private AccountAuthorService accountAuthorService;
	
	@GetMapping("/categories")
	public String showData(Model model, @CookieValue("login_user_id") String cookieId) throws Exception {
		
		AccountAuthor loginaccount = accountAuthorService.findByID(cookieId);
		if (loginaccount == null) {
			throw new Exception();
		}
		model.addAttribute("loginaccount", loginaccount);
		
		
		model.addAttribute("shelfCategories", shelfCategoryService.findByEraseAndOrderId(false));
		
		return "authorshelfcategory";
	}

}
