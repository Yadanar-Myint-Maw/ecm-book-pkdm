package com.redbox.pkdm.api;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.redbox.pkdm.entities.DiscountCouponMapper;
import com.redbox.pkdm.models.CouponModel;
import com.redbox.pkdm.services.DiscountCouponMapperService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/coupon")
public class CouponAPI {
	
	@Autowired
	private DiscountCouponMapperService discountCouponMapperService;

	@GetMapping("/findbyuser/{userID}")
	public List<CouponModel> findByUser (@PathVariable String userID) {
		try {
			List<CouponModel> couponModels = new ArrayList<>();
			List<DiscountCouponMapper> discountCouponMappers = discountCouponMapperService.findByUserAndDate(userID, LocalDate.now());
			for (DiscountCouponMapper discountCouponMapper : discountCouponMappers) {
				CouponModel couponModel = new CouponModel();
				couponModel.setId(discountCouponMapper.getDiscountCoupon().getId());
				couponModel.setRate(discountCouponMapper.getDiscountCoupon().getRate());
				couponModel.setName(discountCouponMapper.getDiscountCoupon().getName());
				couponModel.setDescription(discountCouponMapper.getDiscountCoupon().getDescription());
				couponModels.add(couponModel);
			}
			return couponModels;
		} catch (Exception e) {
			
			return new ArrayList<>();
			
		}
	}
	
	
	
	
//	@GetMapping("/findbyshelffeature/{id}")
//	public List<BookModel> findByShelfFeature (@PathVariable long id) {
//		try {
//			MapperModel model = new MapperModel();
//			List<MapperModel> models = new ArrayList<>();
//			List<ShelfFeatureMapper> shelfFeatureMappers = shelfFeatureMapperService.findByShelfFeature(id);
//			for (ShelfFeatureMapper m : shelfFeatureMappers) {
//				model.setName("My Books");
//				model.setBookType(transition.getBookType());
//				model.setBookID(transition.getBook().getId());
//				model.setBookSectionID(new ArrayList<>());
//				model.getBookSectionID().add(transition.getSectionID());
//				models.add(model);
//				model = new MapperModel();
//			}
//			return bookModels;
//		} catch (Exception e) {
//			return new ArrayList<>();
//		}
//	}
	
//	@GetMapping("/findbypurchased/{id}")
//	public List<MapperModel> findByPurchased (@PathVariable String id) {
//		try {
//			MapperModel model = new MapperModel();
//			List<MapperModel> models = new ArrayList<>();
//			List<PurchasedTransition> purchasedTransitions = purchasedTransitionService.findByUser(id);
//			outer : for (PurchasedTransition transition : purchasedTransitions) {
//				if (models.size() != 0) {
//					for (int i = 0; i < models.size(); ++i) {
//						if (transition.getBook().getId().equals(models.get(i).getBookID())) {
//							models.get(i).getBookSectionID().add(transition.getSectionID());
//							continue outer;
//						}
//					}
//				}
//				model.setName("My Books");
//				model.setBookType(transition.getBookType());
//				model.setBookID(transition.getBook().getId());
//				model.setBookSectionID(new ArrayList<>());
//				model.getBookSectionID().add(transition.getSectionID());
//				models.add(model);
//				model = new MapperModel();
//			}
//			return models;
//		} catch (Exception e) {
//			return new ArrayList<>();
//		}
//	}
	
}
