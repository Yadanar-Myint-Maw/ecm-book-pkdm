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
import com.redbox.pkdm.entities.RuleAndRegulation;
import com.redbox.pkdm.entities.SecurityInfo;
import com.redbox.pkdm.services.AccountAdminService;
import com.redbox.pkdm.services.RuleAndRegulationService;

@Controller
@RequestMapping("admin/account")
public class RuleAndRegulationController {
	
	@Autowired
	private RuleAndRegulationService ruleAndRegulationService;
	
	@Autowired
	private AccountAdminService accountAdminService;
	
	@GetMapping("/ruleandregulations/{id}")
	public String initialize(@PathVariable String id, @CookieValue("login_user_id") String cookieId, Model model) throws Exception {
		AccountAdmin loginaccount = accountAdminService.findByID(cookieId);
		if (loginaccount == null) {
			throw new Exception();
		}
		model.addAttribute("loginaccount", loginaccount);
		if(id.equals("new")) {
			model.addAttribute("ruleandregulation", new RuleAndRegulation());
		}else {
			model.addAttribute("ruleandregulation", ruleAndRegulationService.findByID(Long.parseLong(id)));
		}
		model.addAttribute("rulenadregulations", ruleAndRegulationService.findByErase(false));
		return "adminruleandregulations";
	}
	
	
	@PostMapping("/ruleandregulations/save")
	public String save(@ModelAttribute("ruleandregulation") RuleAndRegulation ruleAndRegulation, @CookieValue("login_user_id") String cookieId, Model model, RedirectAttributes redirAttrs) {
		if (ruleAndRegulation.getSortNumber() == 0 || ruleAndRegulation.getTitle().isEmpty() || ruleAndRegulation.getDescription().isEmpty()) {
			redirAttrs.addFlashAttribute("validation", "Data is missing!");
		}else {
			if(ruleAndRegulation.getId() == 0) {
				ruleAndRegulation.setSecurityInfo(new SecurityInfo(cookieId));
				ruleAndRegulationService.save(ruleAndRegulation);
				redirAttrs.addFlashAttribute("save", "Rule & Regulation save successfully.");
		}else {
			RuleAndRegulation ruleAndRegulation2 = ruleAndRegulationService.findByID(ruleAndRegulation.getId());
			ruleAndRegulation2.setSortNumber(ruleAndRegulation.getSortNumber());
			ruleAndRegulation2.setTitle(ruleAndRegulation.getTitle());
			ruleAndRegulation2.setDescription(ruleAndRegulation.getDescription());
			ruleAndRegulation2.getSecurityInfo().setUpdateDate(LocalDate.now());
			ruleAndRegulation2.getSecurityInfo().setUpdateTime(LocalTime.now().toString());
			ruleAndRegulation2.getSecurityInfo().setUpdateUser(cookieId);
			ruleAndRegulationService.save(ruleAndRegulation2);
			redirAttrs.addFlashAttribute("save", "Rule & Regulation update successfully.");
			}
		}
		return "redirect:/admin/account/ruleandregulations/new";	
	}
	
	@GetMapping("/ruleandregulations/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes redirAttrs) {
		ruleAndRegulationService.delete(ruleAndRegulationService.findByID(id));
		redirAttrs.addFlashAttribute("validation", "Rule & Regulation delete successfully!");
		return "redirect:/admin/account/ruleandregulations/new";
	}
}
