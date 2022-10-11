package com.redbox.pkdm.services;

import java.time.LocalDate;
import java.util.List;

import com.redbox.pkdm.entities.Wallet;


public interface WalletTopUpService {
	
	void save(Wallet wallet, String userId);
	
	void save(Wallet wallet);
	
	void delete(Wallet wallet);
	
	Wallet findByID(long id);
	
	List<Wallet> findAll();
	
	List<Wallet> findByErase(boolean erase);
	
	List<Wallet> findByDate(LocalDate dateFrom, LocalDate dateTo);

	List<Wallet> getWalletList(String id);
	
	List<Wallet> findByUser (String id);

}
