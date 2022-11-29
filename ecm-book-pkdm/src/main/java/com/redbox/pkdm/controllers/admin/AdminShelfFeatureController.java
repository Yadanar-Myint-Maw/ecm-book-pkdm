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
import com.redbox.pkdm.entities.SecurityInfo;
import com.redbox.pkdm.entities.ShelfFeature;
import com.redbox.pkdm.services.AccountAdminService;
import com.redbox.pkdm.services.ShelfFeatureService;

@Controller
@RequestMapping("admin/shelf/feature")
public class AdminShelfFeatureController {
	

	@Autowired
	private ShelfFeatureService shelfFeatureService;
	
	@Autowired
	private AccountAdminService accountAdminService;
	

	@GetMapping("/initialize/{id}")
	public String initialize(@PathVariable String id, Model model, String keyword, @CookieValue("login_user_id") String cookieId) throws Exception {
		
		AccountAdmin loginaccount = accountAdminService.findByID(cookieId);
		if (loginaccount == null) {
			throw new Exception();
		}
		model.addAttribute("loginaccount", loginaccount);	
		if (id.equals("0")) {
			model.addAttribute("shelfFeature", new ShelfFeature());
		} else {
			model.addAttribute("shelfFeature", shelfFeatureService.findByID(Long.parseLong(id)));
			}
			if (keyword == null) {		
				model.addAttribute("shelfFeatures", shelfFeatureService.findByEraseAndOrderId(false));
		}
			else {
//			List<ShelfFeature> shelfFeatures = shelfFeatureService.findByEraseAndOrderId(false);
//			List<ShelfFeature> shelfFeatures2 = new ArrayList<>();
//			for (ShelfFeature s : shelfFeatures) {
//				if (s.getName().toLowerCase().contains(keyword.toLowerCase())) {
//				shelfFeatures2.add(s);
//			}
//		}
			model.addAttribute("shelfFeatures", shelfFeatureService.findByNameLike(keyword));
			}
		return "adminshelffeature";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("shelfFeature") ShelfFeature shelfFeature, @CookieValue("login_user_id") String cookieId , RedirectAttributes redirAttrs) {	
		if(shelfFeature.getName().isEmpty() ){
			redirAttrs.addFlashAttribute("validation", "Data is missing");
		}else {
			if (shelfFeature.getId() == 0) {
			shelfFeature.setSecurityInfo(new SecurityInfo(cookieId));
			shelfFeatureService.save(shelfFeature);
			redirAttrs.addFlashAttribute("save", "Shelf Feature save successfully");
		} else {
			ShelfFeature shelfFeature2 = shelfFeatureService.findByID(shelfFeature.getId());
			shelfFeature2.setName(shelfFeature.getName());
			shelfFeature2.setDescription(shelfFeature.getDescription());
			shelfFeature2.getSecurityInfo().setUpdateDate(LocalDate.now());
			shelfFeature2.getSecurityInfo().setUpdateTime(LocalTime.now().toString());
			shelfFeature2.getSecurityInfo().setUpdateUser(cookieId);
			shelfFeatureService.save(shelfFeature2);	
			redirAttrs.addFlashAttribute("update", "Shelf Category update successfully");
		}
	}
		return "redirect:/admin/shelf/feature/initialize/0";
	}
	
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable String id, RedirectAttributes redirAttrs) {
		shelfFeatureService.delete(shelfFeatureService.findByID(Long.parseLong(id)));
		redirAttrs.addFlashAttribute("validation", "Shelf Category delete successfully");
		return "redirect:/admin/shelf/feature/initialize/0";
	}
}
