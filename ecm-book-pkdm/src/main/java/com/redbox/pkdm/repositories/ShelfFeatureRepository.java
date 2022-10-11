package com.redbox.pkdm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.redbox.pkdm.entities.ShelfFeature;

public interface ShelfFeatureRepository  extends JpaRepository<ShelfFeature, Long> {
	
	@Query(value = "select s from ShelfFeature as s where s.erase = :erase ORDER BY s.id DESC")
	List<ShelfFeature> findByEraseAndOrderId(@Param("erase") Boolean erase);
	
	@Query(value = "select s from ShelfFeature as s where s.name LIKE %:name% and s.erase = false")
	List<ShelfFeature> findByNameLike(@Param("name") String name);
	
	@Query(value = "select s from ShelfFeature as s where s.name = :name and s.erase = false")
	ShelfFeature findByName(@Param("name") String name);
	
	
	

}
