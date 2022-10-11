package com.redbox.pkdm.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.redbox.pkdm.entities.AccountUser;
import com.redbox.pkdm.entities.DiscountCoupon;

public interface DiscountCouponRepository extends JpaRepository<DiscountCoupon, Long>{

	@Query(value = "select d from DiscountCoupon as d where d.erase = :erase ORDER BY d.id DESC")
	List<DiscountCoupon> findByErase(boolean erase);
	
	@Query(value = "select d from DiscountCoupon as d where :singleDate between d.startDate and d.endDate")
	List<DiscountCoupon> findBySingleDate(LocalDate singleDate);
	
	@Query(value = "select d from DiscountCoupon as d where d.startDate between :dateFrom and :dateTo and d.endDate between :dateFrom and :dateTo")
	List<DiscountCoupon> findByDateFromAndDateTo(LocalDate dateFrom, LocalDate dateTo);

	@Query(value = "select u from AccountUser as u where u.id = :id")
	List<AccountUser> getDiscountCouponList(@Param("id")String id);	

}
