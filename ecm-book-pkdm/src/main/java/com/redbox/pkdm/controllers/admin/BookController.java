package com.redbox.pkdm.controllers.admin;

import java.time.LocalDate;
import java.time.LocalTime;
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

import com.redbox.pkdm.entities.AccountAdmin;
import com.redbox.pkdm.entities.Book;
import com.redbox.pkdm.entities.BookSection;
import com.redbox.pkdm.entities.SecurityInfo;
import com.redbox.pkdm.entities.ShelfAuthor;
import com.redbox.pkdm.entities.ShelfAuthorMapper;
import com.redbox.pkdm.entities.ShelfCategory;
import com.redbox.pkdm.entities.ShelfCategoryMapper;
import com.redbox.pkdm.entities.ShelfFeature;
import com.redbox.pkdm.entities.ShelfFeatureMapper;
import com.redbox.pkdm.services.AccountAdminService;
import com.redbox.pkdm.services.BookService;
import com.redbox.pkdm.services.ShelfAuthorMapperService;
import com.redbox.pkdm.services.ShelfAuthorService;
import com.redbox.pkdm.services.ShelfCategoryMapperService;
import com.redbox.pkdm.services.ShelfCategoryService;
import com.redbox.pkdm.services.ShelfFeatureMapperService;
import com.redbox.pkdm.services.ShelfFeatureService;
import com.redbox.pkdm.utilities.ImageUploadUtility;

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
	
	@GetMapping("/book-series/{id}")
	public String initialize(@PathVariable String id, Model model, String keyword, @CookieValue("login_user_id") String cookieId) throws Exception {
		
		AccountAdmin loginaccount = accountAdminService.findByID(cookieId);
		if (loginaccount == null) {
			throw new Exception();
		}
		model.addAttribute("loginaccount", loginaccount);
		
		boolean featureNew = true;
		
		model.addAttribute("categories", shelfCategoryService.findByEraseAndOrderId(false));
		model.addAttribute("features", shelfFeatureService.findByEraseAndOrderId(false));
		model.addAttribute("authors", shelfAuthorService.findByEraseAndOrderId(false));
		model.addAttribute("selectCategory", "Select Category");
		model.addAttribute("selectFeature", "Select Feature");
		model.addAttribute("selectAuthor", "Select Author");
		model.addAttribute("bookType", id);
		
		if(id.equals("ENTIRE")) {
			Book book = new Book();
			book.setBookType("ENTIRE");
			book.setElectronicBook(true);
			model.addAttribute("featureNew", featureNew);
			model.addAttribute("book", book);
		}
		else if (id.equals("SERIE")) {
			System.out.println("1");
			Book book = new Book();
			book.setBookType("SERIE");
			book.setElectronicBook(true);
			model.addAttribute("featureNew", featureNew);
			model.addAttribute("book", book);
		}
		else {
		
			featureNew = false;
			model.addAttribute("featureNew", featureNew);
			model.addAttribute("book", bookService.findByID(id));
			
			String selectCategory = shelfCategoryMapperService.findByBookId(id).get(0).getName();
			String selectCategoryId = String.valueOf(shelfCategoryMapperService.findByBookId(id).get(0).getId());
			model.addAttribute("selectCategory", selectCategory);
			model.addAttribute("selectCategoryId", selectCategoryId);	
			
			String selectAuthor = shelfAuthorMapperService.findByBookId(id).get(0).getName();
			String selectAuthorId = String.valueOf(shelfAuthorMapperService.findByBookId(id).get(0).getId());
			model.addAttribute("selectAuthor", selectAuthor);
			model.addAttribute("selectAuthorId", selectAuthorId);
			
			String selectFeature = shelfFeatureMapperService.findByBookId(id).get(0).getName();
			String selectFeatureId = String.valueOf(shelfAuthorMapperService.findByBookId(id).get(0).getId());
			model.addAttribute("selectFeature", selectFeature);
			model.addAttribute("selectFeatureId", selectFeatureId);
		}
			return "admininventorybooks";
	}
	
	@GetMapping("/add-new-book-section/{bookId}")
	public String addNewSection(@PathVariable String bookId, Model model, @CookieValue("login_user_id") String cookieId) throws Exception {
		
		AccountAdmin loginaccount = accountAdminService.findByID(cookieId);
		if (loginaccount == null) {
			throw new Exception();
		}
		model.addAttribute("loginaccount", loginaccount);
		
		
		model.addAttribute("books", bookService.findByErase(false));
		String bookName = bookService.findByID(bookId).getName();
		model.addAttribute("bookName", bookName);
		model.addAttribute("bookSection", new BookSection());
		boolean description = true;
		model.addAttribute("description", description);
		
		String selectBook = bookService.findByID(bookId).getName();
		String selectBookId = bookService.findByID(bookId).getId();
		
		model.addAttribute("selectBook", selectBook);
		model.addAttribute("selectBookId", selectBookId);

		return "admininventorybooksections";
	
	}
	
	
	@PostMapping("/book-series/save")
	public String save(@ModelAttribute("book") Book book, @CookieValue("login_user_id") String cookieId, String categoryId, String featureNew, String featureId, String authorId) {	
	
		String bookType = "";
		
		if (book.getId().isEmpty()) {
			System.out.println("3");
			System.out.println(book.getId());
			book.setSecurityInfo(new SecurityInfo(cookieId));
			
		if (book.getTranslatorFile() != null) {
				book.setTranslatorImage(ImageUploadUtility.upload(book.getTranslatorFile()));
			}
		
		if (book.getFile() != null) {
			book.setImage(ImageUploadUtility.upload(book.getFile()));
		}
		
		bookService.save(book);
		
		bookType = book.getBookType();
		
		ShelfCategory shelfCategory = shelfCategoryService.findByID(Long.parseLong(categoryId));
		ShelfCategoryMapper shelfCategoryMapper = new ShelfCategoryMapper(shelfCategory, book);
		shelfCategoryMapper.setSecurityInfo(new SecurityInfo(cookieId));
		shelfCategoryMapperService.save(shelfCategoryMapper);
		
		if(featureNew != null) {
			ShelfFeature shelfFeature = shelfFeatureService.findByName(featureNew);
			ShelfFeatureMapper shelfFeatureMapper = new ShelfFeatureMapper(shelfFeature, book);
			shelfFeatureMapper.setSecurityInfo(new SecurityInfo(cookieId));
			shelfFeatureMapperService.save(shelfFeatureMapper);
		}
		
		ShelfFeature shelfFeature = shelfFeatureService.findByID(Long.parseLong(featureId));
		ShelfFeatureMapper shelfFeatureMapper = new ShelfFeatureMapper(shelfFeature, book);
		shelfFeatureMapper.setSecurityInfo(new SecurityInfo(cookieId));
		shelfFeatureMapperService.save(shelfFeatureMapper);
		
		ShelfAuthor shelfAuthor = shelfAuthorService.findByID(Long.parseLong(authorId));
		ShelfAuthorMapper shelfAuthorMapper = new ShelfAuthorMapper(shelfAuthor, book);
		shelfAuthorMapper.setSecurityInfo(new SecurityInfo(cookieId));
		shelfAuthorMapperService.save(shelfAuthorMapper);
		
		} else {
			
			System.out.println("2");
			Book book2 = bookService.findByID(book.getId());
			book2.setName(book.getName());
			book2.setPhysicalBook(book.isPhysicalBook());
			book2.setElectronicBook(book.isElectronicBook());
			book2.setPrice(book.getPrice());
			book2.setTrailer(book.getTrailer());
			book2.setBookType(book.getBookType());
			book2.setForeword(book.getForeword());
			book2.setDescription(book.getDescription());
			if (book.getTranslatorFile() != null) {
				book2.setTranslatorImage(ImageUploadUtility.upload(book.getTranslatorFile()));
			}
			if (book.getFile() != null) {
				book2.setImage(ImageUploadUtility.upload(book.getFile()));
			}
			book2.getSecurityInfo().setUpdateDate(LocalDate.now());
			book2.getSecurityInfo().setUpdateTime(LocalTime.now().toString());
			book2.getSecurityInfo().setUpdateUser(cookieId);
			bookService.save(book2);
			
			bookType = book2.getBookType();
						
			ShelfCategory shelfCategory = shelfCategoryService.findByID(Long.parseLong(categoryId));
			List<ShelfCategoryMapper> shelfCategoryMappers = shelfCategoryMapperService.findMapperByBookId(book.getId());
			for(ShelfCategoryMapper mapper : shelfCategoryMappers) {
				mapper.setShelfCategory(shelfCategory);
				mapper.setBook(book2);
				shelfCategoryMapperService.save(mapper);
			}
			
			ShelfAuthor shelfAuthor = shelfAuthorService.findByID(Long.parseLong(authorId));
			List<ShelfAuthorMapper> shelfAuthorMappers = shelfAuthorMapperService.findMapperByBookId(book.getId());
			for(ShelfAuthorMapper mapper : shelfAuthorMappers) {
				mapper.setShelfAuthor(shelfAuthor);
				mapper.setBook(book2);
				shelfAuthorMapperService.save(mapper);
			}
			
			ShelfFeature shelfFeature = shelfFeatureService.findByID(Long.parseLong(featureId));
			List<ShelfFeatureMapper> shelfFeatureMappers = shelfFeatureMapperService.findMapperByBookId(book.getId());
			for(ShelfFeatureMapper mapper : shelfFeatureMappers) {
				mapper.setShelfFeature(shelfFeature);
				mapper.setBook(book2);
				shelfFeatureMapperService.save(mapper);
			}
		}
		
		return "redirect:/admin/book/book-series/" + bookType;	
	}
	
	@GetMapping("/book-series/delete/{id}")
	public String delete(@PathVariable String id) {
		bookService.delete(bookService.findByID(id));
		return "redirect:/admin/book/show-books/" + bookService.findByID(id).getBookType();
	}
	
	@GetMapping("/show-books/{bookType}")
	public String showbooks(@PathVariable String bookType, Model model, String keyword, @CookieValue("login_user_id") String cookieId) throws Exception {
		
		AccountAdmin loginaccount = accountAdminService.findByID(cookieId);
		if (loginaccount == null) {
			throw new Exception();
		}
		model.addAttribute("loginaccount", loginaccount);
		
		model.addAttribute("bookType", bookType);
		
		boolean addBtn = true;
		if(!bookType.equals("SERIE")) {
			addBtn = false;
		}
		
		
		model.addAttribute("addBtn", addBtn);
		
		if (keyword == null && bookType.equals("SERIE")) {		
			model.addAttribute("books", bookService.findByBookType(bookType));
		}
		else if (keyword == null && bookType.equals("ENTIRE")) {		
			model.addAttribute("books", bookService.findByBookType(bookType));
		}else {
			model.addAttribute("books", bookService.findByNameLikeAndBookType(keyword, bookType));
		}
		return "adminbooks";	
	}
	
	@GetMapping("/read-more/{id}")
	public String readMore(@PathVariable String id, Model model, @CookieValue("login_user_id") String cookieId) throws Exception {
		
		AccountAdmin loginaccount = accountAdminService.findByID(cookieId);
		if (loginaccount == null) {
			throw new Exception();
		}
		model.addAttribute("loginaccount", loginaccount);
		
		
		model.addAttribute("bookDetails", bookService.findByID(id));
		model.addAttribute("bookDetailsAuthor", shelfAuthorMapperService.findByBookId(id).get(0));
		model.addAttribute("bookDetailsCategory", shelfCategoryMapperService.findByBookId(id).get(0));
		model.addAttribute("bookDetailsFeature", shelfFeatureMapperService.findByBookId(id).get(0));
		return "adminbookview";
	}	
	
}
