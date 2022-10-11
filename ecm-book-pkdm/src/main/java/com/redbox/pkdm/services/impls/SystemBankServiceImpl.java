package com.redbox.pkdm.services.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redbox.pkdm.entities.SystemBank;
import com.redbox.pkdm.repositories.SystemBankRepository;
import com.redbox.pkdm.services.SystemBankService;

@Service
public class SystemBankServiceImpl implements SystemBankService{
	
	@Autowired
	private SystemBankRepository repository;

	@Override
	public void save(SystemBank systemBank) {
		repository.save(systemBank);
	}

	@Override
	public void delete(SystemBank systemBank) {
		systemBank.setErase(true);
		repository.save(systemBank);
	}

	@Override
	public SystemBank findByID(long id) {
		return repository.findById(id).get();
	}

	@Override
	public String findByLast() {
		return repository.findByLast();
	}

	@Override
	public List<SystemBank> findAll() {
		return repository.findAll();
	}

	@Override
	public List<SystemBank> findByErase(boolean erase) {
		return repository.findByErase(erase);
	}

	@Override
	public List<SystemBank> findByNameAndErase(String name, boolean erase) {
		return repository.findByNameAndErase("%"+name+"%", erase);
	}

	@Override
	public List<SystemBank> findByEraseAndOrderId(boolean erase) {
		return repository.findByEraseAndOrderId(false);
	}

	@Override
	public List<SystemBank> findByNameLike(String name) {
		return repository.findByNameLike(name);
	}
	
	
}
