package com.redbox.pkdm.services.impls;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.redbox.pkdm.entities.DeliveryTownship;
import com.redbox.pkdm.models.DeliveryTownshipModel;
import com.redbox.pkdm.repositories.DeliveryTownshipRepository;
import com.redbox.pkdm.services.DeliveryRegionService;
import com.redbox.pkdm.services.DeliveryTownshipService;

@Service
public class DeliveryTownshipServiceImpl implements DeliveryTownshipService{
	
	@Autowired
	private DeliveryTownshipRepository townshipRepo;
	
	@Autowired
	private DeliveryRegionService deliveryRegionService;
	
	

	@Override
	public void save(DeliveryTownship deliveryTownship, long region_id) {
		deliveryTownship.setDeliveryRegion(deliveryRegionService.findByID(region_id));
		townshipRepo.save(deliveryTownship);
		
	}

	@Override
	public void delete(DeliveryTownship deliveryTownship) {
		deliveryTownship.setErase(true);
		townshipRepo.save(deliveryTownship);
		
	}

	@Override
	public DeliveryTownship findByID(long id) {
		return townshipRepo.findById(id).get();
	}

	@Override
	public List<DeliveryTownship> findAll() {
		
		return townshipRepo.findAll();
	}
	
	
	@Override
	public List<DeliveryTownship> findByErase(boolean erase) {
		
		return townshipRepo.findByErase(false);
	}
	
	public DeliveryTownshipModel toModel(DeliveryTownship township) {
		return new DeliveryTownshipModel(
				township.getId(), 
				township.getDeliveryRegion().getName(), 
				township.getName(),
				township.getFee(),
				township.isErase());
	}
	
	@Override
	public List<DeliveryTownshipModel> findAllDeliveryTownShipModel(){
		return townshipRepo.findByErase(false).stream()
				.map(township -> toModel(township))
				.collect(Collectors.toList());
	}

	@Override
	public List<DeliveryTownship> findByNameAndErase(String name, boolean erase) {
		return townshipRepo.findByNameAndErase("%"+name+"%", erase);
	}
	
	

}
