package com.redbox.pkdm.services;

import java.util.List;

import com.redbox.pkdm.entities.ShelfAuthor;
import com.redbox.pkdm.entities.ShelfAuthorMapper;
public interface ShelfAuthorMapperService {
	
	void save(ShelfAuthorMapper shelfAuthorMapper);
	
	List<ShelfAuthor> findByBook(String bookId);
	
	List<ShelfAuthorMapper> findMapperByBookId(String bookId);
	

}
