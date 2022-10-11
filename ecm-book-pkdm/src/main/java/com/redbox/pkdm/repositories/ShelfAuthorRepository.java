package com.redbox.pkdm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.redbox.pkdm.entities.ShelfAuthor;

public interface ShelfAuthorRepository extends JpaRepository<ShelfAuthor, Long>{
	
	@Query(value = "select s from ShelfAuthor as s where s.erase = :erase ORDER BY s.id DESC")
	List<ShelfAuthor> findByEraseAndOrderId(@Param("erase") Boolean erase);
	
	@Query(value = "select s from ShelfAuthor as s where s.name LIKE %:name% and s.erase = false")
	List<ShelfAuthor> findByNameLike(@Param("name") String name);
	
	@Query(value = "select count(s) from ShelfAuthor as s where s.erase = false")
	long findCountByShelfAuthor();
	
}
