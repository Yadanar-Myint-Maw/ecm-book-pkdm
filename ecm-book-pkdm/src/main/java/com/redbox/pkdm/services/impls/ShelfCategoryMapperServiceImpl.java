package com.redbox.pkdm.services.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redbox.pkdm.entities.Book;
import com.redbox.pkdm.entities.ShelfCategory;
import com.redbox.pkdm.entities.ShelfCategoryMapper;
import com.redbox.pkdm.repositories.ShelfCategoryMapperRepository;
import com.redbox.pkdm.services.ShelfCategoryMapperService;

@Service
public class ShelfCategoryMapperServiceImpl implements ShelfCategoryMapperService{
	
	@Autowired
	private ShelfCategoryMapperRepository repository;

	@Override
	public void save(ShelfCategoryMapper shelfCategoryMapper) {
		repository.save(shelfCategoryMapper);
	}

	@Override
	public List<ShelfCategory> findByBookId(String bookId) {
		return repository.findByBookId(bookId);
	}

	@Override
	public List<ShelfCategoryMapper> findMapperByBookId(String bookId) {
		return repository.findMapperByBookId(bookId);
	}

	

}
