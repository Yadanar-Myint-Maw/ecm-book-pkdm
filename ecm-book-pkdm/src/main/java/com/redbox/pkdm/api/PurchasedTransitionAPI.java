package com.redbox.pkdm.api;

import java.util.ArrayList;
import java.util.List;

import com.redbox.pkdm.entities.PurchasedTransition;
import com.redbox.pkdm.models.PurchasedTransitionModel;
import com.redbox.pkdm.services.PurchasedTransitionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/purchasedtransition")
public class PurchasedTransitionAPI {
	
	@Autowired
	private PurchasedTransitionService purchasedTransitionService;
	
	@GetMapping("/purchased/{userID}")
	public List<PurchasedTransitionModel> purchasebooksection (@PathVariable String userID) {
		try {
			PurchasedTransitionModel model = new PurchasedTransitionModel();
			List<PurchasedTransitionModel> models = new ArrayList<>();
			List<PurchasedTransition> transitions = purchasedTransitionService.findByUser(userID);
			for (PurchasedTransition transition : transitions) {
				model.setId(transition.getId());
				model.setBookID(transition.getBook().getId());
				model.setUserID(transition.getAccountUser().getId());
				model.setSectionID(transition.getBookType());
				models.add(model);
				model = new PurchasedTransitionModel();
			}
			return models;
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}

}
