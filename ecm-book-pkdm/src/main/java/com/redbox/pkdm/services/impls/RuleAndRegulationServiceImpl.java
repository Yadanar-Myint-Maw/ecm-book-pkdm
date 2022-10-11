package com.redbox.pkdm.services.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redbox.pkdm.entities.AccountUser;
import com.redbox.pkdm.entities.RuleAndRegulation;
import com.redbox.pkdm.repositories.RuleAndRegulationRepository;
import com.redbox.pkdm.services.RuleAndRegulationService;

@Service
public class RuleAndRegulationServiceImpl implements RuleAndRegulationService{
	
	@Autowired
	private RuleAndRegulationRepository repository;

	@Override
	public void save(RuleAndRegulation ruleAndRegulation) {
		repository.save(ruleAndRegulation);
		
	}

	@Override
	public void delete(RuleAndRegulation ruleAndRegulation) {
		ruleAndRegulation.setErase(true);
		repository.save(ruleAndRegulation);
		
	}

	@Override
	public RuleAndRegulation findByID(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public List<RuleAndRegulation> findAll() {
		return repository.findAll();
	}

	@Override
	public List<RuleAndRegulation> findByErase(boolean erase) {
		return repository.findByErase(erase);
	}

}
