package com.redbox.pkdm.api;

import java.util.ArrayList;
import java.util.List;

import com.redbox.pkdm.entities.BookSection;
import com.redbox.pkdm.models.BookSectionModel;
import com.redbox.pkdm.services.BookSectionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/booksection")
public class BookSectionAPI {
	
	@Autowired
	private BookSectionService bookSectionService;

	@GetMapping("/findbybook/{id}")
	public List<BookSectionModel> findByBook (@PathVariable String id) {
		try {
			BookSectionModel model = new BookSectionModel();
			List<BookSectionModel> models = new ArrayList<>();
			List<BookSection> bookSections = bookSectionService.findByBookId(id, false);
			for (BookSection b : bookSections) {
				model.setId(b.getId());
				model.setImage(b.getImage());
				model.setName(b.getName());
				model.setDescription(b.getDescription());
				model.setSort_no(b.getSort_no());
				model.setPrice(b.getPrice());
				models.add(model);
				model = new BookSectionModel();
			}
			return models;
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}
	
}
