package com.redbox.pkdm.services.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redbox.pkdm.entities.Book;
import com.redbox.pkdm.entities.ShelfCategory;
import com.redbox.pkdm.entities.ShelfCategoryMapper;
import com.redbox.pkdm.entities.ShelfFeatureMapper;
import com.redbox.pkdm.repositories.ShelfCategoryMapperRepository;
import com.redbox.pkdm.services.BookService;
import com.redbox.pkdm.services.ShelfCategoryMapperService;
import com.redbox.pkdm.services.ShelfCategoryService;
import com.redbox.pkdm.services.ShelfFeatureService;

@Service
public class ShelfCategoryMapperServiceImpl implements ShelfCategoryMapperService{
	
	@Autowired
	private ShelfCategoryMapperRepository repository;

	@Autowired
	private ShelfFeatureService shelfFeatureService;
	
	@Autowired
	private BookService bookService;

	@Override
	public List<ShelfCategory> findByBookId(String bookId) {
		return repository.findByBookId(bookId);
	}

	@Override
	public List<ShelfCategoryMapper> findMapperByBookId(String bookId) {
		return repository.findMapperByBookId(bookId);
	}

	@Override
	public void save(ShelfCategoryMapper shelfCategoryMapper, String featureId, String bookId) {
		// TODO Auto-generated method stub
		
	}

	

	

}
