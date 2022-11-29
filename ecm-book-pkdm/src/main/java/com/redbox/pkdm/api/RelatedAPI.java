package com.redbox.pkdm.api;

import java.util.ArrayList;
import java.util.List;

import com.redbox.pkdm.entities.ShelfFeature;
import com.redbox.pkdm.entities.ShelfFeatureMapper;
import com.redbox.pkdm.models.ShelfModel;
import com.redbox.pkdm.services.ShelfFeatureMapperService;
import com.redbox.pkdm.services.ShelfFeatureService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/related")
public class RelatedAPI {
	
	@Autowired
	private ShelfFeatureService shelfFeatureService;
	
	@Autowired
	private ShelfFeatureMapperService shelfFeatureMapperService;
	
	

	@GetMapping("/findbybook")
	public List<ShelfModel> findAll () {
		try {
			
			List<ShelfModel> shelfModels = new ArrayList<>();
			List<ShelfFeature> shelfFeatures = shelfFeatureService.findByErase(false);
			List<ShelfFeatureMapper> shelfFeatureMappers = shelfFeatureMapperService.findAll();
			
			for (ShelfFeature shelfFeature : shelfFeatures) {
				
				List<String> books = new ArrayList<>();
				
				for (ShelfFeatureMapper shelfFeatureMapper : shelfFeatureMappers) {
					
					if (shelfFeature.getId() == shelfFeatureMapper.getShelfFeature().getId()) {
						
						books.add(shelfFeatureMapper.getBook().getId());
						
					}
					
				}
				
				shelfModels.add(new ShelfModel(shelfFeature.getName(), books));
				
			}
			
			return shelfModels;
			
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
