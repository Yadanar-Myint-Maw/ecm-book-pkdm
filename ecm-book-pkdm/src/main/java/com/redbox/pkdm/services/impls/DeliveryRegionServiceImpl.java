package com.redbox.pkdm.services.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redbox.pkdm.entities.DeliveryRegion;
import com.redbox.pkdm.repositories.DeliveryRegionRepository;
import com.redbox.pkdm.services.DeliveryRegionService;


@Service
public class DeliveryRegionServiceImpl implements DeliveryRegionService{
	
	@Autowired
	private DeliveryRegionRepository deliRepository;

	@Override
	public void save(DeliveryRegion deliveryRegion) {
		deliRepository.save(deliveryRegion);	
	}
		
	@Override
	public void delete(DeliveryRegion deliveryRegion) {
		deliveryRegion.setErase(true);
		deliRepository.save(deliveryRegion);
		
	}

	@Override
	public List<DeliveryRegion> findAll() {
		return deliRepository.findAll();
	}

	@Override
	public List<DeliveryRegion> findByErase(boolean erase) {
		return deliRepository.findByErase(false);
	}

	@Override
	public DeliveryRegion findByID(long id) {
		return deliRepository.findById(id).get();
	}

	@Override
	public List<DeliveryRegion> findByNameAndErase(String name, boolean erase) {
		return deliRepository.findByNameAndErase("%"+name+"%", erase);
	}	

}
