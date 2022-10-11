package com.redbox.pkdm.controllers.admin;

import com.redbox.pkdm.services.BookService;
import com.redbox.pkdm.services.DiscountCouponService;
import com.redbox.pkdm.services.ShelfAuthorService;
import com.redbox.pkdm.services.ShelfCategoryService;
import com.redbox.pkdm.services.ShelfFeatureService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("author/general")
public class AuthorDashboardController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private ShelfAuthorService shelfAuthorService;
	
	@Autowired
	private ShelfCategoryService shelfCategoryService;
	
	@Autowired
	private ShelfFeatureService shelfFeatureService;
	
	@Autowired
	private DiscountCouponService discountCouponService;
	
	@GetMapping("/dashboard")
	public String initialize(Model model) {
//		model.addAttribute("shelfAuthorTotal", shelfAuthorService.findCountByShelfAuthor());
//		model.addAttribute("shelfFeatureTotal", shelfFeatureService.findCountByShelfFeature());
//		model.addAttribute("shelfCategoryTotal", shelfCategoryService.findCountByShelfCategory());
//		model.addAttribute("couponTotal" , discountCouponService.findCountByDiscountCoupon());
//		model.addAttribute("ebookTotal", bookService.findByCountEBook("Series"));
//		model.addAttribute("pbookTotal", bookService.findByCountPBook("LoneChinn"));
		
		model.addAttribute("shelfAuthorTotal", 0);
		model.addAttribute("shelfFeatureTotal", 0);
		model.addAttribute("shelfCategoryTotal", 0);
		model.addAttribute("couponTotal" , 0);
		model.addAttribute("ebookTotal", 0);
		model.addAttribute("pbookTotal", 0);
		return "authorgeneraldashboard";
	}

}
