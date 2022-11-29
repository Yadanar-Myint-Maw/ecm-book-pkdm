package com.redbox.pkdm.services;

import java.util.List;

import com.redbox.pkdm.entities.BookTag;

public interface BookTagService {
	
	void save(BookTag bookTag);
	
	void delete(BookTag bookTag);

	BookTag findByID(long id);
	
	List<BookTag> findByErase(boolean erase);
	
	List<BookTag> findByBook(String id);

}
