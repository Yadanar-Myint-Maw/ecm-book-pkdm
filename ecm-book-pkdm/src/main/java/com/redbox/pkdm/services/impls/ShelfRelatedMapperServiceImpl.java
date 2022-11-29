package com.redbox.pkdm.services.impls;

import java.util.List;

import com.redbox.pkdm.entities.ShelfRelated;
import com.redbox.pkdm.entities.ShelfRelatedMapper;
import com.redbox.pkdm.repositories.ShelfRelatedMapperRepository;
import com.redbox.pkdm.services.ShelfRelatedMapperService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShelfRelatedMapperServiceImpl implements ShelfRelatedMapperService {
	
	@Autowired
	private ShelfRelatedMapperRepository shelfRelatedMapperRepository;

	@Override
	public void save(ShelfRelatedMapper shelfRelatedMapper) {
		shelfRelatedMapperRepository.save(shelfRelatedMapper);
	}

	@Override
	public void delete(ShelfRelatedMapper shelfRelatedMapper) {
		shelfRelatedMapper.setErase(true);
		shelfRelatedMapperRepository.save(shelfRelatedMapper);
	}

	@Override
	public ShelfRelatedMapper findByID(long id) {
		return shelfRelatedMapperRepository.findById(id).get();
	}

	@Override
	public List<ShelfRelatedMapper> findByErase(boolean erase) {
		return shelfRelatedMapperRepository.findByErase(erase);
	}

	@Override
	public List<ShelfRelated> findByBook(String id) {
		return shelfRelatedMapperRepository.findByBook(id);
	}
	
	@Override
	public List<ShelfRelatedMapper> findByBookID(String bookID) {
		return shelfRelatedMapperRepository.findByBookID(bookID);
	}

	

}
