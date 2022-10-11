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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.redbox.pkdm.entities.AccountAdmin;
import com.redbox.pkdm.entities.DeliveryTownship;
import com.redbox.pkdm.entities.SecurityInfo;
import com.redbox.pkdm.services.AccountAdminService;
import com.redbox.pkdm.services.DeliveryRegionService;
import com.redbox.pkdm.services.DeliveryTownshipService;

@Controller
@RequestMapping("admin/delivery")
public class DeliveryTownshipController {
	
	@Autowired
	private DeliveryTownshipService deliveryTownshipService;
	
	@Autowired
	private DeliveryRegionService deliveryRegionService;
	
	@Autowired
	private AccountAdminService accountAdminService;
	
	
	@GetMapping("/townships/{id}")
	public String initialize(@PathVariable String id,@CookieValue("login_user_id") String cookieId, Model model, String keyword,String regId) throws Exception {
		
		AccountAdmin loginaccount = accountAdminService.findByID(cookieId);
		if (loginaccount == null) {
			throw new Exception();
		}
		model.addAttribute("loginaccount", loginaccount);
		
		model.addAttribute("regions", deliveryRegionService.findByErase(false));
		if (id.equals("new")) {
			model.addAttribute("deliveryTownship", new DeliveryTownship());
		} else {
			model.addAttribute("deliveryTownship", deliveryTownshipService.findByID(Long.parseLong(id)));
		}
		
		List<DeliveryTownship> townships = new ArrayList<>();
		if (keyword == null && regId == null) {
			townships =  deliveryTownshipService.findByErase(false);
		} else if (keyword != null && !regId.equals("notselected")) {
			List<DeliveryTownship> townships1 = deliveryTownshipService.findByErase(false);
			for (DeliveryTownship t : townships1) {
				if (t.getName().toLowerCase().contains(keyword.toLowerCase()) && t.getDeliveryRegion().getId() == Long.parseLong(regId)) {
					townships.add(t);
				}
			}
		} else if (keyword != null && regId.equals("notselected")) {
			List<DeliveryTownship> townships1 = deliveryTownshipService.findByErase(false);
			for (DeliveryTownship t : townships1) {
				if (t.getName().toLowerCase().contains(keyword.toLowerCase())) {
					townships.add(t);
				}
			}
		} else if (keyword == null && !regId.equals("notselected")) {
			List<DeliveryTownship> townships1 = deliveryTownshipService.findByErase(false);
			for (DeliveryTownship t : townships1) {
				if (t.getDeliveryRegion().getId() == Long.parseLong(regId)) {
					townships.add(t);
				}
			}
		}
		model.addAttribute("townships", townships);
		return "admindeliverytownships";
	}
	
	@PostMapping("/townships/save")
	public String save(@ModelAttribute("deliveryTownship") DeliveryTownship deliveryTownship,@RequestParam String regId, @CookieValue("login_user_id") String cookieId, RedirectAttributes redirAttrs) {
		if(regId.equals("notselected")|| deliveryTownship.getName().isEmpty() || deliveryTownship.getFee() == 0) {
			redirAttrs.addFlashAttribute("validation","Data is missing");
			
		}else {
			if (deliveryTownship.getId()== 0) {
				deliveryTownship.setSecurityInfo(new SecurityInfo(cookieId));
				deliveryTownshipService.save(deliveryTownship, Long.parseLong(regId));
				redirAttrs.addFlashAttribute("save","Delivery Township save successfully");
			} 
			else {
				DeliveryTownship deliveryTownship2 = deliveryTownshipService.findByID(deliveryTownship.getId());
				deliveryTownship2.setName(deliveryTownship.getName());
				deliveryTownship2.setFee(deliveryTownship.getFee());
				deliveryTownship2.setDeliveryRegion(deliveryTownship.getDeliveryRegion());
				deliveryTownship2.getSecurityInfo().setUpdateDate(LocalDate.now());
				deliveryTownship2.getSecurityInfo().setUpdateTime(LocalTime.now().toString());
				deliveryTownship2.getSecurityInfo().setUpdateUser(cookieId);
				deliveryTownshipService.save(deliveryTownship2, Long.parseLong(regId));	
				redirAttrs.addFlashAttribute("update","Delivery Township update successfully");
			}
			
		}
		
		return "redirect:/admin/delivery/townships/new";
	}
	
	@GetMapping("/townships/delete/{id}")
	public String delete(@PathVariable String id, RedirectAttributes redirAttrs) {
		deliveryTownshipService.delete(deliveryTownshipService.findByID(Long.parseLong(id)));
		redirAttrs.addFlashAttribute("validation","Delivery Township delete successfully");
		return "redirect:/admin/delivery/townships/new";

	}

}	
