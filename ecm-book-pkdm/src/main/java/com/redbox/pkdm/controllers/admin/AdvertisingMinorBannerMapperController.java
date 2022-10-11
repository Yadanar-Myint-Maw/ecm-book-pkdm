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
import com.redbox.pkdm.entities.AdvertisingMinorBannerMapper;
import com.redbox.pkdm.entities.SecurityInfo;
import com.redbox.pkdm.services.AccountAdminService;
import com.redbox.pkdm.services.AdvertisingMinorBannerMapperService;
import com.redbox.pkdm.services.AdvertisingMinorBannerService;

@Controller
@RequestMapping("admin/advertising")
public class AdvertisingMinorBannerMapperController {
	
	@Autowired
	private AdvertisingMinorBannerMapperService advertisingMinorBannerMapperService;
	
	@Autowired
	private AdvertisingMinorBannerService advertisingMinorBannerService;
	
	@Autowired
	private AccountAdminService accountAdminService;
	
	@GetMapping("/minorbannermappers/{id}")
	public String initialize(@PathVariable String id,@CookieValue("login_user_id") String cookieId, Model model, String advId) throws Exception {
		
		AccountAdmin loginaccount = accountAdminService.findByID(cookieId);
		if (loginaccount == null) {
			throw new Exception();
		}
		model.addAttribute("loginaccount", loginaccount);
		
		model.addAttribute("advMinorBanners", advertisingMinorBannerService.findByErase(false));
		
		
		if (id.equals("new")) {
			model.addAttribute("advMinorBannerMapper", new AdvertisingMinorBannerMapper());
		} else {
			model.addAttribute("advMinorBannerMapper", advertisingMinorBannerMapperService.findByID(Long.parseLong(id)));
		}
		
		if (advId == null) {
			model.addAttribute("minorbannermappers", advertisingMinorBannerMapperService.findByErase(false));
		} else {
			
			model.addAttribute("minorbannermappers", advertisingMinorBannerMapperService.findByID(Long.parseLong(advId)));
		}
		return "adminadvertisingminorbannermapper";
	}
	
	@PostMapping("/minorbannermappers/save")
	public String save(@ModelAttribute("advMinorBannerMapper") AdvertisingMinorBannerMapper advMinorBannerMapper,@RequestParam String bannerId, @CookieValue("login_user_id") String cookieId, RedirectAttributes redirAttrs) {
		if(bannerId.equals("notselected") || advMinorBannerMapper.getStartDate() == null || advMinorBannerMapper.getEndDate() == null || advMinorBannerMapper.getFee() == 0) {
			redirAttrs.addFlashAttribute("validation","Data is missing");
		}else {
			
			if (advMinorBannerMapper.getId() == 0) {
				advMinorBannerMapper.setSecurityInfo(new SecurityInfo(cookieId));
				advertisingMinorBannerMapperService.save(advMinorBannerMapper, Long.parseLong(bannerId));
				redirAttrs.addFlashAttribute("save", "Advertising Minor Banner Mapper save successfully");
			} else {
			
			AdvertisingMinorBannerMapper advMinorBannerMapper2 = advertisingMinorBannerMapperService.findByID(advMinorBannerMapper.getId());
			advMinorBannerMapper2.setStartDate(advMinorBannerMapper.getStartDate());
			advMinorBannerMapper2.setEndDate(advMinorBannerMapper.getEndDate());
			advMinorBannerMapper2.setFee(advMinorBannerMapper.getFee());
			
			
			advMinorBannerMapper2.getSecurityInfo().setUpdateDate(LocalDate.now());
			advMinorBannerMapper2.getSecurityInfo().setUpdateTime(LocalTime.now().toString());
			advMinorBannerMapper2.getSecurityInfo().setUpdateUser(cookieId);
			advertisingMinorBannerMapperService.save(advMinorBannerMapper2, Long.parseLong(bannerId));	
			redirAttrs.addFlashAttribute("update", "Advertising Minor Banner Mapper update successfully");
		   }
			
		}
		return "redirect:/admin/advertising/minorbannermappers/new";
		
	}
	
	@GetMapping("/minorbannermappers/delete/{id}")
	public String delete(@PathVariable String id, RedirectAttributes redirAttrs) {
		advertisingMinorBannerMapperService.delete(advertisingMinorBannerMapperService.findByID(Long.parseLong(id)));
		redirAttrs.addFlashAttribute("validation", "Advertising Major Banner Mapper delete successfully");
		return "redirect:/admin/advertising/minorbannermappers/new";
	}

}
