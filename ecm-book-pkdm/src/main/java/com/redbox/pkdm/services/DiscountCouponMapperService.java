package com.redbox.pkdm.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.redbox.pkdm.entities.AccountUser;
import com.redbox.pkdm.entities.DiscountCouponMapper;

@Service
public interface DiscountCouponMapperService {
	
	void save(DiscountCouponMapper discountCouponMapper, String userId, long disId);
	
	void delete(DiscountCouponMapper discountCouponMapper);
	
	DiscountCouponMapper findByID(Long id);
	
	List<DiscountCouponMapper> findAll();
	
	List<DiscountCouponMapper> findByErase(boolean erase);

	List<DiscountCouponMapper> getDiscountCouponList(String id);

}
