package com.redbox.pkdm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.redbox.pkdm.entities.ShelfCategory;

public interface ShelfCategoryRepository extends JpaRepository<ShelfCategory, Long>{
	
	@Query(value = "select s from ShelfCategory as s where s.erase = :erase ORDER BY s.id DESC")
	List<ShelfCategory> findByEraseAndOrderId(@Param("erase") Boolean erase);
	
	@Query(value = "select s from ShelfCategory as s where s.name LIKE %:name% and s.erase = false")
	List<ShelfCategory> findByNameLike(@Param("name") String name);
	
	

}
