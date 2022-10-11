package com.redbox.pkdm.controllers.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.redbox.pkdm.entities.AccountAuthor;
import com.redbox.pkdm.services.AccountAuthorService;
import com.redbox.pkdm.services.ShelfFeatureService;

@Controller
@RequestMapping("author/shelf")
public class AuthorShelfFeatureController {
	
	@Autowired
	private ShelfFeatureService shelfFeatureService;
	
	@Autowired
	private AccountAuthorService accountAuthorService;
	
	@GetMapping("/features")
	public String showData(Model model, @CookieValue("login_user_id") String cookieId) throws Exception {
		
		AccountAuthor loginaccount = accountAuthorService.findByID(cookieId);
		if (loginaccount == null) {
			throw new Exception();
		}
		model.addAttribute("loginaccount", loginaccount);
		
		
		model.addAttribute("shelfFeatures", shelfFeatureService.findByEraseAndOrderId(false));
		
		return "authorshelffeature";
	}

}
