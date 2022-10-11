package com.redbox.pkdm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.redbox.pkdm.entities.Book;
import com.redbox.pkdm.entities.BookSection;
import com.redbox.pkdm.entities.BookSectionDetail;

public interface BookSectionDetailRepository extends JpaRepository<BookSectionDetail, Long>{
	
	@Query(value = "select b from BookSectionDetail as b where b.erase = :erase")
	List<BookSectionDetail> findByErase(@Param("erase") Boolean erase);
	
	@Query(value = "select b.bookSection.book from BookSectionDetail as b where b.erase = :erase")
	List<Book> findBooks(@Param("erase") boolean erase);
	
	@Query(value = "select b.bookSection from BookSectionDetail as b where b.erase = :erase and b.bookSection.book.bookType = :bookType")
	List<BookSection> findBookSectionsByBookType(@Param("erase") boolean erase, @Param("bookType") String bookType);
	
	@Query(value = "select b from BookSectionDetail as b where b.erase = :erase and b.bookSection.book.id = :bookId")
	List<BookSectionDetail> findByBookId(@Param("erase") boolean erase, @Param("bookId") String bookId);
	
	@Query(value = "select b from BookSectionDetail as b where b.erase = :erase and b.bookSection.id = :bookSectionId")
	List<BookSectionDetail> findByBookSectionId(@Param("erase") boolean erase, @Param("bookSectionId") long bookSectionId);
	
	@Query(value = "select b from BookSectionDetail as b where b.erase = :erase and b.bookSection.id = :bookSectionId and b.bookSection.book.id = :bookId")
	List<BookSectionDetail> findByBookIdAndBookSectionId(@Param("erase") boolean erase, @Param("bookId") String bookId, @Param("bookSectionId") long bookSectionId);


	
	
	
	

}
