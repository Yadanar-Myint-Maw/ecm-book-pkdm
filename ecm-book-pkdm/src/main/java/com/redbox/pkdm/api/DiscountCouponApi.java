package com.redbox.pkdm.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.redbox.pkdm.entities.AccountUser;
import com.redbox.pkdm.entities.DiscountCouponMapper;
import com.redbox.pkdm.services.AccountUserService;
import com.redbox.pkdm.services.DiscountCouponMapperService;
import com.redbox.pkdm.services.DiscountCouponService;

@RestController
@RequestMapping("admin/discountcoupon/api")
public class DiscountCouponApi {
	
	@Autowired
	private DiscountCouponMapperService discountCouponMapperService;
			
	@GetMapping("/discountcouponlist")
	public List<DiscountCouponMapper> getDiscountCouponList(@RequestParam String id){
		return discountCouponMapperService.getDiscountCouponList(id);
	}

}
