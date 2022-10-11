package com.redbox.pkdm.services.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redbox.pkdm.entities.AccountAuthor;
import com.redbox.pkdm.repositories.AccountAuthorRepository;
import com.redbox.pkdm.services.AccountAuthorService;
import com.redbox.pkdm.services.ShelfAuthorService;
import com.redbox.pkdm.utilities.IDGeneratorUtility;

@Service
public class AccountAuthorServiceImpl implements AccountAuthorService{
	
	@Autowired
	private AccountAuthorRepository accountAuthorRepository;
	
	@Autowired
	private ShelfAuthorService shelfAuthorService;

	@Override
	public void save(AccountAuthor accountAuthor, long shelfId) {
		if(accountAuthor.getId().isEmpty()) {
			accountAuthor.setId(IDGeneratorUtility.generate(findByLast()));		
		}
		accountAuthor.setShelfAuthor(shelfAuthorService.findByID(shelfId));
		accountAuthorRepository.save(accountAuthor);
	}

	@Override
	public void delete(AccountAuthor accountAuthor) {
		accountAuthor.setErase(true);
		accountAuthorRepository.save(accountAuthor);
		
	}

	@Override
	public AccountAuthor findByID(String id) {
		return accountAuthorRepository.findById(id).get();
		
	}

	@Override
	public List<AccountAuthor> findAll() {
		return accountAuthorRepository.findAll();
	}

	@Override
	public List<AccountAuthor> findByErase(boolean erase) {
		return accountAuthorRepository.findByErase(false);
	}

	@Override
	public String findByLast() {
		return accountAuthorRepository.findByLast();
	}

	@Override
	public AccountAuthor findByUsernameAndPassword(String username, String password) {
		return accountAuthorRepository.findByUsernameAndPassword(username, password);
	}
	
}
