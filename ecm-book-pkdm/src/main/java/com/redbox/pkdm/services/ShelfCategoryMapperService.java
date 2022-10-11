package com.redbox.pkdm.services;

import java.util.List;

import com.redbox.pkdm.entities.ShelfCategory;
import com.redbox.pkdm.entities.ShelfCategoryMapper;

public interface ShelfCategoryMapperService {
	
	void save(ShelfCategoryMapper shelfCategoryMapper);
	
	List<ShelfCategory> findByBookId(String bookId);
		
	List<ShelfCategoryMapper> findMapperByBookId(String bookId);
	
	List<ShelfCategoryMapper> findAll();
	
	void delete(ShelfCategoryMapper shelfCategoryMapper);
	
	ShelfCategoryMapper findById(long id);
	
	List<ShelfCategoryMapper> findByErase(boolean erase);
	
	void save(ShelfCategoryMapper shelfCategoryMapper, String bookId, String catId);
}
