package com.redbox.pkdm.controllers.admin;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.redbox.pkdm.entities.AccountAdmin;
import com.redbox.pkdm.entities.Book;
import com.redbox.pkdm.entities.BookSection;
import com.redbox.pkdm.entities.BookSectionDetail;
import com.redbox.pkdm.entities.SecurityInfo;
import com.redbox.pkdm.models.BookInfoModel;
import com.redbox.pkdm.services.AccountAdminService;
import com.redbox.pkdm.services.BookSectionDetailService;
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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("admin/book-section-detail")
public class BookSectionDetailController {
	
	@Autowired
	private BookSectionDetailService bookSectionDetailService;
	
	@Autowired
	private BookSectionService bookSectionService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private AccountAdminService accountAdminService;
	
	@GetMapping("/book-series-section-detail/{id}")
	public String initialize(@PathVariable String id, Model model, String keyword, @CookieValue("login_user_id") String cookieId) throws Exception {
		
		AccountAdmin loginaccount = accountAdminService.findByID(cookieId);
		if (loginaccount == null) {
			throw new Exception();
		}
		model.addAttribute("loginaccount", loginaccount);
		
		
		model.addAttribute("bookSections", bookSectionService.findByBookType("SERIE"));
		//System.out.println(bookSectionDetailService.findBookSectionsByBookType(false, "Series").get(0));
		
		if (id.equals("new")) {
			model.addAttribute("bookSectionDetail", new BookSectionDetail());
		}
		else {
			model.addAttribute("bookSectionDetail", bookSectionDetailService.findByID(Long.parseLong(id)));
		}
			return "admininventorybooksectiondetails";
		}
	
	
	@PostMapping("/book-series-section-detail/save")
	public String save(@ModelAttribute("bookSectionDetail") BookSectionDetail bookSectionDetail,@RequestParam long bookSectionId, @CookieValue("login_user_id") String cookieId) {	
		if (bookSectionDetail.getId() == 0) {
			bookSectionDetail.setSecurityInfo(new SecurityInfo(cookieId));
			if (bookSectionDetail.getFile() != null) {
				bookSectionDetail.setImage(ImageUploadUtility.upload(bookSectionDetail.getFile()));
			}
			bookSectionDetailService.save(bookSectionDetail, bookSectionId);
		}
		else {
			BookSectionDetail bookSectionDetail2 = bookSectionDetailService.findByID(bookSectionDetail.getId());
			bookSectionDetail2.setName(bookSectionDetail.getName());
			bookSectionDetail2.setDescription(bookSectionDetail.getDescription());
			if (bookSectionDetail.getFile() != null) {
				bookSectionDetail2.setImage(ImageUploadUtility.upload(bookSectionDetail.getFile()));
			}
			bookSectionDetail2.getSecurityInfo().setUpdateDate(LocalDate.now());
			bookSectionDetail2.getSecurityInfo().setUpdateTime(LocalTime.now().toString());
			bookSectionDetail2.getSecurityInfo().setUpdateUser(cookieId);
			bookSectionDetailService.save(bookSectionDetail2, bookSectionId);	
		}
		return "redirect:/admin/book-section-detail/book-series-section-detail/new";
		
	}
	
	
	@GetMapping("/book-series-section-detail/delete/{id}")
	public String delete(@PathVariable String id) {
		bookSectionDetailService.delete(bookSectionDetailService.findByID(Long.parseLong(id)));
		return "redirect:/admin/book-section-detail/show-book-section-details/SERIE";
	}
	
	@GetMapping("/show-book-section-details/{bookType}")
	public String showbooks(@PathVariable String bookType, Model model, String bookId, @CookieValue("login_user_id") String cookieId) throws Exception {
		
		AccountAdmin loginaccount = accountAdminService.findByID(cookieId);
		if (loginaccount == null) {
			throw new Exception();
		}
		model.addAttribute("loginaccount", loginaccount);
		
		
			model.addAttribute("books", bookSectionDetailService.findBooks(false));
			
			List<Book> books = bookService.findByErase(false);
			List<BookInfoModel> bookModels = new ArrayList<>();
			
			for(Book book : books) {
				List<BookSectionDetail> sectionDetails = bookSectionDetailService.findByBookId(false, book.getId());
				List<BookSection> sections = bookSectionDetailService.findBookSectionsByBookType(false, "SERIE");
				bookModels.add(new BookInfoModel(book, sections, sectionDetails));
			}
			
			List<BookInfoModel> bookModels2 = new ArrayList<>();
						
			if( bookId == null) {
				for(BookInfoModel bookModel : bookModels) {
					if(bookType.equals(bookModel.getBook().getBookType())) {
						bookModels2.add(bookModel);
					}
				}		
			}else {
				for(BookInfoModel bookModel : bookModels) {
					if(bookType.equals(bookModel.getBook().getBookType()) 
							&& bookId.equals(bookModel.getBook().getId())) {
						bookModels2.add(bookModel);
					}
				}	
				
			}	
			
			model.addAttribute("bookModels", bookModels2);
			return "adminbooksectiondetails";	
	}

	@GetMapping("/view/{id}")
	public String view(@PathVariable String id ,Model model, @CookieValue("login_user_id") String cookieId) throws Exception {
		
		AccountAdmin loginaccount = accountAdminService.findByID(cookieId);
		if (loginaccount == null) {
			throw new Exception();
		}
		model.addAttribute("loginaccount", loginaccount);
		
		
		model.addAttribute("bookSectionDetail", bookSectionDetailService.findByID(Long.parseLong(id)));
		return "adminbooksectiondetailsview";
	}

}
