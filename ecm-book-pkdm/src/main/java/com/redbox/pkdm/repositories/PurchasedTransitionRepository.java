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

//	@Query(value = "select p from PurchasedTransition as p where p.invoice_no = :invoice_no and p.erase = false")
//	List<PurchasedTransition> findByInvoiceNo(@Param("invoice_no") String invoiceNo);

//	@Query(value = "select count(p) from PurchasedTransition as p where p.book.id = :bookId")
//	long findCountByBook(@Param("bookId") String bookId);

	@Query(value = "SELECT new BookReportModel(p.book, COUNT(p.book.id), p.bookPrice) FROM PurchasedTransition AS p GROUP BY p.book.id ORDER BY COUNT(p.book.id) DESC")
	List<BookReportModel> findCountByBook();


	@Query(value = "SELECT new BookReportModel(p.book, COUNT(p.book.id), p.bookPrice) FROM PurchasedTransition AS p where p.securityInfo.createDate between :dateFrom and :dateTo GROUP BY p.book.id ORDER BY COUNT(p.book.id) DESC")
	List<BookReportModel> findCountByBookAndDate(@Param("dateFrom") LocalDate dateFrom, @Param("dateTo") LocalDate dateTo);

	@Query(value = "SELECT new UserReportModel(p.accountUser, COUNT(p.accountUser.id)) FROM PurchasedTransition AS p GROUP BY p.accountUser.id ORDER BY COUNT(p.accountUser.id) DESC")
	List<UserReportModel> findCountByUser();


	@Query(value = "SELECT new UserReportModel(p.accountUser, COUNT(p.accountUser.id)) FROM PurchasedTransition AS p where p.securityInfo.createDate between :dateFrom and :dateTo GROUP BY p.accountUser.id ORDER BY COUNT(p.accountUser.id) DESC")
	List<UserReportModel> findCountByUserAndDate(@Param("dateFrom") LocalDate dateFrom, @Param("dateTo") LocalDate dateTo);







}
