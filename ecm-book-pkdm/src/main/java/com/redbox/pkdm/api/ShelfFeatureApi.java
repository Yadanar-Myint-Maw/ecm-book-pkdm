package com.redbox.pkdm.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.redbox.pkdm.entities.ShelfFeature;
import com.redbox.pkdm.models.ShelfFeatureModel;
import com.redbox.pkdm.services.ShelfFeatureService;

@RestController
@RequestMapping("api/shelffeature")
public class ShelfFeatureApi {
	
	@Autowired
	private ShelfFeatureService shelfFeatureService;
	
	
	@GetMapping("/findall")
	List<ShelfFeatureModel> getAllShelfCategories(){
		ShelfFeatureModel model = new ShelfFeatureModel();
		List<ShelfFeatureModel> models = new ArrayList<>();
		List<ShelfFeature> shelfFeatures = shelfFeatureService.findByEraseAndOrderId(false);
		for (ShelfFeature s : shelfFeatures) {
			model.setId(s.getId());
			model.setName(s.getName());
			models.add(model);
			model = new ShelfFeatureModel();
		} 
		return models;
	}

}
