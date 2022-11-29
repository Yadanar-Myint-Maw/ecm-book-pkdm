package com.redbox.pkdm.controllers.admin;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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
import com.redbox.pkdm.entities.DeliveryRegion;
import com.redbox.pkdm.entities.SecurityInfo;
import com.redbox.pkdm.services.AccountAdminService;
import com.redbox.pkdm.services.DeliveryRegionService;

@Controller
@RequestMapping("admin/delivery/region")
public class AdminDeliveryRegionController {
	
	@Autowired
	private DeliveryRegionService deliveryRegionService;
	
	@Autowired
	private AccountAdminService accountAdminService;
	

	@GetMapping("/initialize/{id}")
	public String initialize(@PathVariable String id,@CookieValue("login_user_id") String cookieId, Model model, String keyword) throws Exception {
		
		AccountAdmin loginaccount = accountAdminService.findByID(cookieId);
		if (loginaccount == null) {
			throw new Exception();
		}
		model.addAttribute("loginaccount", loginaccount);
		
		if (id.equals("0")) {
			model.addAttribute("deliveryRegion", new DeliveryRegion());
		} else {
			model.addAttribute("deliveryRegion", deliveryRegionService.findByID(Long.parseLong(id)));
		}
		if (keyword == null) {
			model.addAttribute("regions", deliveryRegionService.findByErase(false));
		} else {
			List<DeliveryRegion> regions = deliveryRegionService.findByErase(false);
			List<DeliveryRegion> regions2 = new ArrayList<>();
			for (DeliveryRegion d : regions) {
				if (d.getName().toLowerCase().contains(keyword.toLowerCase())) {
					regions2.add(d);
				}
			}
			model.addAttribute("regions", regions2);
		}
		return "admindeliveryregion";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("deliveryRegion") DeliveryRegion deliveryRegion, @CookieValue("login_user_id") String cookieId, RedirectAttributes redirAttrs) {
		
		if(deliveryRegion.getName().isEmpty()) {
			redirAttrs.addFlashAttribute("validation", "Data is missing!");
		}else {
			if (deliveryRegion.getId() == 0) {
				deliveryRegion.setSecurityInfo(new SecurityInfo(cookieId));
				deliveryRegionService.save(deliveryRegion);
				redirAttrs.addFlashAttribute("save","Delivery Region save successfully.");
			} else {
				DeliveryRegion deliveryRegion2 = deliveryRegionService.findByID(deliveryRegion.getId());
				deliveryRegion2.setName(deliveryRegion.getName());
				deliveryRegion2.getSecurityInfo().setUpdateDate(LocalDate.now());
				deliveryRegion2.getSecurityInfo().setUpdateTime(LocalTime.now().toString());
				deliveryRegion2.getSecurityInfo().setUpdateUser(cookieId);
				deliveryRegionService.save(deliveryRegion2);
				redirAttrs.addFlashAttribute("update","Delivery Region update successfully.");
			}
			
		}
		return "redirect:/admin/delivery/region/initialize/0";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable String id, RedirectAttributes redirAttrs) {
		deliveryRegionService.delete(deliveryRegionService.findByID(Long.parseLong(id)));
		redirAttrs.addFlashAttribute("validation","Delivery Region delete successfully");
		return "redirect:/admin/delivery/region/initialize/0";
	}

	
	

}
