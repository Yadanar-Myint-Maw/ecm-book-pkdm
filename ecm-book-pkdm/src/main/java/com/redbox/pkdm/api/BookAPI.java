package com.redbox.pkdm.api;

import java.util.ArrayList;
import java.util.List;

import com.redbox.pkdm.entities.Book;
import com.redbox.pkdm.entities.PurchasedTransition;
import com.redbox.pkdm.entities.ShelfAuthor;
import com.redbox.pkdm.entities.ShelfCategory;
import com.redbox.pkdm.entities.ShelfFeature;
import com.redbox.pkdm.entities.ShelfFeatureMapper;
import com.redbox.pkdm.models.BookModel;
import com.redbox.pkdm.services.BookService;
import com.redbox.pkdm.services.PurchasedTransitionService;
import com.redbox.pkdm.services.ShelfAuthorMapperService;
import com.redbox.pkdm.services.ShelfCategoryMapperService;
import com.redbox.pkdm.services.ShelfFeatureMapperService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/book")
public class BookAPI {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private ShelfAuthorMapperService shelfAuthorMapperService;
	
	@Autowired
	private ShelfFeatureMapperService shelfFeatureMapperService;
	
	@Autowired
	private ShelfCategoryMapperService shelfCategoryMapperService;
	
	@Autowired
	private PurchasedTransitionService purchasedTransitionService;

	@GetMapping("/findbyoption/{option}")
	public List<BookModel> findByOption (@PathVariable String option) {
		try {
			List<BookModel> bookModels = new ArrayList<>();
			BookModel model = new BookModel();
			List<Book> books = new ArrayList<>();
			if (option.equals("E")) {
				books = bookService.findByEBook();
			} else {
				books = bookService.findByPBook();
			}
			for (Book b : books) {
				model.setId(b.getId());
				model.setImage(b.getImage());
				model.setName(b.getName());
				model.setDescription(b.getDescription());
				model.setBookType(b.getBookType());
				model.setElectronicBook(b.isElectronicBook());
				model.setPhysicalBook(b.isPhysicalBook());
				model.setTrailer(b.getTrailer());
				model.setTranslatorImage(b.getTranslatorImage());
				List<ShelfAuthor>  shelfAuthors = shelfAuthorMapperService.findByBookId(b.getId());
				if (shelfAuthors != null && shelfAuthors.size() > 0) {
					model.setAuthor(shelfAuthors.get(0).getName());
				}
				List<ShelfFeature> shelfFeatures = shelfFeatureMapperService.findByBookId(b.getId());
				if (shelfFeatures != null && shelfFeatures.size() > 0) {
					model.setFeature(shelfFeatures.get(0).getName());
				}
				List<ShelfCategory> shelfCategories = shelfCategoryMapperService.findByBookId(b.getId());
				if (shelfCategories != null && shelfCategories.size() > 0) {
					model.setCategory(shelfCategories.get(0).getName());
				}
				bookModels.add(model);
				model = new BookModel();
			}
			return bookModels;
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}
	
	@GetMapping("/findbyshelffeature/{id}")
	public List<BookModel> findByShelfFeature (@PathVariable long id) {
		try {
			Book b = new Book();
			BookModel model = new BookModel();
			List<BookModel> bookModels = new ArrayList<>();
			List<ShelfFeatureMapper> shelfFeatureMappers = shelfFeatureMapperService.findByShelfFeature(id);
			for (ShelfFeatureMapper m : shelfFeatureMappers) {
				b = m.getBook();
				model.setId(b.getId());
				model.setImage(b.getImage());
				model.setName(b.getName());
				model.setDescription(b.getDescription());
				model.setBookType(b.getBookType());
				model.setElectronicBook(b.isElectronicBook());
				model.setPhysicalBook(b.isPhysicalBook());
				model.setTrailer(b.getTrailer());
				model.setTranslatorImage(b.getTranslatorImage());
				List<ShelfAuthor>  shelfAuthors = shelfAuthorMapperService.findByBookId(b.getId());
				if (shelfAuthors != null && shelfAuthors.size() > 0) {
					model.setAuthor(shelfAuthors.get(0).getName());
				}
				List<ShelfFeature> shelfFeatures = shelfFeatureMapperService.findByBookId(b.getId());
				if (shelfFeatures != null && shelfFeatures.size() > 0) {
					model.setFeature(shelfFeatures.get(0).getName());
				}
				List<ShelfCategory> shelfCategories = shelfCategoryMapperService.findByBookId(b.getId());
				if (shelfCategories != null && shelfCategories.size() > 0) {
					model.setCategory(shelfCategories.get(0).getName());
				}
				bookModels.add(model);
				model = new BookModel();
				b = new Book();
			}
			return bookModels;
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}
	
	@GetMapping("/findbypurchased/{id}")
	public List<BookModel> findByPurchased (@PathVariable String id) {
		try {
			Book b = new Book();
			BookModel model = new BookModel();
			List<BookModel> bookModels = new ArrayList<>();
			List<PurchasedTransition> purchasedTransitions = purchasedTransitionService.findByUser(id);
			for (PurchasedTransition m : purchasedTransitions) {
				b = m.getBook();
				model.setId(b.getId());
				model.setImage(b.getImage());
				model.setName(b.getName());
				model.setDescription(b.getDescription());
				model.setBookType(b.getBookType());
				model.setElectronicBook(b.isElectronicBook());
				model.setPhysicalBook(b.isPhysicalBook());
				model.setTrailer(b.getTrailer());
				model.setTranslatorImage(b.getTranslatorImage());
				List<ShelfAuthor>  shelfAuthors = shelfAuthorMapperService.findByBookId(b.getId());
				if (shelfAuthors != null && shelfAuthors.size() > 0) {
					model.setAuthor(shelfAuthors.get(0).getName());
				}
				List<ShelfFeature> shelfFeatures = shelfFeatureMapperService.findByBookId(b.getId());
				if (shelfFeatures != null && shelfFeatures.size() > 0) {
					model.setFeature(shelfFeatures.get(0).getName());
				}
				List<ShelfCategory> shelfCategories = shelfCategoryMapperService.findByBookId(b.getId());
				if (shelfCategories != null && shelfCategories.size() > 0) {
					model.setCategory(shelfCategories.get(0).getName());
				}
				bookModels.add(model);
				model = new BookModel();
				b = new Book();
			}
			return bookModels;
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}
	
}
