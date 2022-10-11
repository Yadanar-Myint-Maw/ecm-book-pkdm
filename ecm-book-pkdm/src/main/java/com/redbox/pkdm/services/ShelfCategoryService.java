package com.redbox.pkdm.services;

import java.util.List;

import com.redbox.pkdm.entities.ShelfCategory;

public interface ShelfCategoryService {
	
	void save(ShelfCategory shelfCategory);

	void delete(ShelfCategory shelfCategory);

	ShelfCategory findByID(long id);
			
	List<ShelfCategory> findByEraseAndOrderId(boolean erase);	
	
	List<ShelfCategory> findByNameLike(String name);	

	long findCountByShelfCategory();

}
