package com.redbox.pkdm.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.redbox.pkdm.entities.AccountUser;
import com.redbox.pkdm.entities.RuleAndRegulation;
import com.redbox.pkdm.entities.ShelfAuthor;

@Service
public interface RuleAndRegulationService {
	
	void save(RuleAndRegulation ruleAndRegulation);
	
	void delete(RuleAndRegulation ruleAndRegulation);
	
	RuleAndRegulation findByID(Long id);

	List<RuleAndRegulation> findAll();
	
	List<RuleAndRegulation> findByErase(boolean erase);
}
