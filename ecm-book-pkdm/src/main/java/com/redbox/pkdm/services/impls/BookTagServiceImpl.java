package com.redbox.pkdm.services.impls;

import java.util.List;

import com.redbox.pkdm.entities.BookTag;
import com.redbox.pkdm.repositories.BookTagRepository;
import com.redbox.pkdm.services.BookTagService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookTagServiceImpl implements BookTagService {

	@Autowired
	private BookTagRepository bookTagRepository;
	
	@Override
	public void save(BookTag bookTag) {
		bookTagRepository.save(bookTag);
	}

	@Override
	public void delete(BookTag bookTag) {
		bookTag.setErase(true);
		bookTagRepository.save(bookTag);
	}

	@Override
	public BookTag findByID(long id) {
		return bookTagRepository.findById(id).get();
	}

	@Override
	public List<BookTag> findByErase(boolean erase) {
		return bookTagRepository.findByErase(erase);
	}

	@Override
	public List<BookTag> findByName(String name) {
		return bookTagRepository.findByName(name);
	}

}
