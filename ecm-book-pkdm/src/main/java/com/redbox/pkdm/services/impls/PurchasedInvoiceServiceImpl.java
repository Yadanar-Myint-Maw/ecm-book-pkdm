package com.redbox.pkdm.services.impls;

import java.util.List;

import com.redbox.pkdm.entities.PurchasedInvoice;
import com.redbox.pkdm.repositories.PurchasedInvoiceRepository;
import com.redbox.pkdm.services.PurchasedInvoiceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchasedInvoiceServiceImpl implements PurchasedInvoiceService {
	
	@Autowired
	private PurchasedInvoiceRepository purchasedInvoiceRepository;
	
	@Override
	public void save(PurchasedInvoice purchasedInvoice) {
		purchasedInvoiceRepository.save(purchasedInvoice);
	}
	
	@Override
	public void delete(PurchasedInvoice purchasedInvoice) {
		purchasedInvoice.setErase(true);
		purchasedInvoiceRepository.save(purchasedInvoice);
	}
	
	@Override
	public PurchasedInvoice findByID(String id) {
		return purchasedInvoiceRepository.findById(id).get();
	}

	@Override
	public List<PurchasedInvoice> findByUser(String userID) {
		return purchasedInvoiceRepository.findByUser(userID);
	}

}
