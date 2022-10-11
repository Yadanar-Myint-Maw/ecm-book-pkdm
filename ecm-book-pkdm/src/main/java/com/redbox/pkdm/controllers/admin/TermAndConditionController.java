package com.redbox.pkdm.controllers.admin;

import java.time.LocalDate;
import java.time.LocalTime;

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
import com.redbox.pkdm.entities.TermAndCondition;
import com.redbox.pkdm.services.AccountAdminService;
import com.redbox.pkdm.services.TermAndConditionService;


@Controller
@RequestMapping("admin/account")
public class TermAndConditionController {

	@Autowired
	private TermAndConditionService termAndConditionService;
	
	@Autowired
	private AccountAdminService accountAdminService;
	
	@GetMapping("/termandconditions/{id}")
	public String initialize(@PathVariable String id, @CookieValue("login_user_id") String cookieId, Model model) throws Exception {
		AccountAdmin loginaccount = accountAdminService.findByID(cookieId);
		if (loginaccount == null) {
			throw new Exception();
		}
		model.addAttribute("loginaccount", loginaccount);
		if(id.equals("new")) {
			model.addAttribute("termandcondition", new TermAndCondition());
		}else {
			model.addAttribute("termandcondition", termAndConditionService.findByID(Long.parseLong(id)));
		}
		model.addAttribute("termandconditons", termAndConditionService.findByErase(false));
		return "admintermandconditions";
	}
	
	@PostMapping("/termandconditions/save")
	public String save(@ModelAttribute("termandcondition") TermAndCondition termAndCondition, @CookieValue("login_user_id") String cookieId, Model model, RedirectAttributes redirAttrs) {
		if (termAndCondition.getSortNumber() == 0 || termAndCondition.getTitle().isEmpty() || termAndCondition.getDescription().isEmpty()) {
			redirAttrs.addFlashAttribute("validation", "Data is missing!");
		}else {
			if(termAndCondition.getId() == 0) {
				termAndConditionService.save(termAndCondition);
				termAndCondition.setSecurityInfo(new SecurityInfo(cookieId));
				redirAttrs.addFlashAttribute("save", "Term & Condition save successfully.");
		}else {
			TermAndCondition termAndCondition2 = termAndConditionService.findByID(termAndCondition.getId());
			termAndCondition2.setSortNumber(termAndCondition.getSortNumber());
			termAndCondition2.setTitle(termAndCondition.getTitle());
			termAndCondition2.setDescription(termAndCondition.getDescription());
			termAndCondition2.getSecurityInfo().setUpdateDate(LocalDate.now());
			termAndCondition2.getSecurityInfo().setUpdateTime(LocalTime.now().toString());
			termAndCondition2.getSecurityInfo().setUpdateUser(cookieId);
			termAndConditionService.save(termAndCondition2);
			redirAttrs.addFlashAttribute("save", "Term & Condition update successfully.");
			}
		}
		return "redirect:/admin/account/termandconditions/new";
		
	}
	
	@GetMapping("/termandconditions/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes redirAttrs) {
		termAndConditionService.delete(termAndConditionService.findByID(id));
		redirAttrs.addFlashAttribute("validation", "Term & Condition delete successfully!");
		return "redirect:/admin/account/termandconditions/new";
	}
	
}
