package com.redbox.pkdm.controllers.admin;

import java.time.LocalDate;
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
import com.redbox.pkdm.entities.PurchasedTransition;
import com.redbox.pkdm.models.OrderModel;
import com.redbox.pkdm.services.AccountAdminService;
import com.redbox.pkdm.services.PurchasedTransitionService;
import com.redbox.pkdm.utilities.MessageUtility;

@Controller
@RequestMapping("admin/order/physical-book/")
public class AdminOrderController {
	
	@Autowired
	private AccountAdminService  accountAdminService;
	
	@Autowired
	private PurchasedTransitionService purchasedTransitionService;
		
	@GetMapping("/initialize/{invoiceNo}")
	public String initialize(@PathVariable String invoiceNo, @CookieValue("login_user_id") String cookieId, Model model, String deliveryStatus, String dateFrom, String dateTo) throws Exception {
		AccountAdmin loginaccount = accountAdminService.findByID(cookieId);
		if (loginaccount == null) {
			throw new Exception();
		}
		
		
		model.addAttribute("loginaccount", loginaccount);
		
		if(invoiceNo.equals("0")) {
			model.addAttribute("purchaseTransition", new PurchasedTransition()); 
		}else {
			model.addAttribute("purchaseTransition", purchasedTransitionService.findByInvoiceNo(invoiceNo).get(0)); 
		}
		
		List<PurchasedTransition> purchasedTransitions = new ArrayList<>();
		
		if(deliveryStatus == null && dateFrom == null && dateTo == null) {
			purchasedTransitions = purchasedTransitionService.findByBookTypeWithGroupByInvoiceNo(true);
		}else if(!deliveryStatus.equals("notselected") && !dateFrom.isEmpty() && !dateTo.isEmpty()) {
			purchasedTransitions = purchasedTransitionService.findByBookTypeAndDeliveryStatusAndDateBetweenWithGroupByInvoiceNo(true,deliveryStatus, LocalDate.parse(dateFrom), LocalDate.parse(dateTo));
		}else if(!deliveryStatus.equals("notselected") && dateFrom.isEmpty() && dateTo.isEmpty()) {
			purchasedTransitions = purchasedTransitionService.findByBookTypeAndDeliveryStatusWithGroupByInvoiceNo(true, deliveryStatus);
		}else if(!deliveryStatus.equals("notselected") && !dateFrom.isEmpty() && dateTo.isEmpty()) {
			purchasedTransitions = purchasedTransitionService.findByBookTypeAndDeliveryStatusAndDateWithGroupByInvoiceNo(true,deliveryStatus, LocalDate.parse(dateFrom));
		}else if(!deliveryStatus.equals("notselected") && dateFrom.isEmpty() && !dateTo.isEmpty()) {
			purchasedTransitions = purchasedTransitionService.findByBookTypeAndDeliveryStatusAndDateWithGroupByInvoiceNo(true,deliveryStatus, LocalDate.parse(dateTo));
		}else if(deliveryStatus.equals("notselected") && !dateFrom.isEmpty() && !dateTo.isEmpty()) {
			purchasedTransitions = purchasedTransitionService.findByBookTypeAndDateBetweenWithGroupByInvoiceNo(true, LocalDate.parse(dateFrom), LocalDate.parse(dateTo));
		}else if(deliveryStatus.equals("notselected") && !dateFrom.isEmpty() && dateTo.isEmpty()) {
			purchasedTransitions = purchasedTransitionService.findByBookTypeAndDateWithGroupByInvoiceNo(true, LocalDate.parse(dateFrom));
		}else if(deliveryStatus.equals("notselected") && dateFrom.isEmpty() && !dateTo.isEmpty()) {
			purchasedTransitions = purchasedTransitionService.findByBookTypeAndDateWithGroupByInvoiceNo(true, LocalDate.parse(dateTo));
		}
		
		List<OrderModel> orderModelList = new ArrayList<>();
		
		for(PurchasedTransition purchasedTransition: purchasedTransitions) {	
			double total = 0;
			List<PurchasedTransition> purchasedTransitions2 = purchasedTransitionService.findByInvoiceNo(purchasedTransition.getInvoice_no());
			for(PurchasedTransition purchasedTransition2 : purchasedTransitions2) {
				total += purchasedTransition2.getTotal();
			}
			
			orderModelList.add(new OrderModel(purchasedTransition.getInvoice_no(), purchasedTransitions2, total));
		}
		
		model.addAttribute("purchaseTransitionsByOrder", orderModelList);	
		
		return "adminorder";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("purchaseTransition") PurchasedTransition purchasedTransition, @CookieValue("login_user_id") String cookieId, Model model, RedirectAttributes redirAttrs) {
		
		List<PurchasedTransition> purchasedTransitions = purchasedTransitionService.findByInvoiceNo(purchasedTransition.getInvoice_no());
		
		for(PurchasedTransition purchasedTransition2 : purchasedTransitions) {
			purchasedTransition2.setDeliveryStatus(purchasedTransition.getDeliveryStatus());
			purchasedTransitionService.save(purchasedTransition2);
		}
		redirAttrs.addFlashAttribute("update", MessageUtility.getUpdateSuccessMessage("Account"));	
		
		return "redirect:/admin/order/physical-book/initialize/0";
	}
}
