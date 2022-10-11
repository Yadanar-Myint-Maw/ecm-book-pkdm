package com.redbox.pkdm.controllers.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.redbox.pkdm.entities.AccountAuthor;
import com.redbox.pkdm.services.AccountAuthorService;
import com.redbox.pkdm.services.DiscountCouponService;

@Controller
@RequestMapping("author/discount")
public class AuthorDiscountCouponController {
	
	@Autowired
	private DiscountCouponService discountCouponService;
	
	@Autowired
	private AccountAuthorService accountAuthorService;
	
	@GetMapping("/coupon")
	public String showData(Model model, @CookieValue("login_user_id") String cookieId) throws Exception {
		
		AccountAuthor loginaccount = accountAuthorService.findByID(cookieId);
		if (loginaccount == null) {
			throw new Exception();
		}
		model.addAttribute("loginaccount", loginaccount);
		
		
		model.addAttribute("discountCoupon", discountCouponService.findByErase(false));
		
		return "authordiscountcoupon";
	}

}
