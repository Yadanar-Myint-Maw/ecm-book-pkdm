package com.redbox.pkdm.services.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redbox.pkdm.entities.ShelfFeature;
import com.redbox.pkdm.entities.ShelfFeatureMapper;
import com.redbox.pkdm.repositories.ShelfFeatureMapperRepository;
import com.redbox.pkdm.services.ShelfFeatureMapperService;

@Service
public class ShelfFeatureMapperServiceImpl implements ShelfFeatureMapperService {
	
	@Autowired
	private ShelfFeatureMapperRepository repository;

	@Override
	public void save(ShelfFeatureMapper shelfFeatureMapper) {
		repository.save(shelfFeatureMapper);
	}

	@Override
	public List<ShelfFeature> findByBookId(String bookId) {
		return repository.findByBookId(bookId);
	}

	@Override
	public List<ShelfFeatureMapper> findMapperByBookId(String bookId) {
		return repository.findMapperByBookId(bookId);
	}

	@Override
	public List<ShelfFeatureMapper> findByShelfFeature(long id) {
		return repository.findByShelfFeature(id);
	}
	
	
	
	

}
