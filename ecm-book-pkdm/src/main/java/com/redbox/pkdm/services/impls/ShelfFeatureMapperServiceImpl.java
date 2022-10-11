package com.redbox.pkdm.services.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redbox.pkdm.entities.ShelfFeature;
import com.redbox.pkdm.entities.ShelfFeatureMapper;
import com.redbox.pkdm.repositories.ShelfFeatureMapperRepository;
import com.redbox.pkdm.services.BookService;
import com.redbox.pkdm.services.ShelfFeatureMapperService;
import com.redbox.pkdm.services.ShelfFeatureService;

@Service
public class ShelfFeatureMapperServiceImpl implements ShelfFeatureMapperService {
	
	@Autowired
	private ShelfFeatureMapperRepository repository;
	
	@Autowired
	private ShelfFeatureService shelfFeatureService;
	
	@Autowired
	private BookService bookService;

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

	@Override
	public void save(ShelfFeatureMapper shelfFeatureMapper, String featureid, String bookId) {
		shelfFeatureMapper.setShelfFeature(shelfFeatureService.findByID(Long.parseLong(featureid)));
		shelfFeatureMapper.setBook(bookService.findByID(bookId));
		repository.save(shelfFeatureMapper);
		
	}

	@Override
	public List<ShelfFeatureMapper> findByErase(boolean erase) {
		return repository.findByErase(erase);
	}

	@Override
	public List<ShelfFeatureMapper> findAll() {
		
		return repository.findAll();
	}

	@Override
	public void delete(ShelfFeatureMapper shelfFeatureMapper) {
		shelfFeatureMapper.setErase(true);
		repository.save(shelfFeatureMapper);
		
	}


	@Override
	public ShelfFeatureMapper findById(Long id) {
		return repository.findById(id).get();
	}


}
