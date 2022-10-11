package com.redbox.pkdm.controllers.admin;


import java.time.LocalDate;
import java.time.LocalTime;

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
import com.redbox.pkdm.entities.DiscountCoupon;
import com.redbox.pkdm.entities.SecurityInfo;
import com.redbox.pkdm.services.AccountAdminService;
import com.redbox.pkdm.services.DiscountCouponService;

@Controller
@RequestMapping("admin/discount")
public class DiscountCouponController {

	@Autowired
	private DiscountCouponService discountCouponService;
	
	@Autowired
	private AccountAdminService accountAdminService;
	
	@GetMapping("/usercoupons/{id}")
	public String initialize(@PathVariable String id, @CookieValue("login_user_id") String cookieId, Model model, String name, String dateFrom, String dateTo) throws Exception {
		AccountAdmin loginaccount = accountAdminService.findByID(cookieId); 
		if (loginaccount == null) {
			throw new Exception();
		}
		model.addAttribute("loginaccount", loginaccount);
		if(id.equals("new")) {
			model.addAttribute("discountcoupon", new DiscountCoupon());
		}else {
			model.addAttribute("discountcoupon", discountCouponService.findByID(Long.parseLong(id)));
		}
		
		if (dateFrom == null || dateTo == null || dateFrom.isEmpty() || dateTo.isEmpty()) {
			model.addAttribute("discountcoupons", discountCouponService.findByErase(false));
		} else {
			if (dateFrom.equals(dateTo)) {
				LocalDate date1 = LocalDate.parse(dateFrom);
				model.addAttribute("discountcoupons", discountCouponService.findBySingleDate(date1));	
			} else {
				LocalDate date1 = LocalDate.parse(dateFrom);
				LocalDate date2 = LocalDate.parse(dateTo);
				model.addAttribute("discountcoupons", discountCouponService.findByDateFromAndDateTo(date1, date2));	
			}
		}
		
		return "admindiscountusercoupons";
	}
	
	
	@PostMapping("/usercoupon/save")
	public String save(@ModelAttribute("discountcoupon") DiscountCoupon discountCoupon, @CookieValue("login_user_id")String cookieId, Model model, RedirectAttributes redirAttrs) {
		if (discountCoupon.getName().isEmpty() || discountCoupon.getDescription().isEmpty() || discountCoupon.getStartDate() == null || discountCoupon.getEndDate() == null) {
			redirAttrs.addFlashAttribute("validation", "Data is missing!");
		}else {
			if(discountCoupon.getId() == 0) {
				discountCoupon.setSecurityInfo(new SecurityInfo(cookieId));
				discountCouponService.save(discountCoupon);
				redirAttrs.addFlashAttribute("save", "Discount Coupon save successfully.");
		}else {
			DiscountCoupon discountCoupon2 = discountCouponService.findByID(discountCoupon.getId());
			discountCoupon2.setName(discountCoupon.getName());
			discountCoupon2.setDescription(discountCoupon.getDescription());
			discountCoupon2.setStartDate(LocalDate.now());
			discountCoupon2.setEndDate(LocalDate.now());
			discountCoupon2.setActive(true);
			discountCoupon2.getSecurityInfo().setUpdateDate(LocalDate.now());
			discountCoupon2.getSecurityInfo().setUpdateTime(LocalTime.now().toString());
			discountCoupon2.getSecurityInfo().setUpdateUser(cookieId);
			discountCouponService.save(discountCoupon2);
			redirAttrs.addFlashAttribute("update", "Discount Coupon update successfully.");
			}
		}
		return "redirect:/admin/discount/usercoupons/new";
	}
	
	
	@GetMapping("/usercoupon/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes redirAttrs) {
		discountCouponService.delete(discountCouponService.findByID(id));
		redirAttrs.addFlashAttribute("validation", "Discount Coupon delete successfully!");
		return "redirect:/admin/discount/usercoupons/new";
	}
	
}
