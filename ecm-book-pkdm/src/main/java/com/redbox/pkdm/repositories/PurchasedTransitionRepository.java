package com.redbox.pkdm.repositories;

import java.time.LocalDate;
import java.util.List;

import com.redbox.pkdm.entities.PurchasedTransition;

import com.redbox.pkdm.models.BookReportModel;
import com.redbox.pkdm.models.UserReportModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PurchasedTransitionRepository extends JpaRepository<PurchasedTransition, Long> {

	@Query(value = "select p from PurchasedTransition as p where p.accountUser.id = :id and p.erase = false")
	List<PurchasedTransition> findByUser(@Param("id") String id);
	
	@Query(value = "select p from PurchasedTransition as p where p.bookType = :bookType")
	List<PurchasedTransition> findByBookType(@Param("bookType") String bookType);

	//MyoSandiKyaw
	
	@Query(value = "select p from PurchasedTransition as p where p.erase = :erase")
	List<PurchasedTransition> findByErase(@Param("erase") boolean erase);
	
	@Query(value = "select p from PurchasedTransition as p where p.securityInfo.createDate between :dateFrom and :dateTo and p.erase = false")
	List<PurchasedTransition> findByDateBetween(@Param("dateFrom") LocalDate dateFrom, @Param("dateTo") LocalDate dateTo);
	
	@Query(value = "SELECT new BookReportModel(p.book, COUNT(p.book.id), p.bookPrice) FROM PurchasedTransition AS p GROUP BY p.book.id ORDER BY COUNT(p.book.id) DESC")
	List<BookReportModel> findCountByBook();

	@Query(value = "SELECT new BookReportModel(p.book, COUNT(p.book.id), p.bookPrice) FROM PurchasedTransition AS p where p.securityInfo.createDate between :dateFrom and :dateTo GROUP BY p.book.id ORDER BY COUNT(p.book.id) DESC")
	List<BookReportModel> findCountByBookAndDate(@Param("dateFrom") LocalDate dateFrom, @Param("dateTo") LocalDate dateTo);

	@Query(value = "SELECT new UserReportModel(p.accountUser, COUNT(p.accountUser.id)) FROM PurchasedTransition AS p GROUP BY p.accountUser.id ORDER BY COUNT(p.accountUser.id) DESC")
	List<UserReportModel> findCountByUser();

	@Query(value = "SELECT new UserReportModel(p.accountUser, COUNT(p.accountUser.id)) FROM PurchasedTransition AS p where p.securityInfo.createDate between :dateFrom and :dateTo GROUP BY p.accountUser.id ORDER BY COUNT(p.accountUser.id) DESC")
	List<UserReportModel> findCountByUserAndDate(@Param("dateFrom") LocalDate dateFrom, @Param("dateTo") LocalDate dateTo);
	
	@Query(value = "select p from PurchasedTransition AS p where p.erase = false GROUP BY p.invoice_no")
	List<PurchasedTransition> findByInvoiceNoWithGroupBy();
	
	@Query(value = "select p from PurchasedTransition AS p where p.erase = false and p.invoice_no = :invoiceNo")
	List<PurchasedTransition> findByInvoiceNo(@Param("invoiceNo") String invoiceNo);
	
	@Query(value = "select p from PurchasedTransition AS p where p.erase = false and p.securityInfo.createDate between :dateFrom and :dateTo GROUP BY p.invoice_no")
	List<PurchasedTransition> findByInvoiceNoAndDateBetweenWithGroupBy(@Param("dateFrom") LocalDate dateFrom, @Param("dateTo") LocalDate dateTo);
	
	@Query(value = "select p from PurchasedTransition AS p where p.erase = false and p.securityInfo.createDate = :date  GROUP BY p.invoice_no")
	List<PurchasedTransition> findByInvoiceNoAndDateWithGroupBy(@Param("date") LocalDate date);
	
	@Query(value = "select p from PurchasedTransition AS p where p.erase = false and p.bookType = :bookType GROUP BY p.invoice_no")
	List<PurchasedTransition> findByBookTypeWithGroupByInvoiceNo(@Param("bookType")boolean bookType);
	
	@Query(value = "select p from PurchasedTransition AS p where p.erase = false and p.bookType = :bookType and p.deliveryStatus = :deliveryStatus	 GROUP BY p.invoice_no")
	List<PurchasedTransition> findByBookTypeAndDeliveryStatusWithGroupByInvoiceNo(@Param("bookType")boolean bookType, @Param("deliveryStatus") String deliveryStatus);
	
	@Query(value = "select p from PurchasedTransition AS p where p.erase = false and p.bookType = :bookType and p.deliveryStatus = :deliveryStatus and p.securityInfo.createDate between :dateFrom and :dateTo GROUP BY p.invoice_no")
	List<PurchasedTransition> findByBookTypeAndDeliveryStatusAndDateBetweenWithGroupByInvoiceNo(@Param("bookType")boolean bookType, @Param("deliveryStatus") String deliveryStatus,  @Param("dateFrom") LocalDate dateFrom, @Param("dateTo") LocalDate dateTo);
	
	@Query(value = "select p from PurchasedTransition AS p where p.erase = false and p.bookType = :bookType and p.deliveryStatus = :deliveryStatus and p.securityInfo.createDate = :date GROUP BY p.invoice_no")
	List<PurchasedTransition> findByBookTypeAndDeliveryStatusAndDateWithGroupByInvoiceNo(@Param("bookType")boolean bookType, @Param("deliveryStatus") String deliveryStatus,  @Param("date") LocalDate date);
	
	@Query(value = "select p from PurchasedTransition AS p where p.erase = false and p.bookType = :bookType and p.securityInfo.createDate between :dateFrom and :dateTo	GROUP BY p.invoice_no")
	List<PurchasedTransition> findByBookTypeAndDateBetweenWithGroupByInvoiceNo(@Param("bookType")boolean bookType, @Param("dateFrom") LocalDate dateFrom, @Param("dateTo") LocalDate dateTo);
	
	@Query(value = "select p from PurchasedTransition AS p where p.erase = false and p.bookType = :bookType and p.securityInfo.createDate = :date GROUP BY p.invoice_no")
	List<PurchasedTransition> findByBookTypeAndDateWithGroupByInvoiceNo(@Param("bookType")boolean bookType, @Param("date") LocalDate date);
	
	
	
	
	
	
	
	
	

	
	






}
