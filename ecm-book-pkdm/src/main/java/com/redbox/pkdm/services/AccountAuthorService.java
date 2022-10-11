package com.redbox.pkdm.services;

import java.util.List;

import com.redbox.pkdm.entities.AccountAuthor;

public interface AccountAuthorService {
	
	void save(AccountAuthor accountAuthor, long shelfId);
	
	void delete(AccountAuthor accountAuthor);
	
	AccountAuthor findByID(String id);
	
	String findByLast();
	
	List<AccountAuthor> findAll();
	
	List<AccountAuthor> findByErase(boolean erase);
	
	AccountAuthor findByUsernameAndPassword(String username, String password);
}
