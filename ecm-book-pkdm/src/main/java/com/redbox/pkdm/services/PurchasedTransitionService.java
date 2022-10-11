package com.redbox.pkdm.services;

import java.util.List;

import com.redbox.pkdm.entities.PurchasedTransition;

import org.springframework.stereotype.Service;

@Service
public interface PurchasedTransitionService {
	
	void save(PurchasedTransition purchasedTransition);
	
	void delete(PurchasedTransition purchasedTransition);
	
	PurchasedTransition findByID(Long id);
	
	List<PurchasedTransition> findAll();

	List<PurchasedTransition> findByUser(String id);
	
	List<PurchasedTransition> findByBookType(String bookType);
	
}
