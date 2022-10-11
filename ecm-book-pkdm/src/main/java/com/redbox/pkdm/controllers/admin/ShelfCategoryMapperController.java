package com.redbox.pkdm.controllers.admin;

import java.time.LocalDate;
import com.redbox.pkdm.entities.AccountAdmin;
import com.redbox.pkdm.entities.SecurityInfo;
import com.redbox.pkdm.entities.ShelfCategoryMapper;
import com.redbox.pkdm.services.AccountAdminService;
import com.redbox.pkdm.services.BookService;
import com.redbox.pkdm.services.ShelfCategoryMapperService;
import com.redbox.pkdm.services.ShelfCategoryService;

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
@RequestMapping("admin/shelf")
public class ShelfCategoryMapperController {
	
	@Autowired
	private ShelfCategoryMapperService shelfCategoryMapperService;
	
	@Autowired
	private ShelfCategoryService shelfCategoryService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private AccountAdminService accountAdminService;
	
	@GetMapping("/shelfcategorymappers/{id}")
	public String initialize(@PathVariable String id, Model model, String categoryId, @CookieValue("login_user_id") String cookieId) throws Exception {
		AccountAdmin loginaccount = accountAdminService.findByID(cookieId);
		if (loginaccount == null) {
			throw new Exception();
		}
		model.addAttribute("loginaccount", loginaccount);
		model.addAttribute("books", bookService.findByErase(false));
		model.addAttribute("shelfcategory", shelfCategoryService.findByEraseAndOrderId(false));
		model.addAttribute("shelfcategorymapper", shelfCategoryMapperService.findAll());
		
		if(id.equals("new")) {
			model.addAttribute("shelfcategorymapper", new ShelfCategoryMapper());
		}else {
			model.addAttribute("shelfcategorymapper", shelfCategoryMapperService.findById(Long.parseLong(id)));
		}
		
		if (categoryId == null) {
			model.addAttribute("shelfcategorymappers", shelfCategoryMapperService.findByErase(false));
		} else {
			
			model.addAttribute("shelfcategorymappers", shelfCategoryMapperService.findById(Long.parseLong(categoryId)));
		}
		
		return "adminshelfcategorymappers";
	}
	
	@PostMapping("/shelfcategorymappers/save")
	public String save(@ModelAttribute("shelfcategorymapper") ShelfCategoryMapper shelfCategoryMapper, @CookieValue("login_user_id") String cookieId, RedirectAttributes redirAttrs, @RequestParam String bookId, @RequestParam String catId) {	
		if(bookId.equals("notselected") || catId.equals("notselected")) {
			redirAttrs.addFlashAttribute("validation", "Data is missing!");
		}else {
			if(shelfCategoryMapper.getId() == 0) {
				shelfCategoryMapper.setSecurityInfo(new SecurityInfo(cookieId));
				shelfCategoryMapperService.save(shelfCategoryMapper, bookId, catId);
				redirAttrs.addFlashAttribute("save", "Shelf Categoery Mapper save Successfully");
			}else {
				ShelfCategoryMapper shelfCategoryMapper2 = shelfCategoryMapperService.findById(shelfCategoryMapper.getId());
				shelfCategoryMapper2.setBook(shelfCategoryMapper.getBook());
				shelfCategoryMapper2.setShelfCategory(shelfCategoryMapper.getShelfCategory());
				shelfCategoryMapper2.getSecurityInfo().setUpdateDate(LocalDate.now());
				shelfCategoryMapper2.getSecurityInfo().setUpdateTime(LocalDate.now().toString());
				shelfCategoryMapper2.getSecurityInfo().setUpdateUser(cookieId);
				shelfCategoryMapperService.save(shelfCategoryMapper2, bookId, catId);
				redirAttrs.addFlashAttribute("update", "Shelf Categoery Mapper update Successfully");
			}
		}
		return "redirect:/admin/shelf/shelfcategorymappers/new";
	}
	
	
	@GetMapping("shelfcategorymappers/delete/{id}")
	public String delete(@PathVariable long id, RedirectAttributes redirAttrs) {
		shelfCategoryMapperService.delete(shelfCategoryMapperService.findById(id));
		redirAttrs.addFlashAttribute("validation", "Shelf Category Mapper delete successfully");
		return "redirect:/admin/shelf/shelfcategorymappers/new";
	}

}
