package com.redbox.pkdm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.redbox.pkdm.entities.ShelfCategory;
import com.redbox.pkdm.entities.ShelfCategoryMapper;

public interface ShelfCategoryMapperRepository extends JpaRepository<ShelfCategoryMapper, Long>{
	
	@Query(value = "select s.shelfCategory from ShelfCategoryMapper as s where s.book.id = :bookId")
	List<ShelfCategory> findByBookId(@Param("bookId") String bookId);
	
	@Query(value = "select s from ShelfCategoryMapper as s where s.book.id = :bookId")
	List<ShelfCategoryMapper> findMapperByBookId(@Param("bookId") String bookId);
	
	
	

}
