package com.redbox.pkdm.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.redbox.pkdm.entities.ShelfCategory;
import com.redbox.pkdm.services.ShelfCategoryService;

@RestController
@RequestMapping("admin/shelf/categories/api")
public class ShelfCategoryApi {
	
	@Autowired
	private ShelfCategoryService shelfCategoryService;
	
	
	
	@GetMapping
	List<ShelfCategory> getAllShelfCategories(){
		return shelfCategoryService.findByEraseAndOrderId(false);
	}
	

}
