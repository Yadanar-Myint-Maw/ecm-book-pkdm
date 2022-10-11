package com.redbox.pkdm.services.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redbox.pkdm.entities.AccountAdmin;
import com.redbox.pkdm.repositories.AccountAdminRepository;
import com.redbox.pkdm.services.AccountAdminService;
import com.redbox.pkdm.utilities.IDGeneratorUtility;

@Service
public class AccountAdminServiceImpl implements AccountAdminService {

	@Autowired
	private AccountAdminRepository repository;
	
	
	@Override
	public void save(AccountAdmin accountAdmin) {
		if (accountAdmin.getId().isEmpty()) {
			accountAdmin.setId(IDGeneratorUtility.generate(findByLast()));	
		}
		repository.save(accountAdmin);		
	}
	
	@Override
	public void delete(AccountAdmin accountAdmin) {
		accountAdmin.setErase(true);
		repository.save(accountAdmin);
	}
	
	@Override
	public String findByLast() {
		return repository.findByLast();
	}
	
	@Override
	public long findCountByErase () {
		return repository.findCountByErase();
	}
	
	@Override
	public AccountAdmin findByID(String id) {
		return repository.findById(id).get();
	}

	@Override
	public List<AccountAdmin> findAll() {
		return repository.findAll();
	}

	@Override
	public List<AccountAdmin> findByErase(boolean erase) {
		return repository.findByErase(false);
	}

	@Override
	public List<AccountAdmin> findByNameAndErase(String name, boolean erase) {
		return repository.findByNameAndErase("%"+name+"%", erase);
	}

	@Override
	public AccountAdmin findByUsernameAndPassword(String username, String password) {
		return repository.findByUsernameAndPassword(username, password);
	}


	
	
	
}
