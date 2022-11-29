package com.redbox.pkdm.services.impls;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.redbox.pkdm.entities.Wallet;
import com.redbox.pkdm.models.WalletModle;
import com.redbox.pkdm.repositories.WalletRepository;
import com.redbox.pkdm.services.AccountUserService;
import com.redbox.pkdm.services.WalletService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl implements WalletService{
	
	@Autowired
	private WalletRepository walletRepository;
	
	@Autowired
	private AccountUserService accountUserService;

	
	@Override
	public void save(Wallet wallet, String userId) {
		wallet.setAccountUser(accountUserService.findByID(userId));
		walletRepository.save(wallet);
	}
	
	@Override
	public void save(Wallet wallet) {
		walletRepository.save(wallet);
		
	}

	@Override
	public void delete(Wallet wallet) {
		wallet.setErase(true);
		walletRepository.save(wallet);
		
	}

	@Override
	public Wallet findByID(long id) {
		if (id == 0) {
			return new Wallet();
		} else {
			return walletRepository.findById(id).get();
		}
	}

	@Override
	public List<Wallet> findAll() {
		return walletRepository.findAll();
	}

	@Override
	public List<Wallet> findByErase(boolean erase) {
		return walletRepository.findByErase(false);
	}

	@Override
	public List<Wallet> findByDate(String from, String to) {
		if (from == null || to == null || from.isEmpty() || to.isEmpty()) {
			return findAll();
		}
		LocalDate dateFrom = LocalDate.parse(from);
		LocalDate dateTo = LocalDate.parse(to);
		return walletRepository.findByDate(dateFrom, dateTo);
	}

	@Override
	public List<Wallet> getWalletList(String id) {
		List<WalletModle> walletModles = walletRepository.getWalletList(id).stream()
				.map(w -> {
					return new WalletModle(w.getId(),
							w.getWalletType(),
							w.getDescription(),
							w.getAmount());
				}).collect(Collectors.toList());
		walletModles.forEach(w -> {
			w.getId();
			w.getWalletType();
			w.getDescription();
			w.getAmount();
		});
		return walletRepository.getWalletList(id);
	}
	
	@Override
	public List<Wallet> findByStatus (String status) {
		return walletRepository.findByStatus(status);
	}
	
	@Override
	public List<Wallet> findByWalletType (String type) {
		return walletRepository.findBywalletType(type);
	}
	
	@Override
	public List<Wallet> findByWalletTypeAndDateFromTo (String type, String from, String to) {
		if (from == null || to == null || from.isEmpty() || to.isEmpty()) {
			return walletRepository.findBywalletType(type);
		} 
		LocalDate dateFrom = LocalDate.parse(from);
		LocalDate dateTo = LocalDate.parse(to);
		return walletRepository.findBywalletTypeAndDateFromTo(type, dateFrom, dateTo);
	}
	
	@Override
	public List<Wallet> findByUser (String id) {
		return walletRepository.findByUser(id);
	}

}
