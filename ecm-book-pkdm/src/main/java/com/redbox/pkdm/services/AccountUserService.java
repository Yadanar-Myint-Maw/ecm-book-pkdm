package com.redbox.pkdm.services;


import java.time.LocalDate;
import java.util.List;

import com.redbox.pkdm.entities.AccountAdmin;
import com.redbox.pkdm.entities.AccountUser;


public interface AccountUserService {
	
	void save(AccountUser accountUser);
	
	void delete(AccountUser accountUser);
	
	AccountUser findByID(String id);
	
	String findByLast();
	
	List<AccountUser> findAll();
	
	List<AccountUser> findByErase(boolean erase);
	
	List<AccountUser> findByNameAndErase(String name, boolean erase);
	
	AccountUser findByPhone(String phone);
	
	boolean checkByPhone(String phone);
	
	boolean checkByEmail(String email);

	AccountUser findByPhoneAndPassword(String phone, String password);
	
	AccountUser findByEmailAndPassword(String email, String password);

	AccountUser saveAccountUser(String name, String email, String password, String phone, String gender, LocalDate dob);

	AccountUser updateAccountUser(String id, String name, String email, String password, String phone, String gender,
			LocalDate dob);
	
	long findByCountUser(String level);
	
	long findByCountUserLevel2(String level);

}
