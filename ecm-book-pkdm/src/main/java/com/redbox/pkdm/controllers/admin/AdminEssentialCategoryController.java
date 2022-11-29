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
import com.redbox.pkdm.entities.ShelfCategory;
import com.redbox.pkdm.services.AccountAdminService;
import com.redbox.pkdm.services.ShelfCategoryService;
import com.redbox.pkdm.utilities.MessageUtility;

@Controller
@RequestMapping("admin/essential/category")
public class AdminEssentialCategoryController {
	
	@Autowired
	private ShelfCategoryService shelfCategoryService;
	
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
			model.addAttribute("shelfCategory", new ShelfCategory());
		} else {
			model.addAttribute("shelfCategory", shelfCategoryService.findByID(Long.parseLong(id)));
			}
			if (keyword == null) {		
				model.addAttribute("shelfCategories", shelfCategoryService.findByEraseAndOrderId(false));
		}
			else {
			model.addAttribute("shelfCategories", shelfCategoryService.findByNameLike(keyword));
			}
		return "adminessentialcategory";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("shelfCategory") ShelfCategory shelfCategory, @CookieValue("login_user_id") String cookieId, RedirectAttributes redirAttrs) {	
		if(shelfCategory.getName().isEmpty()) {
			redirAttrs.addFlashAttribute("validation", MessageUtility.getDataIsMissingMessage());
		}else {
			if (shelfCategory.getId() == 0) {
				shelfCategory.setSecurityInfo(new SecurityInfo(cookieId));	
				shelfCategoryService.save(shelfCategory);
				redirAttrs.addFlashAttribute("save", MessageUtility.getSaveSuccessMessage("Category"));
			} else {
				ShelfCategory shelfCategory2 = shelfCategoryService.findByID(shelfCategory.getId());
				shelfCategory2.setName(shelfCategory.getName());
				shelfCategory2.setDescription(shelfCategory.getDescription());		
				shelfCategory2.getSecurityInfo().setUpdateDate(LocalDate.now());
				shelfCategory2.getSecurityInfo().setUpdateTime(LocalTime.now().toString());
				shelfCategory2.getSecurityInfo().setUpdateUser(cookieId);
				shelfCategoryService.save(shelfCategory2);	
				redirAttrs.addFlashAttribute("update", MessageUtility.getUpdateSuccessMessage("Category"));
			}
			
		}
		
		return "redirect:/admin/essential/category/initialize/0";
	}
	
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable String id, RedirectAttributes redirAttrs) {
		shelfCategoryService.delete(shelfCategoryService.findByID(Long.parseLong(id)));
		redirAttrs.addFlashAttribute("validation", MessageUtility.getDeleteSuccesMessage("Category"));
		return "redirect:/admin/essential/category/initialize/0";
	}
}
