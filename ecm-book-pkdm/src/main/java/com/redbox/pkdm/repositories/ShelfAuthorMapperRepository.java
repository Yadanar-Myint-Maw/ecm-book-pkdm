package com.redbox.pkdm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.redbox.pkdm.entities.ShelfAuthor;
import com.redbox.pkdm.entities.ShelfAuthorMapper;


public interface ShelfAuthorMapperRepository extends JpaRepository<ShelfAuthorMapper, Long>{

	@Query(value = "select s.shelfAuthor from ShelfAuthorMapper as s where s.book.id = :bookId")
	List<ShelfAuthor> findByBookId(@Param("bookId") String bookId);
	
	@Query(value = "select s from ShelfAuthorMapper as s where s.book.id = :bookId")
	List<ShelfAuthorMapper> findMapperByBookId(@Param("bookId") String bookId);
	
}
