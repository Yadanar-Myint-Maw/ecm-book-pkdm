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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.redbox.pkdm.entities.AccountAdmin;
import com.redbox.pkdm.entities.AccountUser;
import com.redbox.pkdm.entities.DiscountCouponMapper;
import com.redbox.pkdm.entities.SecurityInfo;
import com.redbox.pkdm.services.AccountAdminService;
import com.redbox.pkdm.services.AccountUserService;
import com.redbox.pkdm.services.DiscountCouponMapperService;
import com.redbox.pkdm.services.DiscountCouponService;

@Controller
@RequestMapping("admin/account")
public class DiscountCouponMapperController {
	
	@Autowired
	private DiscountCouponMapperService discountCouponMapperService;
	
	@Autowired
	private DiscountCouponService discountCouponService;
	
	@Autowired
	private AccountUserService accountUserService;
	
	@Autowired
	private AccountAdminService accountAdminService;
	
	@GetMapping("/discountcouponmappers/{id}")
	public String initialize(@PathVariable String id, @CookieValue("login_user_id") String cookieId, Model model, String couId) throws Exception {
		AccountAdmin loginaccount = accountAdminService.findByID(cookieId);
		if (loginaccount == null) {
			throw new Exception();
		}
		model.addAttribute("loginaccount", loginaccount);
		model.addAttribute("discountcoupons", discountCouponService.findByErase(false));
		model.addAttribute("accountusers", accountUserService.findByErase(false));
		if(id.equals("new")) {
			model.addAttribute("discountcouponmapper", new DiscountCouponMapper());
		}else {
			model.addAttribute("discountcouponmapper", discountCouponMapperService.findByID(Long.parseLong(id)));
		}
		if(couId == null) {
			model.addAttribute("discountcouponmappers", discountCouponMapperService.findByErase(false));
		}else {

			model.addAttribute("discountcouponmappers", discountCouponMapperService.findByID(Long.parseLong(couId)));
		}
		return "admindiscountcouponmappers";
	}

	@PostMapping("/discountcouponmappers/save")
	public String save(@ModelAttribute("discountcouponmapper") DiscountCouponMapper discountCouponMapper, @CookieValue("login_user_id") String cookieId, Model model, RedirectAttributes redirAttrs, @RequestParam String disId, @RequestParam String userId){
		if (disId.equals("notselected") || userId.equals("notselected")) {
			redirAttrs.addFlashAttribute("validation", "Data is missing!");
		}else {
			if(discountCouponMapper.getId() == 0) {
				discountCouponMapper.setSecurityInfo(new SecurityInfo(cookieId));
				discountCouponMapperService.save(discountCouponMapper, userId, Long.parseLong(disId));
				redirAttrs.addFlashAttribute("save", "Discount Coupon Mapper save successfully.");
			}else {
				DiscountCouponMapper discountCouponMapper2 = discountCouponMapperService.findByID(discountCouponMapper.getId());
				discountCouponMapper2.setDiscountCoupon(discountCouponMapper.getDiscountCoupon());
				discountCouponMapper2.setAccountUser(discountCouponMapper.getAccountUser());
				discountCouponMapper2.getSecurityInfo().setUpdateDate(LocalDate.now());
				discountCouponMapper2.getSecurityInfo().setUpdateTime(LocalTime.now().toString());
				discountCouponMapper2.getSecurityInfo().setUpdateUser(cookieId);
				discountCouponMapperService.save(discountCouponMapper2, userId, Long.parseLong(disId));
				redirAttrs.addFlashAttribute("update", "Discount Coupon Mapper update successfully.");
			}
		}
		return "redirect:/admin/account/discountcouponmappers/new";
	}
	
	
	@GetMapping("discountcouponmappers/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes redirAttrs) {
		discountCouponMapperService.delete(discountCouponMapperService.findByID(id));
		redirAttrs.addFlashAttribute("validation", "Admin Account delete successfully!");
		return "redirect:/admin/account/discountcouponmappers/new";
	}

}
