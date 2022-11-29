package com.redbox.pkdm.services;

import java.util.List;

import com.redbox.pkdm.entities.ShelfRelated;

public interface ShelfRelatedService {
	
	void save(ShelfRelated shelfRelated);

	void delete(ShelfRelated shelfFeature);

	ShelfRelated findByID(long id);
	
	List<ShelfRelated> findByErase(boolean erase);
	
	List<ShelfRelated> findByName(String name);
	
}
