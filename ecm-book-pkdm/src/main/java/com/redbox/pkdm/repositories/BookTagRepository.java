package com.redbox.pkdm.repositories;

import java.util.List;

import com.redbox.pkdm.entities.BookTag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookTagRepository extends JpaRepository<BookTag, Long> {
	
	@Query("select b from BookTag as b where b.erase = :erase")
	List<BookTag> findByErase (boolean erase); 
	
	//MyoSandiKyaw
	
	@Query("select b from BookTag as b where b.name = :name")
	List<BookTag> findByName(String name);
	
//	@Query("select b from BookTag as b where b.book.id = :bookId")
//	List<BookTag> findByBook(String bookId);

}
