package com.redbox.pkdm.controllers.admin;

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
import com.redbox.pkdm.entities.AccountUser;
import com.redbox.pkdm.entities.SecurityInfo;
import com.redbox.pkdm.entities.WalletPoint;
import com.redbox.pkdm.services.AccountAdminService;
import com.redbox.pkdm.services.AccountUserService;
import com.redbox.pkdm.services.WalletPointService;

@Controller
@RequestMapping("admin/wallet")
public class WalletPointTopUpController {
	
	@Autowired
	private AccountAdminService accountAdminService;
	
	@Autowired
	private WalletPointService walletPointService;
	
	@Autowired
	private AccountUserService accountUserService;
	
	@GetMapping("/point/{id}")
	public String initialize(@PathVariable String id ,String pointType, String userId, @CookieValue("login_user_id") String cookieId, Model model) throws Exception {
		
		AccountAdmin loginaccount = accountAdminService.findByID(cookieId);
		if (loginaccount == null) {
			throw new Exception();
		}
		model.addAttribute("loginaccount", loginaccount);
		model.addAttribute("accountUsers", accountUserService.findByErase(false));
		
		if (id.equals("new")) {
			model.addAttribute("walletPoint", new WalletPoint());
		} else {
			model.addAttribute("walletPoint", walletPointService.findByID(Long.parseLong(id)));
		}
		
		List<WalletPoint> walletPoints = new ArrayList<>();
		
		if(pointType == null && userId == null) {
			walletPoints = walletPointService.findByErase(false);
		}else {
			walletPoints = walletPointService.findByUserId(userId);
		}
		
		model.addAttribute("walletPoints", walletPoints);

		return "adminwalletpoint";
		
	}
	
	@PostMapping("/point/save")
	public String save(@ModelAttribute("walletPoint") WalletPoint walletPoint,@RequestParam String userId, @CookieValue("login_user_id") String cookieId, RedirectAttributes redirAttrs) {	
		if(walletPoint.getPointType().equals("notSelected")|| userId == null || walletPoint.getAmount() == 0) {
			redirAttrs.addFlashAttribute("validation","Data is missing");
		}else {
			if(walletPoint.getId() == 0) {
				walletPoint.setSecurityInfo(new SecurityInfo(cookieId));
				AccountUser accountUser = accountUserService.findByID(userId);
				walletPoint.setAccountUser(accountUser);
				walletPoint.setDescription(walletPoint.getAmount() + "Poins into ACCOUNT - " + accountUser.getName() + "(" + accountUser.getId() + ")" );
				walletPointService.save(walletPoint);
				redirAttrs.addFlashAttribute("save","Wallet TopUp save successfully");
			}
//			else {
//				WalletPoint walletPoint2 = walletPointService.findByID(walletPoint.getId());
//				walletPoint2.setPointType(walletPoint.getPointType());
//				
//				AccountUser accountUser = accountUserService.findByID(userId);
//				walletPoint2.setAccountUser(accountUser);
//				
//				walletPoint2.setAmount(walletPoint.getAmount());
//				walletPoint2.setDescription(walletPoint.getDescription());
//				walletPoint2.getSecurityInfo().setUpdateDate(LocalDate.now());
//				walletPoint2.getSecurityInfo().setUpdateTime(LocalTime.now().toString());
//				walletPoint2.getSecurityInfo().setUpdateUser(cookieId);
//				walletPointService.save(walletPoint2);	
//				redirAttrs.addFlashAttribute("update", "Wallet TopUp update successfully");
//			}	
//			
		}
		
		return "redirect:/admin/wallet/point/new";
	}
	
	@GetMapping("/point/delete/{id}")
	public String delete(@PathVariable String id , RedirectAttributes redirAttrs) {
		walletPointService.delete(walletPointService.findByID(Long.parseLong(id)));
		redirAttrs.addFlashAttribute("validation","Wallet Point delete successfully");
		return "redirect:/admin/wallet/point/new";

	}

	

}
