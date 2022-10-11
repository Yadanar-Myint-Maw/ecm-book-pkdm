package com.redbox.pkdm.services;


import java.util.List;

import com.redbox.pkdm.entities.DeliveryRegion;

public interface DeliveryRegionService {
	
	
	void save(DeliveryRegion deliveryRegion);
	
	void delete(DeliveryRegion deliveryRegion);
	
	DeliveryRegion findByID(long id);
	
	List<DeliveryRegion> findAll();
	
	List<DeliveryRegion> findByErase(boolean erase);
	
	List<DeliveryRegion> findByNameAndErase(String name, boolean erase);
	
	


}
