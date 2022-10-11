package com.redbox.pkdm.services.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redbox.pkdm.entities.ShelfAuthor;
import com.redbox.pkdm.repositories.ShelfAuthorRepository;
import com.redbox.pkdm.services.ShelfAuthorService;

@Service
public class ShelfAuthorServiceImpl implements ShelfAuthorService{
	
	@Autowired
	private ShelfAuthorRepository repository;

	@Override
	public void save(ShelfAuthor shelfAuthor) {
		repository.save(shelfAuthor);			
	}

	@Override
	public ShelfAuthor findByID(long id) {
		return repository.findById(id).get();
	}

	@Override
	public List<ShelfAuthor> findByEraseAndOrderId(boolean erase) {
		return repository.findByEraseAndOrderId(false);
	}

	@Override
	public void delete(ShelfAuthor shelfAuthor) {
		shelfAuthor.setErase(true);
		repository.save(shelfAuthor);
	}

	@Override
	public List<ShelfAuthor> findByNameLike(String name) {
		return repository.findByNameLike(name);
	}

	@Override
	public long findCountByShelfAuthor() {
		return repository.findCountByShelfAuthor();
	}
	
	
}
