package com.redbox.pkdm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.redbox.pkdm.entities.AccountUser;
import com.redbox.pkdm.entities.DiscountCoupon;
import com.redbox.pkdm.entities.DiscountCouponMapper;

public interface DiscountCouponMapperRepository extends JpaRepository<DiscountCouponMapper, Long>{

	@Query(value = "select m from DiscountCouponMapper as m where m.erase = :erase ORDER BY m.id DESC")
	List<DiscountCouponMapper> findByErase(@Param("erase")boolean erase);

	@Query(value = "select u from DiscountCouponMapper u join u.discountCoupon dc join u.accountUser ac where ac.id = ?1")
	List<DiscountCouponMapper> getDiscountCouponList(String id);
}
