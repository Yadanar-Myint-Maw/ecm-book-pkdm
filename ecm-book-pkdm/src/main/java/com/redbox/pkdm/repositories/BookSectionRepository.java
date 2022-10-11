package com.redbox.pkdm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.redbox.pkdm.entities.Book;
import com.redbox.pkdm.entities.BookSection;

public interface BookSectionRepository extends JpaRepository<BookSection, Long>{
	
	@Query(value = "select b from BookSection as b where b.erase = :erase")
	List<BookSection> findByErase(@Param("erase") Boolean erase);
	
	@Query(value = "select b from BookSection as b where b.book.id = :bookId and b.erase = :erase")
	List<BookSection> findByBookId(@Param("bookId") String bookId, @Param("erase") boolean erase);
	
	@Query(value = "select b.book from BookSection as b where b.erase = :erase")
	List<Book> findBooks(@Param("erase") boolean erase);
	
	@Query(value = "select b from BookSection as b GROUP BY b.book.id")
	List<BookSection> findBookSectionsByBookId();
	
	@Query(value = "select b from BookSection as b where b.book.bookType = :bookType and b.erase = false")
	List<BookSection> findByBookType(@Param("bookType") String bookType);	

}
