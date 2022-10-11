package com.redbox.pkdm.services.impls;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.redbox.pkdm.entities.Wallet;
import com.redbox.pkdm.models.WalletModle;
import com.redbox.pkdm.repositories.WalletTopUpRepository;
import com.redbox.pkdm.services.AccountUserService;
import com.redbox.pkdm.services.WalletTopUpService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletTopUpServiceImpl implements WalletTopUpService{
	
	@Autowired
	private WalletTopUpRepository walletRepository;
	
	@Autowired
	private AccountUserService accountUserService;

	
	@Override
	public void save(Wallet wallet, String userId) {
		wallet.setAccountUser(accountUserService.findByID(userId));
		wallet.setDescription("Top Up " + wallet.getAmount() + "MMK into " + wallet.getAccountUser().getId() + " wallet.");
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
		return walletRepository.findById(id).get();
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
	public List<Wallet> findByDate(LocalDate dateFrom, LocalDate dateTo) {
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
	public List<Wallet> findByUser (String id) {
		return walletRepository.findByUser(id);
	}

}
