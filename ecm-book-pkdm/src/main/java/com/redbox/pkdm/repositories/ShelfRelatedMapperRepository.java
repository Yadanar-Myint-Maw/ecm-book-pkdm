package com.redbox.pkdm.repositories;

import java.util.List;

import com.redbox.pkdm.entities.ShelfRelated;
import com.redbox.pkdm.entities.ShelfRelatedMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ShelfRelatedMapperRepository extends JpaRepository<ShelfRelatedMapper, Long>  {
	
	@Query(value = "select s from ShelfRelatedMapper as s where s.erase = :erase")
	List<ShelfRelatedMapper> findByErase(@Param("erase") Boolean erase);
	
	@Query(value = "select s.shelfRelated from ShelfRelatedMapper as s where s.book.id = :bookId")
	List<ShelfRelated> findByBook(@Param("bookId") String bookId);
	
	@Query(value = "select s from ShelfRelatedMapper as s where s.book.id = :bookId")
	List<ShelfRelatedMapper> findByBookID(@Param("bookId") String bookId);
	
}
