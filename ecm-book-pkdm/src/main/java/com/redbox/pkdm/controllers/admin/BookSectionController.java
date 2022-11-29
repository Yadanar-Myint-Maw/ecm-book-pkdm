package com.redbox.pkdm.controllers.admin;

import java.time.LocalDate;
import java.time.LocalTime;

import com.redbox.pkdm.entities.AccountAdmin;
import com.redbox.pkdm.entities.Book;
import com.redbox.pkdm.entities.BookSection;
import com.redbox.pkdm.entities.SecurityInfo;
import com.redbox.pkdm.services.AccountAdminService;
import com.redbox.pkdm.services.BookSectionService;
import com.redbox.pkdm.services.BookService;
import com.redbox.pkdm.utilities.ImageUploadUtility;

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
@RequestMapping("admin/section")
public class BookSectionController {
	
	@Autowired
	private BookSectionService bookSectionService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private AccountAdminService accountAdminService;
	
	@GetMapping("/book-section/{bookID}/{sectionID}")
	public String initialize(@PathVariable("bookID") String bookID, @PathVariable("sectionID") String sectionID, Model model, String keyword, @CookieValue("login_user_id") String cookieId) throws Exception {
		AccountAdmin loginaccount = accountAdminService.findByID(cookieId);
		if (loginaccount == null) {throw new Exception();}
		Book book = new Book();
		BookSection bookSection = new BookSection();
		if (sectionID.equals("new")) {
			book = bookService.findByID(bookID);
			bookSection.setBook(book);
		} else {
			bookSection = bookSectionService.findByID(Long.parseLong(sectionID));
			book = bookSection.getBook();
		}
		model.addAttribute("loginaccount", loginaccount);
		model.addAttribute("book", book);
		model.addAttribute("bookSection", bookSection);
		model.addAttribute("bookSections", bookSectionService.findByBookId(bookSection.getBook().getId(), false));		
		return "adminbooksection";
	}
	
	@PostMapping("/book-section/save")
	public String save(@ModelAttribute("bookSection") BookSection bookSection, @CookieValue("login_user_id") String cookieId, RedirectAttributes redirAttrs) {
		String id = "";
		if (bookSection.getId() == 0) {
			id = bookSection.getBook().getId();
			bookSection.setSecurityInfo(new SecurityInfo(cookieId));
			if (!bookSection.getFile().isEmpty()) {
				bookSection.setImage(ImageUploadUtility.upload(bookSection.getFile()));
			}
			bookSectionService.save(bookSection, bookSection.getBook().getId());
			redirAttrs.addFlashAttribute("save","အချက်အလက်များကို သိမ်းဆဲလိုက်ပါပြီ။");
		} 
		else {
			BookSection bookSection2 = bookSectionService.findByID(bookSection.getId());
			id = bookSection2.getBook().getId();
			bookSection2.setName(bookSection.getName());
			bookSection2.setPrice(bookSection.getPrice());
			bookSection2.setSort_no(bookSection.getSort_no());
			bookSection2.setDescription(bookSection.getDescription());
			if (!bookSection.getFile().isEmpty()) {
				bookSection2.setImage(ImageUploadUtility.upload(bookSection.getFile()));
			}
			bookSection2.getSecurityInfo().setUpdateDate(LocalDate.now());
			bookSection2.getSecurityInfo().setUpdateTime(LocalTime.now().toString());
			bookSection2.getSecurityInfo().setUpdateUser(cookieId);
			bookSectionService.save(bookSection2, bookSection2.getBook().getId());
			redirAttrs.addFlashAttribute("update","အချက်အလက်များကို ပြင်ဆင်လိုက်ပါပြီ။");
			
		}
		return "redirect:/admin/section/book-section/" + id + "/new";	
	}

	@GetMapping("/book-section/delete/{sectionID}")
	public String delete(@PathVariable String sectionID, RedirectAttributes redirAttrs) {
		BookSection bookSection = bookSectionService.findByID(Long.parseLong(sectionID));
		bookSectionService.delete(bookSection);
		redirAttrs.addFlashAttribute("validation","အချက်အလက်များကို ပယ်ဖျက်လိုက်ပါသည်။");
		return "redirect:/admin/section/book-section/" + bookSection.getBook().getId() + "/new";
	}

}
