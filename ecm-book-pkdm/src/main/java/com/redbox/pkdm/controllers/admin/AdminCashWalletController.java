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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.redbox.pkdm.entities.AccountAdmin;
import com.redbox.pkdm.entities.DeliveryInfo;
import com.redbox.pkdm.entities.DiscountCoupon;
import com.redbox.pkdm.entities.PurchasedTransition;
import com.redbox.pkdm.entities.SecurityInfo;
import com.redbox.pkdm.entities.Wallet;
import com.redbox.pkdm.models.PurchaseByInvoiceModel;
import com.redbox.pkdm.services.AccountAdminService;
import com.redbox.pkdm.services.AccountUserService;
import com.redbox.pkdm.services.PurchasedTransitionService;
import com.redbox.pkdm.services.WalletService;
import com.redbox.pkdm.utilities.MessageUtility;

import net.bytebuddy.asm.Advice.Local;

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
		 
		 //MyosandiKyaw
		 List<PurchaseByInvoiceModel> purchaseByInvoiceModelList = new ArrayList<>();
		 List<PurchasedTransition> purchasedTransitions = new ArrayList<>();
		 
		 if(dateFrom == null && dateTo == null) {
			 purchasedTransitions = purchasedTransitionService.findByInvoiceNoWithGroupBy();
		 }else if(dateFrom.isEmpty() && dateTo != null ){
			 purchasedTransitions = purchasedTransitionService.findByInvoiceNoAndDateWithGroupBy(LocalDate.parse(dateTo));
		 }else if(dateFrom != null && dateTo.isEmpty()){
			 purchasedTransitions = purchasedTransitionService.findByInvoiceNoAndDateWithGroupBy(LocalDate.parse(dateFrom));
		 }else {
			 purchasedTransitions = purchasedTransitionService.findByInvoiceNoAndDateBetweenWithGroupBy(LocalDate.parse(dateFrom), LocalDate.parse(dateTo));
		 } 
		 
		 for(PurchasedTransition purchasedTransition : purchasedTransitions) {
			 double total = 0;
			 double deliveryFee = 0;
			 List<DiscountCoupon> discountCoupons = new ArrayList<>();
			 String deliveryInfo = "";
			 
			 List<PurchasedTransition> purchasedTransitionsByInvoiceNo = purchasedTransitionService.findByInvoiceNo(purchasedTransition.getInvoice_no());
			 for(PurchasedTransition purchasedTransition2 : purchasedTransitionsByInvoiceNo) {
				 total += purchasedTransition2.getTotal();
				 
				 if(purchasedTransition2.getDiscountCoupon() != null) {
					 if(purchasedTransition2.getDiscountCoupon().getCouponType().equals("UserCoupon") || 
							 purchasedTransition2.getDiscountCoupon().getCouponType().equals("OtherCoupon")
						 ) {
						 discountCoupons.add(purchasedTransition2.getDiscountCoupon());
					 }
				 }
				 
				 if(deliveryFee <= 0) {
					 if(purchasedTransition2.isBookType() == true) {
						 deliveryInfo = purchasedTransition2.getDeliveryInfo().getDeliveryTownship().getName() + ", " + 
								 			purchasedTransition2.getDeliveryInfo().getDeliveryRegion().getName(); 
						deliveryFee = purchasedTransition2.getDeliveryFee();
					 }
				 }	
			 }
			 purchaseByInvoiceModelList.add(new PurchaseByInvoiceModel(purchasedTransition.getAccountUser(), purchasedTransition.getInvoice_no(),
					 purchasedTransitionsByInvoiceNo, discountCoupons, total+deliveryFee , deliveryInfo, deliveryFee));
		 }
		 
		 
		 model.addAttribute("purchases", purchaseByInvoiceModelList); 
		 
		 redirectPage = "admincashbalancepurchased"; 

		 } else {
			 
		 wallets = walletService.findByDate(dateFrom, dateTo);
		 redirectPage = "admincashbalanceall"; 
		}
		 
		 
		 model.addAttribute("wallets", wallets);

		 model.addAttribute("total", getTotal(wallets));
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
				wallet2.setAmount(wallet.getAmount());
				wallet2.getSecurityInfo().setUpdateDate(LocalDate.now());
				wallet2.getSecurityInfo().setUpdateTime(LocalTime.now().toString());
				wallet2.getSecurityInfo().setUpdateUser(cookieId);
				walletService.save(wallet2, userId);	
				redirAttrs.addFlashAttribute("update", MessageUtility.getTopUpMessage());	
			}	
		}
		return "redirect:/admin/cash/wallet/init/TOPUP/0";
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
