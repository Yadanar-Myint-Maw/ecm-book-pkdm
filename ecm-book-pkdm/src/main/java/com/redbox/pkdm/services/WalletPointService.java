package com.redbox.pkdm.services;

import java.util.List;

import com.redbox.pkdm.entities.WalletPoint;

public interface WalletPointService {
	
	void save(WalletPoint walletPoint);
		
	void delete(WalletPoint walletPoint);
	
	WalletPoint findByID(long id);
	
	List<WalletPoint> findByErase(boolean erase);
	
	List<WalletPoint> findByUserId(String userId);
	

}
