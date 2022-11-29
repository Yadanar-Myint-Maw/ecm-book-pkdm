package com.redbox.pkdm.services;

import java.util.List;

import com.redbox.pkdm.entities.ShelfRelated;
import com.redbox.pkdm.entities.ShelfRelatedMapper;

public interface ShelfRelatedMapperService {
	
	void save(ShelfRelatedMapper shelfRelatedMapper);

	void delete(ShelfRelatedMapper shelfRelatedMapper);

	ShelfRelatedMapper findByID(long id);
	
	List<ShelfRelatedMapper> findByErase(boolean erase);
	
	List<ShelfRelated> findByBook(String bookID);
	
	List<ShelfRelatedMapper> findByBookID(String bookID);

}
