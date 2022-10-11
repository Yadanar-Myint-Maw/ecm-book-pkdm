package com.redbox.pkdm.repositories;

import java.util.List;

import com.redbox.pkdm.entities.Book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, String>{
	
	@Query(value = "select max(b.id) from Book as b")
	String findByLast();

	@Query(value = "select b from Book as b where b.erase = :erase")
	List<Book> findByErase(@Param("erase") Boolean erase);
	
	@Query(value = "select b from Book as b where b.name LIKE %:name% and b.bookType = :bookType and b.erase = false")
	List<Book> findByNameLikeAndBookType(@Param("name") String name, @Param("bookType") String bookType);
	
	@Query(value = "select b from Book as b where b.bookType = :bookType and b.erase = false")
	List<Book> findbyBookType(@Param("bookType") String bookType);
	
	@Query(value = "select b from Book as b where b.electronicBook = true and b.erase = false")
	List<Book> findbyEBook();
	
	@Query(value = "select b from Book as b where b.physicalBook = true and b.erase = false")
	List<Book> findbyPBook();
	
	@Query(value = "select count(b) from Book as b where b.bookType = :bookType and b.erase = false")
	long findByCountEBook(@Param("bookType")String bookType);
	
	@Query(value = "select count(b) from Book as b where b.bookType = :bookType and b.erase = false")
	long findByCountPBook(@Param("bookType")String bookType);
	
}
