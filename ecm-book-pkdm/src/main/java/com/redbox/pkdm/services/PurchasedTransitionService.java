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

	List<BookReportModel> findCountByBook();

	List<BookReportModel> findCountByBookAndDate(LocalDate dateFrom, LocalDate dateTo);

	List<UserReportModel> findCountByUser();

	List<UserReportModel> findCountByUserAndDate(LocalDate dateFrom, LocalDate dateTo);





}
