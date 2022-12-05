package com.redbox.pkdm.services;

import java.util.List;

import com.redbox.pkdm.entities.Wallet;


public interface WalletService {
	
	void save(Wallet wallet, String userId);
	
	void save(Wallet wallet);
	
	void delete(Wallet wallet);
	
	Wallet findByID(long id);
	
	List<Wallet> findAll();
	
	List<Wallet> findByErase(boolean erase);
	
	List<Wallet> findByDate(String dateFrom, String dateTo);

	List<Wallet> getWalletList(String id);
	
	List<Wallet> findByUser (String id);
	
	List<Wallet> findByStatus (String status);
	
	List<Wallet> findByWalletType (String type);
	
	List<Wallet> findByWalletTypeAndDateFromTo (String type, String dateFrom, String dateTo);

}
