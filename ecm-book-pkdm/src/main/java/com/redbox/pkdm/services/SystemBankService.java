package com.redbox.pkdm.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.redbox.pkdm.entities.AccountUser;
import com.redbox.pkdm.entities.ShelfAuthor;
import com.redbox.pkdm.entities.SystemBank;

@Service
public interface SystemBankService {

	
	void save(SystemBank systemBank);
	
	void delete(SystemBank systemBank);
	
	SystemBank findByID(long id);
	
	String findByLast();
	
	List<SystemBank> findAll();
	
	List<SystemBank> findByErase(boolean erase);
	
	List<SystemBank> findByNameAndErase(String name, boolean erase);
	
	List<SystemBank> findByEraseAndOrderId(boolean erase);

	List<SystemBank> findByNameLike(String name);
}
