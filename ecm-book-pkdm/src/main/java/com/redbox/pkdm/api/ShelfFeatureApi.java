package com.redbox.pkdm.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.redbox.pkdm.entities.ShelfFeature;
import com.redbox.pkdm.entities.ShelfFeatureMapper;
import com.redbox.pkdm.models.ShelfFeatureModel;
import com.redbox.pkdm.services.ShelfFeatureMapperService;
import com.redbox.pkdm.services.ShelfFeatureService;

@RestController
@RequestMapping("api/shelffeature")
public class ShelfFeatureApi {
	
	@Autowired
	private ShelfFeatureService shelfFeatureService;
	
	@Autowired
	private ShelfFeatureMapperService shelfFeatureMapperService;
	
	@GetMapping("/findall")
	List<ShelfFeatureModel> getAllShelfCategories () {
		ShelfFeatureModel model = new ShelfFeatureModel();
		List<ShelfFeatureModel> models = new ArrayList<>();
		List<ShelfFeature> shelfFeatures = shelfFeatureService.findByErase(false);
		List<ShelfFeatureMapper> shelfFeatureMappers = shelfFeatureMapperService.findAll(); 
		for (ShelfFeature s : shelfFeatures) {
			model.setId(s.getId());
			model.setName(s.getName());
			models.add(model);
			model = new ShelfFeatureModel();
		}
		for (int i = 0; i < models.size(); ++i) {
			for (ShelfFeatureMapper m : shelfFeatureMappers) {
				if (models.get(i).getId() == m.getShelfFeature().getId()) {
					models.get(i).getBooks().add(m.getBook().getId());
				}
			}
		}
		return models;
	}

}
