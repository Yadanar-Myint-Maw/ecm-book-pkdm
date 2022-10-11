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

import com.redbox.pkdm.entities.AccountAdmin;
import com.redbox.pkdm.entities.Book;
import com.redbox.pkdm.entities.BookSection;
import com.redbox.pkdm.entities.SecurityInfo;
import com.redbox.pkdm.models.BookInfoModel;
import com.redbox.pkdm.services.AccountAdminService;
import com.redbox.pkdm.services.BookSectionService;
import com.redbox.pkdm.services.BookService;
import com.redbox.pkdm.utilities.ImageUploadUtility;

@Controller
@RequestMapping("admin/book-section")
public class BookSectionController {
	
	@Autowired
	private BookSectionService bookSectionService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private AccountAdminService accountAdminService;
	
	@GetMapping("/book-series-section/{id}")
	public String initialize(@PathVariable String id, Model model, String keyword, @CookieValue("login_user_id") String cookieId) throws Exception {
		
		AccountAdmin loginaccount = accountAdminService.findByID(cookieId);
		if (loginaccount == null) {
			throw new Exception();
		}
		model.addAttribute("loginaccount", loginaccount);
		
		model.addAttribute("books", bookService.findByBookType(id));
		model.addAttribute("bookType", id);
		boolean description = true;
		String selectBook = "Select Book";
			
	if (id.equals("SERIE")) {
		model.addAttribute("bookSection", new BookSection());
		model.addAttribute("selectBook", selectBook);		
	}
	else if(id.equals("ENTIRE")){
		model.addAttribute("bookSection", new BookSection());
		model.addAttribute("selectBook", selectBook);
		description = false;
	}
	else {
		model.addAttribute("bookSection", bookSectionService.findByID(Long.parseLong(id)));
		selectBook = bookSectionService.findByID(Long.parseLong(id)).getBook().getName();
		String selectBookId = bookSectionService.findByID(Long.parseLong(id)).getBook().getId();
		model.addAttribute("selectBook", selectBook);
		model.addAttribute("selectBookId", selectBookId);
	}
		model.addAttribute("description", description);
		return "admininventorybooksections";
	}
	
	@GetMapping("/add-new-book-section/{bookId}")
	public String addNewSection(@PathVariable String id, Model model, @CookieValue("login_user_id") String cookieId) throws Exception {
		
		AccountAdmin loginaccount = accountAdminService.findByID(cookieId);
		if (loginaccount == null) {
			throw new Exception();
		}
		model.addAttribute("loginaccount", loginaccount);
		
		
		model.addAttribute("books", bookService.findByBookType(id));
		String bookName = bookService.findByID(id).getName();
		model.addAttribute("bookName", bookName);
		return "admininventorybooksections";
	}
	
	
	@PostMapping("/book-series-section/save")
	public String save(@ModelAttribute("bookSection") BookSection bookSection, @RequestParam String bookId ,@CookieValue("login_user_id") String cookieId) {
		
		String bookType = "";
		
		if (bookSection.getId() == 0) {
			bookSection.setSecurityInfo(new SecurityInfo(cookieId));
			
			if (bookSection.getFile() != null) {
				bookSection.setImage(ImageUploadUtility.upload(bookSection.getFile()));
			}
			bookSectionService.save(bookSection, bookId);
			bookType = bookSection.getBook().getBookType();
		} 
		else {
			BookSection bookSection2 = bookSectionService.findByID(bookSection.getId());
			bookSection2.setName(bookSection.getName());
			bookSection2.setPrice(bookSection.getPrice());
			bookSection2.setSort_no(bookSection.getSort_no());
			bookSection2.setDescription(bookSection.getDescription());
			if (bookSection.getFile() != null) {
				bookSection2.setImage(ImageUploadUtility.upload(bookSection.getFile()));
			}
			bookSection2.getSecurityInfo().setUpdateDate(LocalDate.now());
			bookSection2.getSecurityInfo().setUpdateTime(LocalTime.now().toString());
			bookSection2.getSecurityInfo().setUpdateUser(cookieId);
			bookSectionService.save(bookSection2, bookId);
			bookType = bookSection2.getBook().getBookType();
		}
		return "redirect:/admin/book-section/book-series-section/" + bookType;	
	}

	@GetMapping("/book-series-section/delete/{id}")
	public String delete(@PathVariable String id) {
		bookSectionService.delete(bookSectionService.findByID(Long.parseLong(id)));
		return "redirect:/admin/book-section/show-book-sections/" + bookSectionService.findByID(Long.parseLong(id)).getBook().getBookType();
	}
	
	@GetMapping("/show-book-sections/{bookType}")
	public String showbookSections(@PathVariable String bookType, Model model, String bookId, @CookieValue("login_user_id") String cookieId) throws Exception {
		
		AccountAdmin loginaccount = accountAdminService.findByID(cookieId);
		if (loginaccount == null) {
			throw new Exception();
		}
		model.addAttribute("loginaccount", loginaccount);
		
		model.addAttribute("books", bookService.findByBookType(bookType));
		model.addAttribute("bookType", bookType);
		
		List<Book> books = bookService.findByErase(false);
		List<BookInfoModel> bookModels = new ArrayList<>();
		for (Book book : books) {
			List<BookSection> sections = bookSectionService.findByBookId(book.getId(), false);
			bookModels.add(new BookInfoModel(book, sections));
		}
		
		List<BookInfoModel> bookModels2 = new ArrayList<>();
		
		if(bookId == null) {
			for(BookInfoModel bookModel : bookModels) {
				if(bookType.equals(bookModel.getBook().getBookType())) {
					bookModels2.add(bookModel);
				}
			}
		}
		else {
			for(BookInfoModel bookModel : bookModels) {
				if(bookType.equals(bookModel.getBook().getBookType()) 
						&& bookId.equals(bookModel.getBook().getId())) {
					bookModels2.add(bookModel);
				}
			}
		}
		model.addAttribute("bookModels", bookModels2);

		boolean addBtn = true;
		if(!bookType.equals("SERIE")) {
			addBtn = false;
		}
		model.addAttribute("addBtn", addBtn);
		
		return "adminbooksections";
	}
	
	@GetMapping("/view/{id}")
	public String readMore(@PathVariable String id, Model model, @CookieValue("login_user_id") String cookieId) throws Exception {
		
		AccountAdmin loginaccount = accountAdminService.findByID(cookieId);
		if (loginaccount == null) {
			throw new Exception();
		}
		model.addAttribute("loginaccount", loginaccount);
		
		
		model.addAttribute("bookSection", bookSectionService.findByID(Long.parseLong(id)));
		return "adminbooksectionview";
	}

}
