package com.redbox.pkdm.services.impls;

import java.util.List;

import com.redbox.pkdm.entities.ShelfRelated;
import com.redbox.pkdm.repositories.ShelfRelatedRepository;
import com.redbox.pkdm.services.ShelfRelatedService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShelfRelatedServiceImpl implements ShelfRelatedService {
	
	@Autowired
	private ShelfRelatedRepository shelfRelatedRepository;

	@Override
	public void save(ShelfRelated shelfRelated) {
		shelfRelatedRepository.save(shelfRelated);
	}

	@Override
	public void delete(ShelfRelated shelfFeature) {
		shelfFeature.setErase(true);
		shelfRelatedRepository.save(shelfFeature);
	}

	@Override
	public ShelfRelated findByID(long id) {
		return shelfRelatedRepository.findById(id).get();
	}

	@Override
	public List<ShelfRelated> findByErase(boolean erase) {
		return shelfRelatedRepository.findByErase(erase);
	}

	@Override
	public List<ShelfRelated> findByName(String name) {
		return shelfRelatedRepository.findByNameAndErase(name, false);
	}

}
