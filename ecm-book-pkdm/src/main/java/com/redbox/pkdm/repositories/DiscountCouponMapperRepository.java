package com.redbox.pkdm.repositories;

import java.time.LocalDate;
import java.util.List;

import com.redbox.pkdm.entities.DiscountCouponMapper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DiscountCouponMapperRepository extends JpaRepository<DiscountCouponMapper, Long>{

	@Query(value = "select m from DiscountCouponMapper as m where m.erase = :erase ORDER BY m.id DESC")
	List<DiscountCouponMapper> findByErase(@Param("erase")boolean erase);

	@Query(value = "select u from DiscountCouponMapper u join u.discountCoupon dc join u.accountUser ac where ac.id = ?1")
	List<DiscountCouponMapper> getDiscountCouponList(String id);
	
	@Query(value = "select d from DiscountCouponMapper as d where d.erase = false and d.accountUser.id = :userID and :date between d.discountCoupon.startDate and d.discountCoupon.endDate")
	List<DiscountCouponMapper> findByUserAndEndDate(String userID, LocalDate date);
}
