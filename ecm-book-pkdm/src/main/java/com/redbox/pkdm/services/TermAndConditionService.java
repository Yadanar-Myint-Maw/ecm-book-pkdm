package com.redbox.pkdm.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.redbox.pkdm.entities.RuleAndRegulation;
import com.redbox.pkdm.entities.TermAndCondition;

@Service
public interface TermAndConditionService {
	
	void save(TermAndCondition termAndCondition);
	
	void delete(TermAndCondition termAndCondition);

	TermAndCondition findByID(Long id);

	List<TermAndCondition> findAll();
	
	List<TermAndCondition> findByErase(boolean erase);

}
