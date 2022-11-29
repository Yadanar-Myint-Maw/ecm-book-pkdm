package com.redbox.pkdm.controllers.admin;

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
import com.redbox.pkdm.entities.BookTag;
import com.redbox.pkdm.entities.ShelfRelated;
import com.redbox.pkdm.services.AccountAdminService;
import com.redbox.pkdm.services.BookTagService;
import com.redbox.pkdm.utilities.MessageUtility;

@Controller
@RequestMapping("admin/essential/tag")
public class AdminEssentialTagController {
	
	@Autowired
	private AccountAdminService accountAdminService;
	
	@Autowired
	private BookTagService bookTagService;
	
	@GetMapping("/initialize/{id}")
	public String initialize (@PathVariable String id, Model model, @CookieValue("login_user_id") String cookieId) throws Exception {
		AccountAdmin loginaccount = accountAdminService.findByID(cookieId);
		if (loginaccount == null) {
			throw new Exception();
		}
		model.addAttribute("loginaccount", loginaccount);
		if (id.equals("0")) {
			model.addAttribute("bookTag", new ShelfRelated());
		} else {
			model.addAttribute("bookTag", bookTagService.findByID(Long.parseLong(id)));
		}
		model.addAttribute("bookTags", bookTagService.findByErase(false));
		return "adminessentialtag";
		     
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("bookTag") BookTag bookTag, @CookieValue("login_user_id") String cookieId, Model model, RedirectAttributes redirAttrs) {
		if(bookTag.getName().isEmpty()) {
			redirAttrs.addFlashAttribute("validation", MessageUtility.getDataIsMissingMessage());
		} else {
			if(bookTag.getId() == 0) {
				bookTagService.save(bookTag);
				redirAttrs.addFlashAttribute("save", MessageUtility.getSaveSuccessMessage("Related"));
			} else {
				bookTagService.save(bookTag);
				redirAttrs.addFlashAttribute("update", MessageUtility.getUpdateSuccessMessage("Related"));
			}
		}
		return "redirect:/admin/essential/tag/initialize/0";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes redirAttrs) {
		bookTagService.delete(bookTagService.findByID(id));
		redirAttrs.addFlashAttribute("validation", MessageUtility.getDeleteSuccesMessage("Related"));
		return "redirect:/admin/essential/tag/initialize/0";
	}

}
