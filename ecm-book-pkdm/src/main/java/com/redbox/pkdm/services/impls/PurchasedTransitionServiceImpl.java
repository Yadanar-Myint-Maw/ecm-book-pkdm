package com.redbox.pkdm.services.impls;

import java.time.LocalDate;
import java.util.List;

import com.redbox.pkdm.entities.PurchasedTransition;
import com.redbox.pkdm.models.BookReportModel;
import com.redbox.pkdm.models.UserReportModel;
import com.redbox.pkdm.repositories.PurchasedTransitionRepository;
import com.redbox.pkdm.services.PurchasedTransitionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchasedTransitionServiceImpl implements PurchasedTransitionService {

	@Autowired
	private PurchasedTransitionRepository repository;
	
	@Override
	public void save(PurchasedTransition purchasedTransition) {
		repository.save(purchasedTransition);
	}

	@Override
	public void delete(PurchasedTransition purchasedTransition) {
		repository.delete(purchasedTransition);
	}

	@Override
	public PurchasedTransition findByID(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public List<PurchasedTransition> findAll() {
		return repository.findAll();
	}

	@Override
	public List<PurchasedTransition> findByUser(String id) {
		return repository.findByUser(id);
	}

	@Override
	public List<PurchasedTransition> findByBookType(String bookType) {
		return repository.findByBookType(bookType);
	}

//	@Override
//	public List<PurchasedTransition> findByInvoiceNo(String invoiceNo) {
//		return repository.findByInvoiceNo(invoiceNo);
//	}

	@Override
	public List<BookReportModel> findCountByBook() {
		return repository.findCountByBook();
	}

	@Override
	public List<BookReportModel> findCountByBookAndDate(LocalDate dateFrom, LocalDate dateTo) {
		return repository.findCountByBookAndDate(dateFrom, dateTo);
	}

	@Override
	public List<UserReportModel> findCountByUser() {
		return repository.findCountByUser();
	}

	@Override
	public List<UserReportModel> findCountByUserAndDate(LocalDate dateFrom, LocalDate dateTo) {
		return repository.findCountByUserAndDate(dateFrom, dateTo);
	}
}
