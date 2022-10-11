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
import com.redbox.pkdm.utilities.ImageUploadUtility;

@Controller
@RequestMapping("admin/shelf")
public class ShelfCategoryController {
	
	@Autowired
	private ShelfCategoryService shelfCategoryService;
	
	@Autowired
	private AccountAdminService accountAdminService;
	

	@GetMapping("/categories/{id}")
	public String initialize(@PathVariable String id, Model model, String keyword, @CookieValue("login_user_id") String cookieId) throws Exception {
		AccountAdmin loginaccount = accountAdminService.findByID(cookieId);
		if (loginaccount == null) {
			throw new Exception();
		}
		model.addAttribute("loginaccount", loginaccount);	
		if (id.equals("new")) {
			System.out.println("1");
			model.addAttribute("shelfCategory", new ShelfCategory());
		} else {
			System.out.println("2");
			model.addAttribute("shelfCategory", shelfCategoryService.findByID(Long.parseLong(id)));
			}
			if (keyword == null) {		
				model.addAttribute("shelfCategories", shelfCategoryService.findByEraseAndOrderId(false));
		}
			else {
//			List<ShelfCategory> shelfCategories = shelfCategoryService.findByEraseAndOrderId(false);
//			List<ShelfCategory> shelfCategories2 = new ArrayList<>();
//			for (ShelfCategory s : shelfCategories) {
//				if (s.getName().toLowerCase().contains(keyword.toLowerCase())) {
//					shelfCategories2.add(s);
//			}
//		}
			model.addAttribute("shelfCategories", shelfCategoryService.findByNameLike(keyword));
			}
		return "adminshelfcategories";
	}
	
	@PostMapping("/categories/save")
	public String save(@ModelAttribute("shelfCategory") ShelfCategory shelfCategory, @CookieValue("login_user_id") String cookieId, RedirectAttributes redirAttrs) {	
		System.out.println("File " + shelfCategory.getFile());
		System.out.println("Name " + shelfCategory.getName());
		System.out.println("Desc " + shelfCategory.getDescription());
		
		if(shelfCategory.getFile().isEmpty() || shelfCategory.getName() == null || shelfCategory.getDescription() == null) {
			redirAttrs.addFlashAttribute("validation", "Data is missing");
		}else {
			if (shelfCategory.getId() == 0) {
				System.out.println("shelf Category " + shelfCategory.getId());
				shelfCategory.setSecurityInfo(new SecurityInfo(cookieId));
			if (shelfCategory.getFile() != null) {
				shelfCategory.setIamge(ImageUploadUtility.upload(shelfCategory.getFile()));
				}		
				shelfCategoryService.save(shelfCategory);
				redirAttrs.addFlashAttribute("save", "Shelf Category save successfully");
			} else {
				ShelfCategory shelfCategory2 = shelfCategoryService.findByID(shelfCategory.getId());
				shelfCategory2.setName(shelfCategory.getName());
				shelfCategory2.setDescription(shelfCategory.getDescription());
				
				if (shelfCategory.getFile() != null) {
					shelfCategory.setIamge(ImageUploadUtility.upload(shelfCategory.getFile()));
				}			shelfCategory2.getSecurityInfo().setUpdateDate(LocalDate.now());
				shelfCategory2.getSecurityInfo().setUpdateTime(LocalTime.now().toString());
				shelfCategory2.getSecurityInfo().setUpdateUser(cookieId);
				shelfCategoryService.save(shelfCategory2);	
				redirAttrs.addFlashAttribute("update", "Shelf Category update successfully");
			}
			
		}
		
		return "redirect:/admin/shelf/categories/new";
	}
	
	
	@GetMapping("/categories/delete/{id}")
	public String delete(@PathVariable String id, RedirectAttributes redirAttrs) {
		shelfCategoryService.delete(shelfCategoryService.findByID(Long.parseLong(id)));
		redirAttrs.addFlashAttribute("validation", "Shelf Category delete successfully");
		return "redirect:/admin/shelf/categories/new";
	}
}
