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
import com.redbox.pkdm.entities.AdvertisingMajorBanner;
import com.redbox.pkdm.entities.AdvertisingMinorBanner;
import com.redbox.pkdm.entities.SecurityInfo;
import com.redbox.pkdm.services.AccountAdminService;
import com.redbox.pkdm.services.AdvertisingMinorBannerService;
import com.redbox.pkdm.utilities.ImageUploadUtility;

@Controller
@RequestMapping("admin/advertising")
public class AdvertisingMinorBannerController {
	
	@Autowired
	private AdvertisingMinorBannerService advertisingMinorBannerService;
	
	@Autowired
	private AccountAdminService accountAdminService;
	
	@GetMapping("/minorbanners/{id}")
	public String initialize(@PathVariable String id,@CookieValue("login_user_id") String cookieId, Model model, String keyword) throws Exception {
		
		model.addAttribute("advMinorBanners", advertisingMinorBannerService.findByErase(false));
		
		
		AccountAdmin loginaccount = accountAdminService.findByID(cookieId);
		if (loginaccount == null) {
			throw new Exception();
		}
		model.addAttribute("loginaccount", loginaccount);
		
		
		if (id.equals("new")) {
			model.addAttribute("advMinorBanner", new AdvertisingMajorBanner());
		} else {
			model.addAttribute("advMinorBanner", advertisingMinorBannerService.findByID(Long.parseLong(id)));
		}
		
		if (keyword == null) {
			model.addAttribute("minorbanners", advertisingMinorBannerService.findByErase(false));
		} else {
			
			model.addAttribute("minorbanners", advertisingMinorBannerService.findByID(Long.parseLong(keyword)));
		}
		return "adminadvertisingminorbanner";
	}
	
	@PostMapping("/minorbanners/save")
	public String save(@ModelAttribute("advMinorBanner") AdvertisingMinorBanner advMinorBanner, @CookieValue("login_user_id") String cookieId, RedirectAttributes redirAttrs) {
		if(advMinorBanner.getFile().isEmpty()) {
			redirAttrs.addFlashAttribute("validation" , "Data is missing");
		}else {
			if (advMinorBanner.getId() == 0) {
				advMinorBanner.setSecurityInfo(new SecurityInfo(cookieId));
				
				if(advMinorBanner.getFile() != null) {
					advMinorBanner.setImage(ImageUploadUtility.upload(advMinorBanner.getFile()));
				}
				advertisingMinorBannerService.save(advMinorBanner);
				redirAttrs.addFlashAttribute("save","Advertising Minor Banner save successfully");
			} else {
				
				AdvertisingMinorBanner advMinorBanner2 = advertisingMinorBannerService.findByID(advMinorBanner.getId());
				if(advMinorBanner.getFile() != null) {
					advMinorBanner2.setImage(ImageUploadUtility.upload(advMinorBanner.getFile()));
				}
				advMinorBanner.getSecurityInfo().setUpdateDate(LocalDate.now());
				advMinorBanner.getSecurityInfo().setUpdateTime(LocalTime.now().toString());
				advMinorBanner.getSecurityInfo().setUpdateUser(cookieId);
				advertisingMinorBannerService.save(advMinorBanner2);		
				redirAttrs.addFlashAttribute("update","Advertising Major Banner update successfully");
			}
			
			
		}
		
		return "redirect:/admin/advertising/minorbanners/new";
	}
	
	@GetMapping("/minorbanners/delete/{id}")
	public String delete(@PathVariable String id, RedirectAttributes redirAttrs) {
		advertisingMinorBannerService.delete(advertisingMinorBannerService.findByID(Long.parseLong(id)));
		redirAttrs.addFlashAttribute("validation","Advertising Major Banner delete successfully");
		return "redirect:/admin/advertising/minorbanners/new";
	}
}
