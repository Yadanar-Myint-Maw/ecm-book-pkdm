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
import com.redbox.pkdm.entities.AdvertisingMajorBannerMapper;
import com.redbox.pkdm.entities.SecurityInfo;
import com.redbox.pkdm.services.AccountAdminService;
import com.redbox.pkdm.services.AdvertisingMajorBannerMapperService;
import com.redbox.pkdm.services.AdvertisingMajorBannerService;


@Controller
@RequestMapping("admin/advertising")
public class AdvertisingMajorBannerMapperController {
	
	@Autowired
	private AdvertisingMajorBannerMapperService advertisingMajorBannerMapperService;
	
	@Autowired
	private AdvertisingMajorBannerService advertisingMajorBannerService;
	
	@Autowired
	private AccountAdminService accountAdminService;
	
	@GetMapping("/majorbannermappers/{id}")
	public String initialize(@PathVariable String id, @CookieValue("login_user_id") String cookieId, Model model, String advId) throws Exception{
		
		AccountAdmin loginaccount = accountAdminService.findByID(cookieId);
		if (loginaccount == null) {
			throw new Exception();
		}
		model.addAttribute("loginaccount", loginaccount);
		
		model.addAttribute("advMajorBanners", advertisingMajorBannerService.findByErase(false));
		
		
		if (id.equals("new")) {
			model.addAttribute("advMajorBannerMapper", new AdvertisingMajorBannerMapper());
		} else {
			model.addAttribute("advMajorBannerMapper", advertisingMajorBannerMapperService.findByID(Long.parseLong(id)));
		}
		
		if (advId == null) {
			model.addAttribute("majorbannermappers", advertisingMajorBannerMapperService.findByErase(false));
		} else {
			
			model.addAttribute("majorbannermappers", advertisingMajorBannerMapperService.findByID(Long.parseLong(advId)));
		}
		return "adminadvertisingmajorbannermapper";
	}
	
	@PostMapping("/majorbannermappers/save")
	public String save(@ModelAttribute("advMajorBannerMapper") AdvertisingMajorBannerMapper advMajorBannerMapper,@RequestParam String bannerId, @CookieValue("login_user_id") String cookieId, RedirectAttributes redirAttrs) {
		
		if(bannerId.equals("notselected") || advMajorBannerMapper.getStartDate() == null || advMajorBannerMapper.getEndDate() == null || advMajorBannerMapper.getFee() == 0) {
			redirAttrs.addFlashAttribute("validation","Data is missing");
		}else {
			if (advMajorBannerMapper.getId() == 0) {
				advMajorBannerMapper.setSecurityInfo(new SecurityInfo(cookieId));
				advertisingMajorBannerMapperService.save(advMajorBannerMapper, Long.parseLong(bannerId));
				redirAttrs.addFlashAttribute("save", "Advertising Major Banner Mapper save successfully");
			} 
			else {
				
			AdvertisingMajorBannerMapper advMajorBannerMapper2 = advertisingMajorBannerMapperService.findByID(advMajorBannerMapper.getId());
			advMajorBannerMapper2.setStartDate(advMajorBannerMapper.getStartDate());
			advMajorBannerMapper2.setEndDate(advMajorBannerMapper.getEndDate());
			advMajorBannerMapper2.setFee(advMajorBannerMapper.getFee());
			advMajorBannerMapper2.getSecurityInfo().setUpdateDate(LocalDate.now());
			advMajorBannerMapper2.getSecurityInfo().setUpdateTime(LocalTime.now().toString());
			advMajorBannerMapper2.getSecurityInfo().setUpdateUser(cookieId);
			advertisingMajorBannerMapperService.save(advMajorBannerMapper2, Long.parseLong(bannerId));	
			redirAttrs.addFlashAttribute("update", "Advertising Major Banner Mapper update successfully");
			}
		}
		
		
		
		return "redirect:/admin/advertising/majorbannermappers/new";
		
	}
	
	@GetMapping("/majorbannermappers/delete/{id}")
	public String delete(@PathVariable String id, RedirectAttributes redirAttrs) {
		advertisingMajorBannerMapperService.delete(advertisingMajorBannerMapperService.findByID(Long.parseLong(id)));
		redirAttrs.addFlashAttribute("validation", "Advertising Major Banner Mapper delete successfully");
		return "redirect:/admin/advertising/majorbannermappers/new";
	}

}
