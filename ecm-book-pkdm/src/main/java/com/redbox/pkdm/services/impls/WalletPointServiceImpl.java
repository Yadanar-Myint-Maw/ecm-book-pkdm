package com.redbox.pkdm.services.impls;

import java.util.List;

import com.redbox.pkdm.entities.WalletPoint;
import com.redbox.pkdm.repositories.WalletPointRepository;
import com.redbox.pkdm.services.WalletPointService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletPointServiceImpl implements WalletPointService{
	
	@Autowired
	private WalletPointRepository repository;

	@Override
	public void save(WalletPoint walletPoint) {
		repository.save(walletPoint);
	}

	
	@Override
	public void delete(WalletPoint walletPoint) {
		walletPoint.setErase(true);
		repository.save(walletPoint);
	}

	@Override
	public WalletPoint findByID(long id) {
		return repository.findById(id).get();
	}


	@Override
	public List<WalletPoint> findByErase(boolean erase) {
		return repository.findByErase(erase);
	}


	@Override
	public List<WalletPoint> findByUserId(String userId) {
		return repository.findByUserId(userId);
	}

}
