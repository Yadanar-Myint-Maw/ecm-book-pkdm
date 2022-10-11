package com.redbox.pkdm.services.impls;


import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redbox.pkdm.entities.TermAndCondition;
import com.redbox.pkdm.repositories.TermAndConditionRepository;
import com.redbox.pkdm.services.TermAndConditionService;

@Service
public class TermAndConditionServiceImpl implements TermAndConditionService{
	
	@Autowired
	private TermAndConditionRepository repository;

	@Override
	public void save(TermAndCondition termAndCondition) {
		repository.save(termAndCondition);
		
	}

	@Override
	public void delete(TermAndCondition termAndCondition) {
		termAndCondition.setErase(true);
		repository.save(termAndCondition);
	}

	@Override
	public TermAndCondition findByID(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public List<TermAndCondition> findAll() {
		return repository.findAll();
	}

	@Override
	public List<TermAndCondition> findByErase(boolean erase) {
		return repository.findByErase(erase).stream().sorted(Comparator.comparing(TermAndCondition::getSortNumber)).collect(Collectors.toList());
	}
	
}
