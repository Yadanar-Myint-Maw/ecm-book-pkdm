package com.redbox.pkdm.controllers.admin;

import com.redbox.pkdm.entities.AccountAdmin;
import com.redbox.pkdm.entities.ShelfRelated;
import com.redbox.pkdm.services.AccountAdminService;
import com.redbox.pkdm.services.ShelfRelatedService;
import com.redbox.pkdm.utilities.MessageUtility;

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

@Controller
@RequestMapping("admin/essential/related")
public class AdminEssentialRelatedController {
	
	@Autowired
	private AccountAdminService accountAdminService;
	
	@Autowired
	private ShelfRelatedService shelfRelatedService;
	
	@GetMapping("/initialize/{id}")
	public String initialize (@PathVariable String id, Model model, @CookieValue("login_user_id") String cookieId) throws Exception {
		AccountAdmin loginaccount = accountAdminService.findByID(cookieId);
		if (loginaccount == null) {
			throw new Exception();
		}
		model.addAttribute("loginaccount", loginaccount);
		if (id.equals("0")) {
			model.addAttribute("shelfRelated", new ShelfRelated());
		} else {
			model.addAttribute("shelfRelated", shelfRelatedService.findByID(Long.parseLong(id)));
		}
		model.addAttribute("shelfRelateds", shelfRelatedService.findByErase(false));
		return "adminessentialrelated";
		     
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("shelfRelated") ShelfRelated shelfRelated, @CookieValue("login_user_id") String cookieId, Model model, RedirectAttributes redirAttrs) {
		if(shelfRelated.getName().isEmpty()) {
			redirAttrs.addFlashAttribute("validation", MessageUtility.getDataIsMissingMessage());
		}else {
			if(shelfRelated.getId() == 0) {
				shelfRelatedService.save(shelfRelated);
				redirAttrs.addFlashAttribute("save", MessageUtility.getSaveSuccessMessage("Related"));
			} else {
				shelfRelatedService.save(shelfRelated);
				redirAttrs.addFlashAttribute("update", MessageUtility.getUpdateSuccessMessage("Related"));
			}
		}
		return "redirect:/admin/essential/related/initialize/0";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes redirAttrs) {
		shelfRelatedService.delete(shelfRelatedService.findByID(id));
		redirAttrs.addFlashAttribute("validation", MessageUtility.getDeleteSuccesMessage("Related"));
		return "redirect:/admin/essential/related/initialize/0";
	}

}
