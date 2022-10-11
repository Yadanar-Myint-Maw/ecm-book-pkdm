package com.redbox.pkdm.services.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redbox.pkdm.entities.ShelfAuthor;
import com.redbox.pkdm.entities.ShelfAuthorMapper;
import com.redbox.pkdm.repositories.ShelfAuthorMapperRepository;
import com.redbox.pkdm.services.ShelfAuthorMapperService;

@Service
public class ShelfAuthorMapperServiceImpl implements ShelfAuthorMapperService{
	
	@Autowired
	private ShelfAuthorMapperRepository repository;

	@Override
	public void save(ShelfAuthorMapper shelfAuthorMapper) {
		repository.save(shelfAuthorMapper);
	}

	@Override
	public List<ShelfAuthor> findByBookId(String bookId) {
		return repository.findByBookId(bookId);
	}

	@Override
	public List<ShelfAuthorMapper> findMapperByBookId(String bookId) {
		return repository.findMapperByBookId(bookId);
	}

}
