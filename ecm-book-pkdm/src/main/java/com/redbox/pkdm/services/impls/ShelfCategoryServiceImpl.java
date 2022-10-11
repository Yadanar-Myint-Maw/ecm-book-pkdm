package com.redbox.pkdm.services.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.redbox.pkdm.entities.ShelfCategory;
import com.redbox.pkdm.repositories.ShelfCategoryRepository;
import com.redbox.pkdm.services.ShelfCategoryService;

@Service
public class ShelfCategoryServiceImpl implements ShelfCategoryService{
	

	@Autowired
	private ShelfCategoryRepository repository;

	@Override
	public void save(ShelfCategory shelfCategory) {
		repository.save(shelfCategory);			
	}

	@Override
	public ShelfCategory findByID(long id) {
		return repository.findById(id).get();
	}

	@Override
	public List<ShelfCategory> findByEraseAndOrderId(boolean erase) {
		return repository.findByEraseAndOrderId(false);
	}

	@Override
	public void delete(ShelfCategory shelfCategory) {
		shelfCategory.setErase(true);
		repository.save(shelfCategory);
	}

	@Override
	public List<ShelfCategory> findByNameLike(String name) {
		return repository.findByNameLike(name);
	}
	
	
	


}
