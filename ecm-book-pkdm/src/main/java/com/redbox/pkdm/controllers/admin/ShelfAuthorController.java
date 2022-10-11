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
import com.redbox.pkdm.entities.ShelfAuthor;
import com.redbox.pkdm.services.AccountAdminService;
import com.redbox.pkdm.services.ShelfAuthorService;
import com.redbox.pkdm.utilities.ImageUploadUtility;

@Controller
@RequestMapping("admin/shelf")
public class ShelfAuthorController {
	

	@Autowired
	private ShelfAuthorService shelfAuthorService;
	
	@Autowired
	private AccountAdminService accountAdminService;
	
	@GetMapping("/authors/{id}")
	public String initialize(@PathVariable String id, Model model, String keyword, @CookieValue("login_user_id") String cookieId)throws Exception {
		
		AccountAdmin loginaccount = accountAdminService.findByID(cookieId);
		if (loginaccount == null) {
			throw new Exception();
		}
		model.addAttribute("loginaccount", loginaccount);	
		
		if (id.equals("new")) {
			model.addAttribute("shelfAuthor", new ShelfAuthor());
		} else {
			model.addAttribute("shelfAuthor", shelfAuthorService.findByID(Long.parseLong(id)));
		}
		if (keyword == null) {		
				model.addAttribute("shelfauthors", shelfAuthorService.findByEraseAndOrderId(false));
		}
		else {
//			List<ShelfAuthor> shelfAuthors = shelfAuthorService.findByEraseAndOrderId(false);
//			List<ShelfAuthor> shelfAuthors2 = new ArrayList<>();
//			for (ShelfAuthor s : shelfAuthors) {
//				if (s.getName().toLowerCase().contains(keyword.toLowerCase())) {
//				shelfAuthors2.add(s);
//			}
//		}
			model.addAttribute("shelfauthors", shelfAuthorService.findByNameLike(keyword));
		}
		return "adminshelfauthors";
	}
	
	@PostMapping("/author/save")
	public String save(@ModelAttribute("shelfAuthor") ShelfAuthor shelfAuthor, @CookieValue("login_user_id") String cookieId, RedirectAttributes redirAttrs) {	
		System.out.println("File " + shelfAuthor.getFile());
		System.out.println("Name " + shelfAuthor.getName());
		System.out.println("Desc " + shelfAuthor.getDescription());
		if(shelfAuthor.getFile().isEmpty() || shelfAuthor.getName() == null || shelfAuthor.getDescription() == null) {
			redirAttrs.addFlashAttribute("validation", "Data is missing");
		}else {
			if (shelfAuthor.getId() == 0) {
				shelfAuthor.setSecurityInfo(new SecurityInfo(cookieId));
			if (shelfAuthor.getFile() != null) {
				shelfAuthor.setImage(ImageUploadUtility.upload(shelfAuthor.getFile()));
			}
			shelfAuthorService.save(shelfAuthor);
			redirAttrs.addFlashAttribute("save", "Shelf Author save successfully");
			} else {
				ShelfAuthor shelfAuthor2 = shelfAuthorService.findByID(shelfAuthor.getId());
				shelfAuthor2.setName(shelfAuthor.getName());
				shelfAuthor2.setDescription(shelfAuthor.getDescription());
				if (shelfAuthor.getFile() != null) {
					shelfAuthor2.setImage(ImageUploadUtility.upload(shelfAuthor.getFile()));
				}
				shelfAuthor2.getSecurityInfo().setUpdateDate(LocalDate.now());
				shelfAuthor2.getSecurityInfo().setUpdateTime(LocalTime.now().toString());
				shelfAuthor2.getSecurityInfo().setUpdateUser(cookieId);
				shelfAuthorService.save(shelfAuthor2);	
				redirAttrs.addFlashAttribute("update", "Shelf Author update successfully");
			}
			
		}
			
		return "redirect:/admin/shelf/authors/new";
	}
	
	
	@GetMapping("/authors/delete/{id}")
	public String delete(@PathVariable String id , RedirectAttributes redirAttrs) {
		shelfAuthorService.delete(shelfAuthorService.findByID(Long.parseLong(id)));
		redirAttrs.addFlashAttribute("validation", "Shelf Author delete successfully");
		return "redirect:/admin/shelf/authors/new";
	}
}
