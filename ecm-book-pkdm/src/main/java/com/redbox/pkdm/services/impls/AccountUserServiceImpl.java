package com.redbox.pkdm.services.impls;

import java.time.LocalDate;
import java.util.List;

import com.redbox.pkdm.entities.AccountUser;
import com.redbox.pkdm.repositories.AccountUserRepository;
import com.redbox.pkdm.services.AccountUserService;
import com.redbox.pkdm.utilities.IDGeneratorUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountUserServiceImpl implements AccountUserService{
	
	@Autowired
	private AccountUserRepository repository;

	@Override
	public void save(AccountUser accountUser) {
		if (accountUser.getId() == null) {
			accountUser.setId(IDGeneratorUtility.generate(findByLast()));
		}
		repository.save(accountUser);	
	}

	@Override
	public void delete(AccountUser accountUser) {
		accountUser.setErase(true);
		repository.save(accountUser);
	}

	@Override
	public AccountUser findByID(String id) {
		return repository.findById(id).get();
	}

	@Override
	public String findByLast() {
		return repository.findByLast();
	}

	@Override
	public List<AccountUser> findAll() {
		return repository.findAll();
	}

	@Override
	public List<AccountUser> findByErase(boolean erase) {
		return repository.findByErase(false);
	}

	@Override
	public List<AccountUser> findByNameAndErase(String name, boolean erase) {
		return repository.findByNameAndErase("%"+name+"%", erase);
	}

	@Override
	public AccountUser findByEmailAndPassword(String email, String password) {
		return repository.findByEmailAndPassword(email, password);
	}

	@Override
	public String findByPhone(String phone) {
		return repository.findByPhone(phone);
	}

	@Override
	public AccountUser findByPhoneAndPassword(String phone, String password) {
		return repository.findByPhoneAndPassword(phone, password);
	}

	@Override
	public AccountUser saveAccountUser(String name, String email, String password, String phone, String gender,
			LocalDate dob) {
		AccountUser accountUser = new AccountUser();
		accountUser.setId(IDGeneratorUtility.generate(findByLast()));
		accountUser.setName(name);
		accountUser.setEmail(email);
		accountUser.setPassword(password);
		accountUser.setPhone(phone);
		accountUser.setGender(gender);
		accountUser.setDob(dob);
		return repository.save(accountUser);
	}

	@Override
	public AccountUser updateAccountUser(String id, String name, String email, String password, String phone, String gender,
			LocalDate dob) {
		AccountUser accountUser = new AccountUser();
		accountUser.setId(id);
		accountUser.setName(name);
		accountUser.setEmail(email);
		accountUser.setPassword(password);
		accountUser.setPhone(phone);
		accountUser.setGender(gender);
		accountUser.setDob(dob);
		return repository.save(accountUser);
	}

	@Override
	public boolean checkByPhone(String phone) {
		return repository.findCountByPhone(phone) == 0 ? true : false;
	}

	@Override
	public boolean checkByEmail(String email) {
		return repository.findCountByEmail(email) == 0 ? true : false;
	}

	@Override
	public long findByCountUser(String level) {
		return repository.findByCountUser(level);
	}

	@Override
	public long findByCountUserLevel2(String level) {
		return repository.findByCountUserLevel2(level);
	}

}
