package com.redbox.pkdm.services.impls;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.redbox.pkdm.entities.DiscountCouponMapper;
import com.redbox.pkdm.models.DiscountCoupons;
import com.redbox.pkdm.repositories.DiscountCouponMapperRepository;
import com.redbox.pkdm.repositories.DiscountCouponRepository;
import com.redbox.pkdm.services.AccountUserService;
import com.redbox.pkdm.services.DiscountCouponMapperService;
import com.redbox.pkdm.services.DiscountCouponService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountCouponMapperServiceImpl implements DiscountCouponMapperService{
	
	@Autowired
	private DiscountCouponMapperRepository discountCouponMapperRepository;
	
	@Autowired
	private DiscountCouponRepository discountCouponRepository;
	
	@Autowired
	private AccountUserService accountUserService;
	
	@Autowired
	private DiscountCouponService discountCouponService;
	

	@Override
	public void delete(DiscountCouponMapper discountCouponMapper) {
		discountCouponMapper.setErase(true);
		discountCouponMapperRepository.save(discountCouponMapper);
	}

	@Override
	public DiscountCouponMapper findByID(Long id) {
		return discountCouponMapperRepository.findById(id).get();
	}

	@Override
	public List<DiscountCouponMapper> findAll() {
		return discountCouponMapperRepository.findAll();
	}

	@Override
	public List<DiscountCouponMapper> findByErase(boolean erase) {
		return discountCouponMapperRepository.findByErase(erase);
	}

	@Override
	public void save(DiscountCouponMapper discountCouponMapper, String userId, long disId) {
		discountCouponMapper.setAccountUser(accountUserService.findByID(userId));
		discountCouponMapper.setDiscountCoupon(discountCouponService.findByID(disId));
		discountCouponMapperRepository.save(discountCouponMapper);
	}

	@Override
	public List<DiscountCouponMapper> getDiscountCouponList(String id) {
		List<DiscountCoupons> discountCoupon = discountCouponMapperRepository.getDiscountCouponList(id).stream()
				.map(a -> {
					return new DiscountCoupons(a.getDiscountCoupon().getId(),
							a.getDiscountCoupon().getName(),
							a.getDiscountCoupon().getDescription(),
							a.getDiscountCoupon().getStartDate(),
							a.getDiscountCoupon().getEndDate());	
				}).collect(Collectors.toList());
		discountCoupon.forEach(a -> {
			a.getId();
			a.getName();
			a.getDescription();
			a.getStartDate();
			a.getEndDate();
		});
		return discountCouponMapperRepository.getDiscountCouponList(id);
	}
	
	@Override
	public List<DiscountCouponMapper> findByUserAndDate(String userID, LocalDate date) {
		return discountCouponMapperRepository.findByUserAndEndDate(userID, date);
	}

}
