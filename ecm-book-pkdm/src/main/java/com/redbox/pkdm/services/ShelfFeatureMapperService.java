package com.redbox.pkdm.services;

import java.util.List;

import com.redbox.pkdm.entities.ShelfFeature;
import com.redbox.pkdm.entities.ShelfFeatureMapper;

public interface ShelfFeatureMapperService {
	
	void save(ShelfFeatureMapper shelfFeatureMapper);
	
	void save(ShelfFeatureMapper shelfFeatureMapper, String featureid, String bookId);
	
	void delete(ShelfFeatureMapper shelfFeatureMapper);
	
	List<ShelfFeature> findByBookId(String bookId);
	
	List<ShelfFeatureMapper> findMapperByBookId(String bookId);
	
	List<ShelfFeatureMapper> findByShelfFeature(long id);
	
	List<ShelfFeatureMapper> findByErase(boolean erase);
	
	List<ShelfFeatureMapper> findAll();
	
	ShelfFeatureMapper findById(Long id);
	
	

}
