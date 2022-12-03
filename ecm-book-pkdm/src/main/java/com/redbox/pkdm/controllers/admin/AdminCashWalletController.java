package com.redbox.pkdm.controllers.admin;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.redbox.pkdm.entities.PurchasedTransition;
import com.redbox.pkdm.services.PurchasedTransitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.redbox.pkdm.entities.AccountAdmin;
import com.redbox.pkdm.entities.SecurityInfo;
import com.redbox.pkdm.entities.Wallet;
import com.redbox.pkdm.services.AccountAdminService;
import com.redbox.pkdm.services.AccountUserService;
import com.redbox.pkdm.services.WalletService;
import com.redbox.pkdm.utilities.MessageUtility;

@Controller
@RequestMapping("admin/cash/wallet")
public class AdminCashWalletController {
	
	@Autowired
	private WalletService walletService;
	
	@Autowired
	private AccountAdminService accountAdminService;
	
	@Autowired
	private AccountUserService accountUserService;

	@Autowired
	private PurchasedTransitionService purchasedTransitionService;
	
	@GetMapping("init/{type}/{id}")
	public String initialize(Model model, @PathVariable String type, @PathVariable String id, String dateFrom, String dateTo, @CookieValue("login_user_id") String cookieId) throws Exception{

		 AccountAdmin loginaccount = accountAdminService.findByID(cookieId);

		 if (loginaccount == null) {

		 return "signin";

		}

		 String redirectPage = "";

		 List<Wallet> wallets = new ArrayList<>();

		 model.addAttribute("loginaccount", loginaccount);

		 model.addAttribute("accountUsers", accountUserService.findByErase(false));

		 model.addAttribute("wallet", walletService.findByID(Long.parseLong(id)));

		 if (type.equals("TOPUP")) {

		 wallets = walletService.findByWalletTypeAndDateFromTo(type, dateFrom, dateTo);

		 redirectPage = "admincashbalancetopup"; 

		 } else if (type.equals("PURCHASED")) {

		 wallets = walletService.findByWalletTypeAndDateFromTo(type, dateFrom, dateTo);

		 redirectPage = "admincashbalancepurchased"; 

		 } else {

		 wallets = walletService.findByDate(dateFrom, dateTo);

		 redirectPage = "admincashbalanceall"; 

		}

		 model.addAttribute("wallets", wallets);

		 model.addAttribute("total", getTotal(wallets));
		 model.addAttribute("purchases", purchasedTransitionService.findAll());
		return redirectPage;
		 
	}
	
	@PostMapping("save")
	public String save(@ModelAttribute("wallet") Wallet wallet, String userId, @CookieValue("login_user_id") String cookieId, RedirectAttributes redirAttrs) {	
		if(userId == null || wallet.getAmount() == 0) {
			redirAttrs.addFlashAttribute("validation", MessageUtility.getDataIsMissingMessage());
		} else {
			if(wallet.getId() == 0) {
				wallet.setWalletType("TOPUP");
				wallet.setDescription(MessageUtility.getTopUpDescription(String.valueOf(wallet.getAmount())));
				wallet.setSecurityInfo(new SecurityInfo(cookieId));
				walletService.save(wallet, userId);
				redirAttrs.addFlashAttribute("save", MessageUtility.getTopUpMessage());
			} else {
				Wallet wallet2 = walletService.findByID(wallet.getId());
				wallet2.setWalletType("TOPUP");
				wallet2.setDescription(MessageUtility.getTopUpDescription(String.valueOf(wallet.getAmount())));
				wallet2.setStatus(wallet.getStatus());
				wallet2.setAccountUser(wallet.getAccountUser());
				wallet2.setAmount(wallet.getAmount());
				wallet2.setDescription(wallet.getDescription());
				wallet2.getSecurityInfo().setUpdateDate(LocalDate.now());
				wallet2.getSecurityInfo().setUpdateTime(LocalTime.now().toString());
				wallet2.getSecurityInfo().setUpdateUser(cookieId);
				walletService.save(wallet2, userId);	
				redirAttrs.addFlashAttribute("update", MessageUtility.getTopUpMessage());
			}
		}
		return "redirect:/admin/wallet/init/TOPUP/0";
	}
	
	private double getTotal (List<Wallet> wallets) {
		double total = 0;
		for (Wallet wallet : wallets) {
			if (wallet.getStatus().equals("APPROVE")) {
				total += wallet.getAmount();
			}
		} 
		return total;
	}


}
