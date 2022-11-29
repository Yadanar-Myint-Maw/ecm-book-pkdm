package com.redbox.pkdm.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redbox.pkdm.entities.TermAndCondition;
import com.redbox.pkdm.services.TermAndConditionService;

@RestController
@RequestMapping("/api/termandcondition")
public class TermAndConditionAPI {
	
	@Autowired
	private TermAndConditionService termAndConditionService;
	
	@GetMapping("/findall")
	public List<TermAndCondition> findAll(){
		return termAndConditionService.findAll();
	}
}
