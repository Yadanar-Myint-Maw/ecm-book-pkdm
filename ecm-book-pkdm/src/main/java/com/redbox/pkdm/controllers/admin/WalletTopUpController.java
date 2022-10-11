package com.redbox.pkdm.controllers.admin;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.redbox.pkdm.entities.AccountAdmin;
import com.redbox.pkdm.entities.SecurityInfo;
import com.redbox.pkdm.entities.Wallet;
import com.redbox.pkdm.services.AccountAdminService;
import com.redbox.pkdm.services.AccountUserService;
import com.redbox.pkdm.services.WalletTopUpService;

@Controller
@RequestMapping("admin/purchasement")
public class WalletTopUpController {
	
	@Autowired
	private WalletTopUpService walletTopUpService;
	
	@Autowired
	private AccountAdminService accountAdminService;
	
	@Autowired
	private AccountUserService accountUserService;
	
	
	@GetMapping("/wallets/{id}")
	public String initialize(@PathVariable String id, Model model, String walletType, String userId, @CookieValue("login_user_id") String cookieId,String dateFrom, String dateTo) throws Exception{
		
		AccountAdmin loginaccount = accountAdminService.findByID(cookieId);
		if (loginaccount == null) {
			throw new Exception();
		}
		model.addAttribute("loginaccount", loginaccount);
		
		model.addAttribute("accountUsers", accountUserService.findByErase(false));
		
		if (id.equals("new")) {
			model.addAttribute("wallet", new Wallet());
		} else {
			model.addAttribute("wallet", walletTopUpService.findByID(Long.parseLong(id)));
		}
		
		if (dateFrom == null || dateTo == null || dateFrom.isEmpty() || dateTo.isEmpty()) {
			model.addAttribute("discountcoupons", walletTopUpService.findByErase(false));
		} 
			else {
//			if (dateFrom.equals(dateTo)) {
//				LocalDate date1 = LocalDate.parse(dateFrom);
//				model.addAttribute("discountcoupons", wall.findBySingleDate(date1));	
//			}
//			else {
				LocalDate date1 = LocalDate.parse(dateFrom);
				LocalDate date2 = LocalDate.parse(dateTo);
				model.addAttribute("discountcoupons", walletTopUpService.findByDate(date1, date2));	
//			}
		}
		
		List<Wallet> wals = new ArrayList<>();
		if (walletType == null  && userId == null) {
			wals = walletTopUpService.findByErase(false);
		}else if(walletType.equals("notselected") && !userId.equals("notselected")) {
			List<Wallet> w1 = walletTopUpService.findByErase(false);
			for(Wallet w : w1) {
				if(w.getAccountUser().getId().equals(userId)) {
					wals.add(w);
				}
			}
			
		}else if(!walletType.equals("notselected") && userId.equals("notselected")) {
			List<Wallet> w1 = walletTopUpService.findByErase(false);
			for(Wallet w : w1) {
				if(w.getWalletType().equals(walletType)) {
					wals.add(w);
				}
			}
			
			
		}else if(walletType != null && userId != null) {
			List<Wallet> w1 = walletTopUpService.findByErase(false);
			for(Wallet w : w1) {
				if(w.getAccountUser().getId().equals(userId) && w.getWalletType().equals(walletType)) {
					wals.add(w);
				}
			}
			
		}
				
		model.addAttribute("wal", wals);
		return "adminpurchasementwallettopup";
	}
	
	@PostMapping("/wallets/save")
	public String save(@ModelAttribute("wallet") Wallet wallet,@RequestParam String userId, @CookieValue("login_user_id") String cookieId, RedirectAttributes redirAttrs) {	
		if(wallet.getWalletType().equals("notSelected")|| userId == null || wallet.getAmount() == 0) {
			redirAttrs.addFlashAttribute("validation","Data is missing");
		}else {
			if(wallet.getId() == 0) {
				wallet.setSecurityInfo(new SecurityInfo(cookieId));
				walletTopUpService.save(wallet,userId);
				redirAttrs.addFlashAttribute("save","Wallet TopUp save successfully");
			}
			else {
				Wallet wallet2 = walletTopUpService.findByID(wallet.getId());
				wallet2.setWalletType(wallet.getWalletType());
				wallet2.setAccountUser(wallet.getAccountUser());
				wallet2.setAmount(wallet.getAmount());
				wallet2.setDescription(wallet.getDescription());
				wallet2.getSecurityInfo().setUpdateDate(LocalDate.now());
				wallet2.getSecurityInfo().setUpdateTime(LocalTime.now().toString());
				wallet2.getSecurityInfo().setUpdateUser(cookieId);
				walletTopUpService.save(wallet2,userId);	
				redirAttrs.addFlashAttribute("update", "Wallet TopUp update successfully");
			}
			
		}
		
		return "redirect:/admin/purchasement/wallets/new";
	}
	
	@GetMapping("/wallets/delete/{id}")
	public String delete(@PathVariable String id , RedirectAttributes redirAttrs) {
		walletTopUpService.delete(walletTopUpService.findByID(Long.parseLong(id)));
		redirAttrs.addFlashAttribute("validation","Wallet TopUp delete successfully");
		return "redirect:/admin/purchasement/wallets/new";

	}

}
