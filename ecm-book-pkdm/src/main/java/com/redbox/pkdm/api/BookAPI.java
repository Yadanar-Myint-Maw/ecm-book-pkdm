package com.redbox.pkdm.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redbox.pkdm.entities.Book;
import com.redbox.pkdm.entities.BookSection;
import com.redbox.pkdm.entities.BookTag;
import com.redbox.pkdm.entities.PurchasedTransition;
import com.redbox.pkdm.entities.ShelfAuthor;
import com.redbox.pkdm.entities.ShelfCategory;
import com.redbox.pkdm.entities.ShelfRelated;
import com.redbox.pkdm.models.BookInformationModel;
import com.redbox.pkdm.models.BookModel;
import com.redbox.pkdm.models.BookSectionModel;
import com.redbox.pkdm.models.MyBookModel;
import com.redbox.pkdm.services.BookSectionService;
import com.redbox.pkdm.services.BookService;
import com.redbox.pkdm.services.BookTagService;
import com.redbox.pkdm.services.PurchasedTransitionService;
import com.redbox.pkdm.services.ShelfAuthorMapperService;
import com.redbox.pkdm.services.ShelfCategoryMapperService;
import com.redbox.pkdm.services.ShelfRelatedMapperService;

@RestController
@RequestMapping("api/book")
public class BookAPI {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private BookSectionService bookSectionService;
	
	@Autowired
	private ShelfAuthorMapperService shelfAuthorMapperService;
	
	@Autowired
	private ShelfRelatedMapperService shelfRelatedMapperService;
	
	@Autowired
	private ShelfCategoryMapperService shelfCategoryMapperService;
	
	@Autowired
	private PurchasedTransitionService purchasedTransitionService;
	
	@Autowired
	private BookTagService bookTagService;

	@GetMapping("/findbyuser/{userID}")
	public List<BookModel> findByOption (@PathVariable String userID) {
		try {
			
			List<BookModel> bookModels = new ArrayList<>();
			
			for (Book book : bookService.findByErase(false)) {
				
				BookInformationModel bookInformationModel = new BookInformationModel();
				bookInformationModel.setId(book.getId());
				bookInformationModel.setImage(book.getImage());
				bookInformationModel.setName(book.getName());
				bookInformationModel.setDescription(book.getDescription());
				bookInformationModel.setAuthor(getAuthorName(book.getId()));
				bookInformationModel.setCategory(getCategoryName(book.getId()));
				bookInformationModel.setRelated(getRelatedName(book.getId()));
				bookInformationModel.setRelatedDiscount(getRelatedPrice(book.getId()));
				bookInformationModel.setTag(getTagName(book));
				bookInformationModel.setTagDiscount(getTagPrice(getTagName(book))); // Myosandikyaw
				bookInformationModel.setBookType(book.getBookType());
				bookInformationModel.setElectronicBook(book.isElectronicBook());
				bookInformationModel.setPhysicalBook(book.isPhysicalBook());
				bookInformationModel.setE_price(book.getE_price());
				bookInformationModel.setP_price(book.getP_price());
				bookInformationModel.setE_discount(book.getE_discount());
				bookInformationModel.setP_discount(book.getP_discount());
				bookInformationModel.setE_actual_price(getActualPrice(book.getE_price(), book.getE_discount()));
				bookInformationModel.setP_actual_price(getActualPrice(book.getP_price(), book.getP_discount()));
				bookInformationModel.setE_purchased(false);
				bookInformationModel.setP_purchased(false);
				
				List<BookSectionModel> bookSectionModels = new ArrayList<>();
				for (BookSection bookSection : bookSectionService.findByBookId(book.getId(), false)) {
					BookSectionModel bookSectionModel = new BookSectionModel();
					bookSectionModel.setNo(bookSection.getSort_no());
					bookSectionModel.setImage(bookSection.getImage());
					bookSectionModel.setName(bookSection.getName());
					bookSectionModel.setDescription(bookSection.getDescription());
					bookSectionModels.add(bookSectionModel);
				}
				
				bookModels.add(new BookModel(bookInformationModel, bookSectionModels));
				
			}
			
			Collections.reverse(bookModels);
			return bookModels;
			
		} catch (Exception e) {
			
			return new ArrayList<>();
			
		}
	}
	
	private String getAuthorName (String bookID) {
		List<ShelfAuthor> authors = shelfAuthorMapperService.findByBook(bookID);
		if (authors != null && authors.size() > 0) {
			return authors.get(0).getName();
		}
		return "-";
	}
	
	private String getCategoryName (String bookID) {
		List<ShelfCategory> categories = shelfCategoryMapperService.findByBook(bookID);
		if (categories != null && categories.size() > 0) {
			return categories.get(0).getName();
		}
		return "-";
	}
	
	private String getRelatedName (String bookID) {
		List<ShelfRelated> relateds = shelfRelatedMapperService.findByBook(bookID);
		if (relateds != null && relateds.size() > 0) {
			return relateds.get(0).getName();
		}
		return "-";
	}
	
	private double getRelatedPrice (String bookID) {
		List<ShelfRelated> relateds = shelfRelatedMapperService.findByBook(bookID);
		if (relateds != null && relateds.size() > 0) {
			return relateds.get(0).getPrice();
		}
		return 0;
	}
	
	private String getTagName (Book book) {
		if (book.getBookTag() != null) {
			return book.getBookTag().getName();
		}
		return "-";
	}
	
	//MyosandiKyaw
	private double getTagPrice (String name) {
		List<BookTag> bookTags = bookTagService.findByName(name);
		if (bookTags != null && bookTags.size() > 0) {
			return bookTags.get(0).getDiscount();
		}
		return 0;
	}
	
	
	private double getActualPrice (double price, int discount) {
		if (discount != 0) {
			return price - ((price * discount) / 100);
		}
		return price;
	}
	
	@GetMapping("/findmybooks/{userID}")
	public List<MyBookModel> findMyBooks (@PathVariable String userID) {
		try {
			MyBookModel model = new MyBookModel();
			List<MyBookModel> models = new ArrayList<>();
			List<PurchasedTransition> purchasedTransitions = purchasedTransitionService.findByUser(userID);
			for (PurchasedTransition purchasedTransition : purchasedTransitions) {
				model.setBookID(purchasedTransition.getBook().getId());
				model.setBookType(purchasedTransition.getBookType());
				if (purchasedTransition.getDeliveryInfo() != null) {
					model.setAddress(purchasedTransition.getDeliveryInfo().getPhone() + ", " + purchasedTransition.getDeliveryInfo().getDeliveryRegion().getName() + ", " + purchasedTransition.getDeliveryInfo().getDeliveryTownship().getName() + ", " + purchasedTransition.getDeliveryInfo().getAddressDetail());
				}
				models.add(model);
				model = new MyBookModel();
			}
			return models.stream().sorted(Comparator.comparing(MyBookModel::getBookID).reversed()).collect(Collectors.toList());
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}
	
}
