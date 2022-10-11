package com.redbox.pkdm.services.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.redbox.pkdm.entities.ShelfCategory;
import com.redbox.pkdm.entities.ShelfCategoryMapper;
import com.redbox.pkdm.repositories.ShelfCategoryMapperRepository;
import com.redbox.pkdm.services.BookService;
import com.redbox.pkdm.services.ShelfCategoryMapperService;
import com.redbox.pkdm.services.ShelfCategoryService;

@Service
public class ShelfCategoryMapperServiceImpl implements ShelfCategoryMapperService{
	
	@Autowired
	private ShelfCategoryMapperRepository repository;
	
	@Autowired
	private ShelfCategoryService shelfCategoryService;
	
	@Autowired
	private BookService bookService;

	@Override
	public void save(ShelfCategoryMapper shelfCategoryMapper) {
		repository.save(shelfCategoryMapper);
	}
	
	@Override
	public void delete(ShelfCategoryMapper shelfCategoryMapper) {
		shelfCategoryMapper.setErase(true);
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

	@Override
	public void save(ShelfCategoryMapper shelfCategoryMapper, String bookId, String catId) {
		shelfCategoryMapper.setBook(bookService.findByID(bookId));
		shelfCategoryMapper.setShelfCategory(shelfCategoryService.findByID(Long.parseLong(catId)));
		repository.save(shelfCategoryMapper);
	}

	@Override
	public List<ShelfCategoryMapper> findAll() {
		return repository.findAll();
	}

	@Override
	public ShelfCategoryMapper findById(long id) {
		return repository.findById(id).get();
	}

	@Override
	public List<ShelfCategoryMapper> findByErase(boolean erase) {
		return repository.findByErase(false);
	}
}
