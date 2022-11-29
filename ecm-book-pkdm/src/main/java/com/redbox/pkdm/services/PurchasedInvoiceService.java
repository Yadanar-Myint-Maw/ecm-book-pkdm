package com.redbox.pkdm.services;

import java.util.List;

import com.redbox.pkdm.entities.PurchasedInvoice;

public interface PurchasedInvoiceService {

	void save(PurchasedInvoice purchasedInvoice);
	
	void delete(PurchasedInvoice purchasedInvoice);
	
	PurchasedInvoice findByID(String id);
	
	List<PurchasedInvoice> findByUser(String userID);
	
}
