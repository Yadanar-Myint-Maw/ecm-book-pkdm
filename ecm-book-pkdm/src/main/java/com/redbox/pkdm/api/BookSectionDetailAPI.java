package com.redbox.pkdm.api;

import java.util.ArrayList;
import java.util.List;

import com.redbox.pkdm.entities.BookSectionDetail;
import com.redbox.pkdm.models.BookSectionDetailModel;
import com.redbox.pkdm.services.BookSectionDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/booksectiondetail")
public class BookSectionDetailAPI {
	
	@Autowired
	private BookSectionDetailService bookSectionDetailService;

	@GetMapping("/findbybooksection/{id}")
	public List<BookSectionDetailModel> findByBookSection (@PathVariable String id) {
		try {
			BookSectionDetailModel model = new BookSectionDetailModel();
			List<BookSectionDetailModel> models = new ArrayList<>();
			List<BookSectionDetail> bookSectionDetails = bookSectionDetailService.findByBookSectionId(false, Long.parseLong(id));
			for (BookSectionDetail b : bookSectionDetails) {
				model.setId(b.getId());
				model.setTitile(b.getName());
				model.setDescription(b.getDescription());
				models.add(model);
				model = new BookSectionDetailModel();
			}
			return models;
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}
	
}
