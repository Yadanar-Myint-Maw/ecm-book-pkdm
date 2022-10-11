package com.redbox.pkdm.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redbox.pkdm.entities.ShelfAuthor;
import com.redbox.pkdm.services.ShelfAuthorService;

@RestController
@RequestMapping("admin/shelf/authors/api")
public class ShelfAuthorApi {
	
	@Autowired
	private ShelfAuthorService shelfAuthorService;
	
	
	@GetMapping
	List<ShelfAuthor> getAllShelfAuthors(){
		return shelfAuthorService.findByEraseAndOrderId(false);
	}
	
	
	
	
	
	
	
	
	

}
