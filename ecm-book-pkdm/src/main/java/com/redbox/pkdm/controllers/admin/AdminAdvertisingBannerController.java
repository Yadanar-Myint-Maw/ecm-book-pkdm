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
import com.redbox.pkdm.entities.SecurityInfo;
import com.redbox.pkdm.services.AccountAdminService;
import com.redbox.pkdm.services.AdvertisingMajorBannerService;
import com.redbox.pkdm.utilities.ImageUploadUtility;
import com.redbox.pkdm.utilities.MessageUtility;

@Controller
@RequestMapping("admin/advertising/banner")
public class AdminAdvertisingBannerController {
	
	
	@Autowired
	private AdvertisingMajorBannerService advertisingMajorBannerService;
	
	@Autowired
	private AccountAdminService accountAdminService;
	
	@GetMapping("/initialize/{id}")
	public String initialize(@PathVariable String id,@CookieValue("login_user_id") String cookieId, Model model, String keyword) throws Exception{
		
		model.addAttribute("advMajorBanners", advertisingMajorBannerService.findByErase(false));
		
		AccountAdmin loginaccount = accountAdminService.findByID(cookieId);
		if (loginaccount == null) {
			throw new Exception();
		}
		model.addAttribute("loginaccount", loginaccount);
		if (id.equals("0")) {
			model.addAttribute("advMajorBanner", new AdvertisingMajorBanner());
		} else {
			model.addAttribute("advMajorBanner", advertisingMajorBannerService.findByID(Long.parseLong(id)));
		}
		
		if (keyword == null) {
			model.addAttribute("majorbanners", advertisingMajorBannerService.findByErase(false));
		} else {
			
			model.addAttribute("majorbanners", advertisingMajorBannerService.findByID(Long.parseLong(keyword)));
		}
		
		return "adminadvertisingbanner";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("advMajorBanner") AdvertisingMajorBanner advMajorBanner, @CookieValue("login_user_id") String cookieId, RedirectAttributes redirAttrs) {
		
		if(advMajorBanner.getFile().isEmpty()) {
			redirAttrs.addFlashAttribute("validation", MessageUtility.getDataIsMissingMessage());
		}else {
			
			if (advMajorBanner.getId() == 0) {
				advMajorBanner.setSecurityInfo(new SecurityInfo(cookieId));
				
				
			if(	advMajorBanner.getFile() != null) {
				advMajorBanner.setImage(ImageUploadUtility.upload(advMajorBanner.getFile()));
			}
				advertisingMajorBannerService.save(advMajorBanner);
				redirAttrs.addFlashAttribute("save", MessageUtility.getSaveSuccessMessage("Banner"));
			} else {
				AdvertisingMajorBanner advMajorBanner2 = advertisingMajorBannerService.findByID(advMajorBanner.getId());
				if(advMajorBanner.getFile() != null) {
					advMajorBanner2.setImage(ImageUploadUtility.upload(advMajorBanner.getFile()));
				}
				advMajorBanner.getSecurityInfo().setUpdateDate(LocalDate.now());
				advMajorBanner.getSecurityInfo().setUpdateTime(LocalTime.now().toString());
				advMajorBanner.getSecurityInfo().setUpdateUser(cookieId);
				advertisingMajorBannerService.save(advMajorBanner2);	
				redirAttrs.addFlashAttribute("update", MessageUtility.getUpdateSuccessMessage("Banner"));
			}
			
		}
		
		return "redirect:/admin/advertising/banner/initialize/0";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable String id, RedirectAttributes redirAttrs) {
		advertisingMajorBannerService.delete(advertisingMajorBannerService.findByID(Long.parseLong(id)));
		redirAttrs.addFlashAttribute("validation", MessageUtility.getDeleteSuccesMessage("Banner"));
		return "redirect:/admin/advertising/banner/initialize/0";
	}

}
	

