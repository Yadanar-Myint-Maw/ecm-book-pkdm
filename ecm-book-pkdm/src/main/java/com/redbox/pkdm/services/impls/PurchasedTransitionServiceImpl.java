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
	
	//MyoSandiKyaw
	
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

	@Override
	public List<PurchasedTransition> findByErase(boolean erase) {
		return repository.findByErase(erase);
	}

	@Override
	public List<PurchasedTransition> findByDateBetween(LocalDate dateFrom, LocalDate dateTo) {
		return repository.findByDateBetween(dateFrom, dateTo);
	}

	@Override
	public List<PurchasedTransition> findByInvoiceNoWithGroupBy() {
		return repository.findByInvoiceNoWithGroupBy();
	}

	@Override
	public List<PurchasedTransition> findByInvoiceNo(String invoiceNo) {
		return repository.findByInvoiceNo(invoiceNo);
	}

	@Override
	public List<PurchasedTransition> findByInvoiceNoAndDateBetweenWithGroupBy(LocalDate dateFrom, LocalDate dateTo) {
		return repository.findByInvoiceNoAndDateBetweenWithGroupBy(dateFrom, dateTo);
	}

	@Override
	public List<PurchasedTransition> findByInvoiceNoAndDateWithGroupBy(LocalDate date) {
		return repository.findByInvoiceNoAndDateWithGroupBy(date);
	}

	@Override
	public List<PurchasedTransition> findByBookTypeWithGroupByInvoiceNo(boolean bookType) {
		return repository.findByBookTypeWithGroupByInvoiceNo(bookType);
	}

	@Override
	public List<PurchasedTransition> findByBookTypeAndDeliveryStatusWithGroupByInvoiceNo(boolean bookType,
			String deliveryStatus) {
		return repository.findByBookTypeAndDeliveryStatusWithGroupByInvoiceNo(bookType, deliveryStatus);
	}
	
	@Override
	public List<PurchasedTransition> findByBookTypeAndDateBetweenWithGroupByInvoiceNo(boolean bookType,LocalDate dateFrom, LocalDate dateTo) {
		return repository.findByBookTypeAndDateBetweenWithGroupByInvoiceNo(bookType, dateFrom, dateTo);
	}

	@Override
	public List<PurchasedTransition> findByBookTypeAndDeliveryStatusAndDateBetweenWithGroupByInvoiceNo(boolean bookType,
			String deliveryStatus, LocalDate dateFrom, LocalDate dateTo) {
		return repository.findByBookTypeAndDeliveryStatusAndDateBetweenWithGroupByInvoiceNo(bookType, deliveryStatus, dateFrom, dateTo);
	}

	@Override
	public List<PurchasedTransition> findByBookTypeAndDeliveryStatusAndDateWithGroupByInvoiceNo(boolean bookType,
			String deliveryStatus, LocalDate date) {
		return repository.findByBookTypeAndDeliveryStatusAndDateWithGroupByInvoiceNo(bookType, deliveryStatus, date);
	}

	@Override
	public List<PurchasedTransition> findByBookTypeAndDateWithGroupByInvoiceNo(boolean bookType, LocalDate date) {
		return repository.findByBookTypeAndDateWithGroupByInvoiceNo(bookType, date);
	}
	
	
	
	
	
	
	
	
	

	
}
