package com.redbox.pkdm.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redbox.pkdm.entities.RuleAndRegulation;
import com.redbox.pkdm.services.RuleAndRegulationService;

@RestController
@RequestMapping("/api/ruleandregulation/api")
public class RuleAndRegulationAPI {

	@Autowired
	private RuleAndRegulationService ruleAndRegulationService;
	
	@GetMapping("/findall")
	public List<RuleAndRegulation> findAll(){
		return ruleAndRegulationService.findAll();
	}
}
