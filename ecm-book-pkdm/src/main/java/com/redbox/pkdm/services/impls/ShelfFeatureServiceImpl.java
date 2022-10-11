package com.redbox.pkdm.services.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redbox.pkdm.entities.ShelfFeature;
import com.redbox.pkdm.repositories.ShelfFeatureRepository;
import com.redbox.pkdm.services.ShelfFeatureService;

@Service
public class ShelfFeatureServiceImpl implements ShelfFeatureService{
	

	@Autowired
	private ShelfFeatureRepository repository;

	@Override
	public void save(ShelfFeature shelfFeature) {
		repository.save(shelfFeature);			
	}

	@Override
	public ShelfFeature findByID(long id) {
		return repository.findById(id).get();
	}

	@Override
	public List<ShelfFeature> findByEraseAndOrderId(boolean erase) {
		return repository.findByEraseAndOrderId(false);
	}

	@Override
	public void delete(ShelfFeature shelfFeature) {
		shelfFeature.setErase(true);
		repository.save(shelfFeature);
	}

	@Override
	public List<ShelfFeature> findByNameLike(String name) {
		return repository.findByNameLike(name);
	}

	@Override
	public ShelfFeature findByName(String name) {
		return repository.findByName(name);
	}

}
