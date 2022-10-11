package com.redbox.pkdm.services;

import java.util.List;

import com.redbox.pkdm.entities.AccountAdmin;

public interface AccountAdminService {
	
	void save(AccountAdmin accountAdmin);
	
	void delete(AccountAdmin accountAdmin);

	AccountAdmin findByID(String id);
	
	String findByLast();
	
	List<AccountAdmin> findAll();
	
	List<AccountAdmin> findByErase(boolean erase);
	
	List<AccountAdmin> findByNameAndErase(String name, boolean erase);
	
	AccountAdmin findByUsernameAndPassword(String username, String password);
	
	long findCountByErase();
	
	long findCountByAdmin();
	
}
