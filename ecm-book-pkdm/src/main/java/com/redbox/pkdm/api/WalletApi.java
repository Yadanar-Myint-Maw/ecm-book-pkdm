package com.redbox.pkdm.api;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.redbox.pkdm.entities.AccountUser;
import com.redbox.pkdm.entities.Book;
import com.redbox.pkdm.entities.PurchasedTransition;
import com.redbox.pkdm.entities.SecurityInfo;
import com.redbox.pkdm.entities.Wallet;
import com.redbox.pkdm.models.WalletModel;
import com.redbox.pkdm.services.AccountUserService;
import com.redbox.pkdm.services.BookService;
import com.redbox.pkdm.services.PurchasedTransitionService;
import com.redbox.pkdm.services.WalletTopUpService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wallet")
public class WalletApi {
		
	@Autowired
	private WalletTopUpService walletTopUpService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private AccountUserService accountUserService;
	
	@Autowired
	private PurchasedTransitionService purchasedTransitionService;
	
	@GetMapping("/walletlist")
	public List<Wallet> getWalletList(@PathVariable String id){
		return walletTopUpService.getWalletList(id);
	}
	
	@GetMapping("/purchasebooksection/{userID}/{bookID}/{sectionID}")
	public boolean purchasebooksection (@PathVariable String userID, @PathVariable String bookID, @PathVariable String sectionID) {
		try {
			Book book = bookService.findByID(bookID);
			AccountUser accountUser = accountUserService.findByID(userID);
			Wallet wallet = new Wallet();
			wallet.setWalletType("Purchased");
			wallet.setDescription("Purchasing " + book.getPrice() + " MMK  for " + book.getName() + " book. Thanks for purchasing.");
			wallet.setAmount(book.getPrice());
			wallet.setAccountUser(accountUser);
			wallet.setSecurityInfo(new SecurityInfo());
			wallet.getSecurityInfo().setCreateDate(LocalDate.now());
			wallet.getSecurityInfo().setCreateTime(LocalTime.now().toString());
			wallet.getSecurityInfo().setCreateUser(accountUser.getId());
			walletTopUpService.save(wallet);
			PurchasedTransition transition = new PurchasedTransition();
			transition.setAccountUser(accountUser);
			transition.setBook(book);
			transition.setBookType(sectionID);
			transition.setPaymentType("Application");
			transition.setSecurityInfo(new SecurityInfo());
			purchasedTransitionService.save(transition);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@GetMapping("/purchase/{userID}/{BookID}")
	public boolean purchasebook (@PathVariable String userID, @PathVariable String BookID) {
		try {
			Book book = bookService.findByID(BookID);
			AccountUser accountUser = accountUserService.findByID(userID);
			Wallet wallet = new Wallet();
			wallet.setWalletType("Purchased");
			wallet.setDescription("Purchasing " + book.getPrice() + " MMK  for " + book.getName() + " book. Thanks for purchasing.");
			wallet.setAmount(book.getPrice());
			wallet.setAccountUser(accountUser);
			wallet.setSecurityInfo(new SecurityInfo());
			wallet.getSecurityInfo().setCreateDate(LocalDate.now());
			wallet.getSecurityInfo().setCreateTime(LocalTime.now().toString());
			wallet.getSecurityInfo().setCreateUser(accountUser.getId());
			walletTopUpService.save(wallet);
			PurchasedTransition transition = new PurchasedTransition();
			transition.setAccountUser(accountUser);
			transition.setBook(book);
			transition.setBookType("ALL");
			transition.setPaymentType("Application");
			transition.setSecurityInfo(new SecurityInfo());
			purchasedTransitionService.save(transition);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@GetMapping("/findwalletamount/{id}")
	public double findWalletAmount (@PathVariable String id) {
		double wallet_amount = 0.0;
		List<Wallet> wallets = walletTopUpService.findByUser(id);
		for (Wallet wallet : wallets) {
			if (wallet.getWalletType().equals("Top Up")) {
				wallet_amount += wallet.getAmount();
			} else {
				wallet_amount -= wallet.getAmount();
			}
		}
		return wallet_amount;
	}
	
	@GetMapping("/findwalletdata/{id}")
	public List<WalletModel> findWalletData (@PathVariable String id) {
		WalletModel model = new WalletModel();
		List<WalletModel> models = new ArrayList<>();
		List<Wallet> wallets = walletTopUpService.findByUser(id);
		for (Wallet w : wallets) {
			model.setId(w.getId());
			model.setDate(w.getSecurityInfo().getCreateDate().toString());
			model.setTime(w.getSecurityInfo().getCreateTime());
			model.setWalletType(w.getWalletType());
			model.setDescription(w.getDescription());
			model.setAmount(w.getAmount());
			models.add(model);
			model = new WalletModel();
		}
		return models;
	}

}
