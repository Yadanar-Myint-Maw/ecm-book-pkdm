package com.redbox.pkdm.controllers.admin;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
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
import com.redbox.pkdm.entities.AccountUser;
import com.redbox.pkdm.entities.SecurityInfo;
import com.redbox.pkdm.entities.SystemBank;
import com.redbox.pkdm.services.AccountAdminService;
import com.redbox.pkdm.services.SystemBankService;
import com.redbox.pkdm.utilities.ImageUploadUtility;


@Controller
@RequestMapping("admin/payment")
public class SystemBankController {
	
	@Autowired
	private SystemBankService systemBankService;
	
	@Autowired
	private AccountAdminService accountAdminService;
	
	@GetMapping("/systembanks/{id}")
	public  String initialize(@PathVariable String id, @CookieValue("login_user_id") String cookieId, Model model, String keyword) throws Exception {
		AccountAdmin loginaccount = accountAdminService.findByID(cookieId);
		if (loginaccount == null) {
			throw new Exception();
		}
		model.addAttribute("loginaccount", loginaccount);
		if(id.equals("new")) {
			model.addAttribute("systembank", new SystemBank());
		}else {
			model.addAttribute("systembank", systemBankService.findByID(Long.parseLong(id)));
		}		
		if(keyword == null) {
			model.addAttribute("systembanks", systemBankService.findByErase(false));
		}else {
			List<SystemBank> systemBanks = systemBankService.findByErase(false);
			List<SystemBank> systemBanks2 = new ArrayList<>();
			for(SystemBank b : systemBanks) {
				if(b.getName().toLowerCase().contains(keyword.toLowerCase())) {
					systemBanks2.add(b);
				}
			}
			model.addAttribute("systembanks", systemBanks2);
		}
		return "adminsystembanks";
	}
	
	@PostMapping("/systembanks/save")
	public String save(@ModelAttribute("systembank") SystemBank systemBank, @CookieValue("login_user_id") String cookieId, Model model, RedirectAttributes redirAttrs) {
		if(systemBank.getFile().isEmpty() || systemBank.getName().isEmpty()) {
			redirAttrs.addFlashAttribute("validation", "Data is missing!");
		}else {
			if(systemBank.getId() == 0 && systemBank.getFile() != null) {
				systemBank.setImage(ImageUploadUtility.upload(systemBank.getFile()));
				systemBank.getSecurityInfo().setUpdateDate(LocalDate.now());
				systemBank.getSecurityInfo().setUpdateTime(LocalTime.now().toString());
				systemBank.setSecurityInfo(new SecurityInfo(cookieId));
				systemBankService.save(systemBank);
				redirAttrs.addFlashAttribute("save", "Payment Option save successfully.");
			}
		}
		return "redirect:/admin/payment/systembanks/new";
	}
	
	@GetMapping("/systembanks/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes redirAttrs) {
		systemBankService.delete(systemBankService.findByID(id));
		redirAttrs.addFlashAttribute("validation", "Payment Option delete successfully!");
		return "redirect:/admin/payment/systembanks/new";
	}

}
