package com.redbox.pkdm.repositories;

import java.util.List;

import com.redbox.pkdm.entities.PurchasedTransition;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PurchasedTransitionRepository extends JpaRepository<PurchasedTransition, Long> {

	
	@Query(value = "select p from PurchasedTransition as p where p.accountUser.id = :id and p.erase = false")
	List<PurchasedTransition> findByUser(@Param("id") String id);
	
	@Query(value = "select p from PurchasedTransition as p where p.bookType = :bookType")
	List<PurchasedTransition> findByBookType(@Param("bookType") String bookType);
	
}
