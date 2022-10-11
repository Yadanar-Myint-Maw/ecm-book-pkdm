package com.redbox.pkdm.services;

import java.util.List;
import com.redbox.pkdm.entities.ShelfAuthor;

public interface ShelfAuthorService {
	
	void save(ShelfAuthor shelfAuthor);

	void delete(ShelfAuthor shelfAuthor);

	ShelfAuthor findByID(long id);
			
	List<ShelfAuthor> findByEraseAndOrderId(boolean erase);	
	
	List<ShelfAuthor> findByNameLike(String name);	

	long findCountByShelfAuthor();
	
	
	
	

}
