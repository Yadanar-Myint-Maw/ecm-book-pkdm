package com.redbox.pkdm.services.impls;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redbox.pkdm.entities.AccountUser;
import com.redbox.pkdm.entities.DiscountCoupon;
import com.redbox.pkdm.repositories.DiscountCouponRepository;
import com.redbox.pkdm.services.DiscountCouponService;

@Service
public class DiscountCouponServiceImpl implements DiscountCouponService{

	@Autowired
	private DiscountCouponRepository repository;
	
	@Override
	public void save(DiscountCoupon discountCoupon) {
		repository.save(discountCoupon);
	}

	@Override
	public void delete(DiscountCoupon discountCoupon) {
		discountCoupon.setErase(true);
		repository.save(discountCoupon);
	}

	@Override
	public DiscountCoupon findByID(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public List<DiscountCoupon> findAll() {
		return repository.findAll();
	}

	@Override
	public List<DiscountCoupon> findByErase(boolean erase) {
		return repository.findByErase(erase);
	}

	@Override
	public List<DiscountCoupon> findBySingleDate(LocalDate singleDate) {
		return repository.findBySingleDate(singleDate);
	}

	@Override
	public List<DiscountCoupon> findByDateFromAndDateTo(LocalDate dateFrom, LocalDate dateTo) {
		return repository.findByDateFromAndDateTo(dateFrom, dateTo);
	}

	@Override
	public List<AccountUser> getDiscountCouponList(String id) {
		return repository.getDiscountCouponList(id);
	}

	@Override
	public long findCountByDiscountCoupon() {
		return repository.findCountByDiscountCoupon();
	}
}
