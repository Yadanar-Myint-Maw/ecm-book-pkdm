package com.redbox.pkdm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.redbox.pkdm.entities.DiscountCouponMapper;
import com.redbox.pkdm.entities.ShelfFeature;
import com.redbox.pkdm.entities.ShelfFeatureMapper;

public interface ShelfFeatureMapperRepository extends JpaRepository<ShelfFeatureMapper, Long>{
	
	@Query(value = "select s.shelfFeature from ShelfFeatureMapper as s where s.book.id = :bookId")
	List<ShelfFeature> findByBookId(@Param("bookId") String bookId);
	
	@Query(value = "select s from ShelfFeatureMapper as s where s.book.id = :bookId")
	List<ShelfFeatureMapper> findMapperByBookId(@Param("bookId") String bookId);
	
	@Query(value = "select s from ShelfFeatureMapper as s where s.shelfFeature.id = :id")
	List<ShelfFeatureMapper> findByShelfFeature(@Param("id") long id);
	
	@Query(value = "select s from ShelfFeatureMapper as s where s.erase = :erase ORDER BY s.id DESC")
	List<ShelfFeatureMapper> findByErase(@Param("erase")boolean erase);
}
