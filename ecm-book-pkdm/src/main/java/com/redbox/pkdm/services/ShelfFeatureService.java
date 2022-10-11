package com.redbox.pkdm.services;

import java.util.List;

import com.redbox.pkdm.entities.ShelfFeature;

public interface ShelfFeatureService {
	
	void save(ShelfFeature shelfFeature);

	void delete(ShelfFeature shelfFeature);

	ShelfFeature findByID(long id);
			
	List<ShelfFeature> findByEraseAndOrderId(boolean erase);	
	
	List<ShelfFeature> findByErase(boolean erase);
	
	List<ShelfFeature> findByNameLike(String name);	
	
	ShelfFeature findByName(String name);

	long findCountByShelfFeature();
}
