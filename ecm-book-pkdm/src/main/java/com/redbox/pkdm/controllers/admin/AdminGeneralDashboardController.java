package com.redbox.pkdm.controllers.admin;

import com.redbox.pkdm.entities.AccountAdmin;
import com.redbox.pkdm.services.AccountAdminService;
import com.redbox.pkdm.services.AccountAuthorService;
import com.redbox.pkdm.services.AccountUserService;
import com.redbox.pkdm.services.AdvertisingMajorBannerService;
import com.redbox.pkdm.services.AdvertisingMinorBannerService;
import com.redbox.pkdm.services.BookService;
import com.redbox.pkdm.services.DeliveryRegionService;
import com.redbox.pkdm.services.DeliveryTownshipService;
import com.redbox.pkdm.services.DiscountCouponService;
import com.redbox.pkdm.services.ShelfAuthorService;
import com.redbox.pkdm.services.ShelfCategoryService;
import com.redbox.pkdm.services.ShelfFeatureService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/")
public class AdminGeneralDashboardController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired	
	private AccountAdminService accountAdminService;
	
	@Autowired
	private AccountAuthorService accountAuthorService;
	
	@Autowired
	private AccountUserService accountUserService;
	
	@Autowired
	private ShelfAuthorService shelfAuthorService;
	
	@Autowired
	private ShelfCategoryService shelfCategoryService;
	
	@Autowired
	private ShelfFeatureService shelfFeatureService;
	
	@Autowired
	private DiscountCouponService discountCouponService;
	
	@Autowired
	private AdvertisingMajorBannerService advertisingMajorBannerService;
	
	@Autowired
	private AdvertisingMinorBannerService advertisingMinorBannerService;
	
	@Autowired
	private DeliveryRegionService deliveryRegionService;
	
	@Autowired
	private DeliveryTownshipService deliveryTownshipService;
	
	@GetMapping("/general/dashboard")
	public String initialize(Model model, String keyword,  @CookieValue("login_user_id") String cookieId) {
		AccountAdmin loginaccount = accountAdminService.findByID(cookieId);
		if (loginaccount == null) {
			return "signin";
		}
//		model.addAttribute("adminTotal", accountAdminService.findCountByAdmin());
//		model.addAttribute("authorTotal", accountAuthorService.findCountByAuthor());
//		model.addAttribute("userlevel1Total", accountUserService.findByCountUser("Level-1"));
//		model.addAttribute("userlevel2Total", accountUserService.findByCountUserLevel2("Level-2"));
//		model.addAttribute("ebookTotal", bookService.findByCountEBook("Series"));
//		model.addAttribute("pbookTotal", bookService.findByCountPBook("LoneChinn"));
//		model.addAttribute("shelfAuthorTotal", shelfAuthorService.findCountByShelfAuthor());
//		model.addAttribute("shelfFeatureTotal", shelfFeatureService.findCountByShelfFeature());
//		model.addAttribute("shelfCategoryTotal", shelfCategoryService.findCountByShelfCategory());
//		model.addAttribute("couponTotal" , discountCouponService.findCountByDiscountCoupon());
//		model.addAttribute("majorBannerTotal" , advertisingMajorBannerService.findCountByMajorBanner());
//		model.addAttribute("minorTotal", advertisingMinorBannerService.findCountByMinorBanner());
//		model.addAttribute("regionTotal", deliveryRegionService.findCountByDeliveryRegion());
//		model.addAttribute("townshipTotal", deliveryTownshipService.findCountByDeliveryTownship());

		model.addAttribute("adminTotal", accountAdminService.findCountByAdmin());
		model.addAttribute("authorTotal", accountAuthorService.findCountByAuthor());
		model.addAttribute("userlevel1Total", accountUserService.findByCountUser("Level-1"));
		model.addAttribute("userlevel2Total", accountUserService.findByCountUserLevel2("Level-2"));
		model.addAttribute("ebookTotal", bookService.findByCountEBook("Series"));
		model.addAttribute("pbookTotal", bookService.findByCountPBook("LoneChinn"));
		model.addAttribute("shelfAuthorTotal", shelfAuthorService.findCountByShelfAuthor());
		model.addAttribute("shelfFeatureTotal", shelfFeatureService.findCountByShelfFeature());
		model.addAttribute("shelfCategoryTotal", shelfCategoryService.findCountByShelfCategory());
		model.addAttribute("couponTotal" , discountCouponService.findCountByDiscountCoupon());
		model.addAttribute("majorBannerTotal" , advertisingMajorBannerService.findCountByMajorBanner());
		model.addAttribute("minorTotal", advertisingMinorBannerService.findCountByMinorBanner());
		model.addAttribute("regionTotal", deliveryRegionService.findCountByDeliveryRegion());
		model.addAttribute("townshipTotal", deliveryTownshipService.findCountByDeliveryTownship());
		
		return "admingeneraldashboard";
		
	}

}
