package com.redbox.pkdm.services.impls;

import java.util.List;

import com.redbox.pkdm.entities.PurchasedTransition;
import com.redbox.pkdm.repositories.PurchasedTransitionRepository;
import com.redbox.pkdm.services.PurchasedTransitionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchasedTransitionServiceImpl implements PurchasedTransitionService {

	@Autowired
	private PurchasedTransitionRepository repository;
	
	@Override
	public void save(PurchasedTransition purchasedTransition) {
		repository.save(purchasedTransition);
	}

	@Override
	public void delete(PurchasedTransition purchasedTransition) {
		repository.delete(purchasedTransition);
	}

	@Override
	public PurchasedTransition findByID(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public List<PurchasedTransition> findAll() {
		return repository.findAll();
	}

	@Override
	public List<PurchasedTransition> findByUser(String id) {
		return repository.findByUser(id);
	}

	@Override
	public List<PurchasedTransition> findByBookType(String bookType) {
		return repository.findByBookType(bookType);
	}

}
