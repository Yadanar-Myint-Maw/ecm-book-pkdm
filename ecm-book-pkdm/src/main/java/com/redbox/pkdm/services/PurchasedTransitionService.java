package com.redbox.pkdm.services;

import java.time.LocalDate;
import java.util.List;

import com.redbox.pkdm.entities.PurchasedTransition;

import com.redbox.pkdm.models.BookReportModel;
import com.redbox.pkdm.models.UserReportModel;
import org.springframework.stereotype.Service;

@Service
public interface PurchasedTransitionService {
	
	void save(PurchasedTransition purchasedTransition);
	
	void delete(PurchasedTransition purchasedTransition);
	
	PurchasedTransition findByID(Long id);
	
	List<PurchasedTransition> findAll();

	List<PurchasedTransition> findByUser(String id);
	
	List<PurchasedTransition> findByBookType(String bookType);

	//MyoSandiKyaw
	
	List<PurchasedTransition> findByErase(boolean erase);
	
	List<PurchasedTransition> findByDateBetween(LocalDate dateFrom, LocalDate dateTo);
	
	List<BookReportModel> findCountByBook();

	List<BookReportModel> findCountByBookAndDate(LocalDate dateFrom, LocalDate dateTo);

	List<UserReportModel> findCountByUser();

	List<UserReportModel> findCountByUserAndDate(LocalDate dateFrom, LocalDate dateTo);
	
	List<PurchasedTransition> findByInvoiceNoWithGroupBy();
	
	List<PurchasedTransition> findByInvoiceNo(String invoiceNo);
	
	List<PurchasedTransition> findByInvoiceNoAndDateBetweenWithGroupBy(LocalDate dateFrom, LocalDate dateTo);
	
	List<PurchasedTransition> findByInvoiceNoAndDateWithGroupBy(LocalDate date);
	
	List<PurchasedTransition> findByBookTypeWithGroupByInvoiceNo(boolean bookType);
	
	List<PurchasedTransition> findByBookTypeAndDeliveryStatusWithGroupByInvoiceNo(boolean bookType, String deliveryStatus);
	
	List<PurchasedTransition> findByBookTypeAndDeliveryStatusAndDateBetweenWithGroupByInvoiceNo(boolean bookType, String deliveryStatus, LocalDate dateFrom, LocalDate dateTo);
	
	List<PurchasedTransition> findByBookTypeAndDeliveryStatusAndDateWithGroupByInvoiceNo(boolean bookType, String deliveryStatus, LocalDate date);
	
	List<PurchasedTransition> findByBookTypeAndDateBetweenWithGroupByInvoiceNo(boolean bookType, LocalDate dateFrom, LocalDate dateTo);
	
	List<PurchasedTransition> findByBookTypeAndDateWithGroupByInvoiceNo(boolean bookType, LocalDate date);


	
	
	


	
	





}
