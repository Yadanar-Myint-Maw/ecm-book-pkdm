package com.redbox.pkdm.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.redbox.pkdm.entities.AccountUser;
import com.redbox.pkdm.entities.DiscountCoupon;

@Service
public interface DiscountCouponService {

	void save(DiscountCoupon discountCoupon);
	
	void delete(DiscountCoupon discountCoupon);
	
	DiscountCoupon findByID(Long id);
	
	List<DiscountCoupon> findAll();
	
	List<DiscountCoupon> findByErase(boolean erase);
	
	List<DiscountCoupon> findBySingleDate(LocalDate singleDate);
	
	List<DiscountCoupon> findByDateFromAndDateTo(LocalDate dateFrom, LocalDate dateTo);

	List<AccountUser> getDiscountCouponList(String id);
	
	long findCountByDiscountCoupon();
	
}
