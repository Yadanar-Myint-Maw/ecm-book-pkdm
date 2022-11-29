package com.redbox.pkdm.repositories;

import java.util.List;

import com.redbox.pkdm.entities.PurchasedInvoice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PurchasedInvoiceRepository extends JpaRepository<PurchasedInvoice, String> {
	
	@Query("select p from PurchasedInvoice as p where p.accountUser.id = :userID")
	List<PurchasedInvoice> findByUser (String userID);
	
	

}
