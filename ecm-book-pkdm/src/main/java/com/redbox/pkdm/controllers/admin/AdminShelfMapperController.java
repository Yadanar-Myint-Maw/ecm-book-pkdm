package com.redbox.pkdm.controllers.admin;

import java.time.LocalDate;

import com.redbox.pkdm.entities.AccountAdmin;
import com.redbox.pkdm.entities.SecurityInfo;
import com.redbox.pkdm.entities.ShelfFeatureMapper;
import com.redbox.pkdm.services.AccountAdminService;
import com.redbox.pkdm.services.BookService;
import com.redbox.pkdm.services.ShelfFeatureMapperService;
import com.redbox.pkdm.services.ShelfFeatureService;

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

@Controller
@RequestMapping("admin/shelf/mapper")
public class AdminShelfMapperController {
	
	@Autowired
	private ShelfFeatureService shelfFeatureService;
	
	@Autowired
	private ShelfFeatureMapperService shelfFeatureMapperService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private AccountAdminService accountAdminService;
	
	@GetMapping("/initialize/{id}")
	public String initialize(@PathVariable String id, @CookieValue("login_user_id") String cookieId, Model model,String featureId) throws Exception {
		
		AccountAdmin loginaccount = accountAdminService.findByID(cookieId);
		if (loginaccount == null) {
			throw new Exception();
		}
		
		model.addAttribute("loginaccount", loginaccount);	
		
		model.addAttribute("features", shelfFeatureService.findByErase(false));
		model.addAttribute("books" , bookService.findByErase(false));
		
		if(id.equals("0")) {
			model.addAttribute("shelfFeatureMapper", new ShelfFeatureMapper());
			
		}else {
			model.addAttribute("shelfFeatureMapper", shelfFeatureMapperService.findById(Long.parseLong(id)));
		}
		
		if(featureId == null) {
			model.addAttribute("shelfFeatureMappers", shelfFeatureMapperService.findByErase(false));
		}else {
			model.addAttribute("shelfFeatureMappers", shelfFeatureMapperService.findById(Long.parseLong(featureId)));
		}

		return "adminshelfmapper";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("shelfFeatureMapper") ShelfFeatureMapper shelfFeatureMapper, @CookieValue("login_user_id") String cookieId ,@RequestParam String featureId ,@RequestParam String bookId, RedirectAttributes redirAttrs) {	
	
		if(featureId.equals("notselected") || bookId.equals("notselected")) {
			redirAttrs.addFlashAttribute("validation", "Data is missing");
		}else {
			if(shelfFeatureMapper.getId() == 0) {
				shelfFeatureMapper.setSecurityInfo(new SecurityInfo(cookieId));
				shelfFeatureMapperService.save(shelfFeatureMapper, featureId , bookId);
				redirAttrs.addFlashAttribute("save" , "Shelf Feature Mapper save successfully");
				
			}else {
				ShelfFeatureMapper shelfFeatureMapper2 = shelfFeatureMapperService.findById(shelfFeatureMapper.getId());
				shelfFeatureMapper2.setBook(shelfFeatureMapper.getBook());
				shelfFeatureMapper2.setShelfFeature(shelfFeatureMapper.getShelfFeature());
				shelfFeatureMapper2.getSecurityInfo().setUpdateDate(LocalDate.now());
				shelfFeatureMapper2.getSecurityInfo().setUpdateTime(LocalDate.now().toString());
				shelfFeatureMapper2.getSecurityInfo().setUpdateUser(cookieId);
				shelfFeatureMapperService.save(shelfFeatureMapper2, featureId, bookId);
				redirAttrs.addFlashAttribute("update", "Shelf Feature Mapper update successfully");
			}	
		}
		
		return "redirect:/admin/shelf/mapper/initialize/0";
	}
	
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable String id, RedirectAttributes redirAttrs) {
		shelfFeatureMapperService.delete(shelfFeatureMapperService.findById(Long.parseLong(id)));
		redirAttrs.addFlashAttribute("validation", "Shelf Feature Mapper delete successfully");
		return "redirect:/admin/shelf/mapper/initialize/0";
	}
}
