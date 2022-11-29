package com.redbox.pkdm.controllers.admin;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.redbox.pkdm.entities.AccountAdmin;
import com.redbox.pkdm.entities.Book;
import com.redbox.pkdm.entities.BookTag;
import com.redbox.pkdm.entities.SecurityInfo;
import com.redbox.pkdm.entities.ShelfAuthor;
import com.redbox.pkdm.entities.ShelfAuthorMapper;
import com.redbox.pkdm.entities.ShelfCategory;
import com.redbox.pkdm.entities.ShelfCategoryMapper;
import com.redbox.pkdm.entities.ShelfFeature;
import com.redbox.pkdm.entities.ShelfFeatureMapper;
import com.redbox.pkdm.entities.ShelfRelated;
import com.redbox.pkdm.entities.ShelfRelatedMapper;
import com.redbox.pkdm.services.AccountAdminService;
import com.redbox.pkdm.services.BookService;
import com.redbox.pkdm.services.BookTagService;
import com.redbox.pkdm.services.ShelfAuthorMapperService;
import com.redbox.pkdm.services.ShelfAuthorService;
import com.redbox.pkdm.services.ShelfCategoryMapperService;
import com.redbox.pkdm.services.ShelfCategoryService;
import com.redbox.pkdm.services.ShelfFeatureMapperService;
import com.redbox.pkdm.services.ShelfFeatureService;
import com.redbox.pkdm.services.ShelfRelatedMapperService;
import com.redbox.pkdm.services.ShelfRelatedService;
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
@RequestMapping("admin/book")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private ShelfCategoryService shelfCategoryService;
	
	@Autowired
	private ShelfFeatureService shelfFeatureService;
	
	@Autowired
	private ShelfAuthorService shelfAuthorService;
	
	@Autowired
	private ShelfCategoryMapperService shelfCategoryMapperService;
	
	@Autowired
	private ShelfFeatureMapperService shelfFeatureMapperService;
	
	@Autowired
	private ShelfAuthorMapperService shelfAuthorMapperService;
	
	@Autowired
	private AccountAdminService accountAdminService;
	
	@Autowired
	private BookTagService bookTagService;
	
	@Autowired
	private ShelfRelatedService shelfRelatedService;
	
	@Autowired
	private ShelfRelatedMapperService shelfRelatedMapperService;
	
	@GetMapping("/book-entire/{id}")
	public String initializeBookEntire(@PathVariable String id, Model model, String keyword, @CookieValue("login_user_id") String cookieId) throws Exception {
		
		AccountAdmin loginaccount = accountAdminService.findByID(cookieId);
		if (loginaccount == null) { throw new Exception();}
		
		model.addAttribute("loginaccount", loginaccount);
		model.addAttribute("categories", shelfCategoryService.findByEraseAndOrderId(false));
		model.addAttribute("features", shelfFeatureService.findByEraseAndOrderId(false));
		model.addAttribute("authors", shelfAuthorService.findByEraseAndOrderId(false));
		model.addAttribute("relateds", shelfRelatedService.findByErase(false));
		
		List<Book> books = new ArrayList<>();
		for (Book book : bookService.findByBookType("ENTIRE")) {
			
			List<ShelfCategory> shelfCategories = shelfCategoryMapperService.findByBook(book.getId());
			if (shelfCategories != null && shelfCategories.size() > 0) {
				book.setCategory(shelfCategories.get(0).getName());
			}
			
			List<ShelfRelated> shelfRelateds = shelfRelatedMapperService.findByBook(book.getId());
			if (shelfRelateds != null && shelfRelateds.size() > 0) {
				book.setRelated(shelfRelateds.get(0).getName());
			}
			
			List<ShelfAuthor> shelfAuthors = shelfAuthorMapperService.findByBook(book.getId());
			if (shelfAuthors != null && shelfAuthors.size() > 0) {
				book.setAuthor(shelfAuthors.get(0).getName());
			}
			
			double e_value = (book.getE_price() * book.getE_discount())/100;
			book.setE_actual_price(book.getE_price()-e_value);

			double p_value = (book.getP_price() * book.getP_discount())/100;
			book.setP_actual_price(book.getP_price()-p_value);
			
			books.add(book);
			
		}
		
		model.addAttribute("books", books);
		
		if(id.equals("new")) {
			
			Book book = new Book();
			book.setBookType("ENTIRE");
			book.setElectronicBook(true);
			model.addAttribute("book", book);
			
			
		} else {	
			model.addAttribute("book", bookService.findByID(id));
		}
		
		return "adminbookentire";
	}
	
	@GetMapping("/book-serie/{id}")
	public String initializeBookSerie(@PathVariable String id, Model model, String keyword, @CookieValue("login_user_id") String cookieId) throws Exception {
		
		AccountAdmin loginaccount = accountAdminService.findByID(cookieId);
		if (loginaccount == null) { throw new Exception();}
		
		model.addAttribute("loginaccount", loginaccount);
		model.addAttribute("categories", shelfCategoryService.findByEraseAndOrderId(false));
		model.addAttribute("features", shelfFeatureService.findByEraseAndOrderId(false));
		model.addAttribute("authors", shelfAuthorService.findByEraseAndOrderId(false));
		model.addAttribute("tags", bookTagService.findByErase(false));
		
		List<Book> books = new ArrayList<>();
		for (Book book : bookService.findByBookType("SERIE")) {
			
			List<ShelfCategory> shelfCategories = shelfCategoryMapperService.findByBook(book.getId());
			if (shelfCategories != null && shelfCategories.size() > 0) {
				book.setCategory(shelfCategories.get(0).getName());
			}
			
			List<ShelfAuthor> shelfAuthors = shelfAuthorMapperService.findByBook(book.getId());
			if (shelfAuthors != null && shelfAuthors.size() > 0) {
				book.setAuthor(shelfAuthors.get(0).getName());
			}
			
			
			double e_value = (book.getE_price() * book.getE_discount())/100;
			book.setE_actual_price(book.getE_price()-e_value);

			double p_value = (book.getP_price() * book.getP_discount())/100;
			book.setP_actual_price(book.getP_price()-p_value);
			
			books.add(book);
			
		}
		
		model.addAttribute("books", books);
		
		if(id.equals("new")) {
			
			Book book = new Book();
			book.setBookType("SERIE");
			book.setElectronicBook(true);
			model.addAttribute("book", book);
			
			
		} else {	
			model.addAttribute("book", bookService.findByID(id));
		}
		
		return "adminbookserie";
	}
	
	@PostMapping("/book-entire/save")
	public String saveBookEntire(@ModelAttribute("book") Book book, @CookieValue("login_user_id") String cookieId, String categoryId, String relatedId, String authorId, RedirectAttributes redirAttrs) {	
		System.out.println("Work");
		if (book.getId().isEmpty()) {
		
			if (book.getFile() != null) {
				book.setImage(ImageUploadUtility.upload(book.getFile()));
			}
			
			book.setBookType("ENTIRE");
			book.setSecurityInfo(new SecurityInfo(cookieId));
			bookService.save(book);
			redirAttrs.addFlashAttribute("save","အချက်အလက်များကို သိမ်းဆဲလိုက်ပါပြီ။");
		
		} else {
			
			Book book2 = bookService.findByID(book.getId());
			book2.setName(book.getName());
			book2.setPhysicalBook(book.isPhysicalBook());
			book2.setElectronicBook(book.isElectronicBook());
			book2.setE_price(book.getE_price());
			book2.setP_price(book.getP_price());
			book2.setDescription(book.getDescription());
			book2.setE_discount(book.getE_discount());
			book2.setP_discount(book.getP_discount());
			
			if (!book.getFile().isEmpty()) {
				book2.setImage(ImageUploadUtility.upload(book.getFile()));
			}
			
			book2.setBookType("ENTIRE");
			book2.getSecurityInfo().setUpdateDate(LocalDate.now());
			book2.getSecurityInfo().setUpdateTime(LocalTime.now().toString());
			book2.getSecurityInfo().setUpdateUser(cookieId);
			bookService.save(book2);
			redirAttrs.addFlashAttribute("update","အချက်အလက်များကို ပြင်ဆင်လိုက်ပါပြီ။");
			
		}
		
		ShelfCategory shelfCategory = shelfCategoryService.findByID(Long.parseLong(categoryId));
		ShelfCategoryMapper shelfCategoryMapper = new ShelfCategoryMapper(shelfCategory, book);
		shelfCategoryMapperService.save(shelfCategoryMapper);
		
		ShelfAuthor shelfAuthor = shelfAuthorService.findByID(Long.parseLong(authorId));
		ShelfAuthorMapper shelfAuthorMapper = new ShelfAuthorMapper(shelfAuthor, book);
		shelfAuthorMapperService.save(shelfAuthorMapper);
		
		ShelfRelated shelfRelated = shelfRelatedService.findByID(Long.parseLong(relatedId));
		ShelfRelatedMapper shelfRelatedMapper = new ShelfRelatedMapper(shelfRelated, book);
		shelfRelatedMapperService.save(shelfRelatedMapper);
		
		ShelfFeature shelfFeature = shelfFeatureService.findByID(1);
		ShelfFeatureMapper shelfFeatureMapper = new ShelfFeatureMapper(shelfFeature, book);
		shelfFeatureMapperService.save(shelfFeatureMapper);
		
		return "redirect:/admin/book/book-entire/new";	
	}
	
	@PostMapping("/book-serie/save")
	public String saveBookSerie(@ModelAttribute("book") Book book, @CookieValue("login_user_id") String cookieId, String categoryId, String relatedId, String authorId, RedirectAttributes redirAttrs) {	
		if (book.getId().isEmpty()) {
		
			if (!book.getFile().isEmpty()) {
				book.setImage(ImageUploadUtility.upload(book.getFile()));
			}
			
			BookTag bookTag = bookTagService.findByID(Long.parseLong(relatedId));
			book.setBookTag(bookTag);
			
			book.setBookType("SERIE");
			book.setSecurityInfo(new SecurityInfo(cookieId));
			bookService.save(book);
			redirAttrs.addFlashAttribute("save","အချက်အလက်များကို သိမ်းဆဲလိုက်ပါပြီ။");
		
		} else {
			
			Book book2 = bookService.findByID(book.getId());
			book2.setName(book.getName());
			book2.setPhysicalBook(book.isPhysicalBook());
			book2.setElectronicBook(book.isElectronicBook());
			book2.setE_price(book.getE_price());
			book2.setP_price(book.getP_price());
			book2.setDescription(book.getDescription());
			
			if (!book.getFile().isEmpty()) {
				book2.setImage(ImageUploadUtility.upload(book.getFile()));
			}
			
			BookTag bookTag = bookTagService.findByID(Long.parseLong(relatedId));
			book2.setBookTag(bookTag);
			
			book2.setBookType("SERIE");
			book2.getSecurityInfo().setUpdateDate(LocalDate.now());
			book2.getSecurityInfo().setUpdateTime(LocalTime.now().toString());
			book2.getSecurityInfo().setUpdateUser(cookieId);
			bookService.save(book2);
			redirAttrs.addFlashAttribute("update","အချက်အလက်များကို ပြင်ဆင်လိုက်ပါပြီ။");
			
		}
		
		ShelfCategory shelfCategory = shelfCategoryService.findByID(Long.parseLong(categoryId));
		ShelfCategoryMapper shelfCategoryMapper = new ShelfCategoryMapper(shelfCategory, book);
		shelfCategoryMapperService.save(shelfCategoryMapper);
		
		ShelfAuthor shelfAuthor = shelfAuthorService.findByID(Long.parseLong(authorId));
		ShelfAuthorMapper shelfAuthorMapper = new ShelfAuthorMapper(shelfAuthor, book);
		shelfAuthorMapperService.save(shelfAuthorMapper);
		
		ShelfFeature shelfFeature = shelfFeatureService.findByID(1);
		ShelfFeatureMapper shelfFeatureMapper = new ShelfFeatureMapper(shelfFeature, book);
		shelfFeatureMapperService.save(shelfFeatureMapper);
		
		return "redirect:/admin/book/book-serie/new";	
	}
	
	@GetMapping("/book-entire/delete/{id}")
	public String deleteBookEntire(@PathVariable String id, RedirectAttributes redirAttrs) {
		bookService.delete(bookService.findByID(id));
		redirAttrs.addFlashAttribute("validation","အချက်အလက်များကို ပယ်ဖျက်လိုက်ပါသည်။");
		return "redirect:/admin/book/book-entire/new";
	}
	
	@GetMapping("/book-serie/delete/{id}")
	public String deleteBookSerie(@PathVariable String id, RedirectAttributes redirAttrs) {
		bookService.delete(bookService.findByID(id));
		redirAttrs.addFlashAttribute("validation","အချက်အလက်များကို ပယ်ဖျက်လိုက်ပါသည်။");
		return "redirect:/admin/book/book-serie/new";
	}

}
