package com.redbox.pkdm.services;


import java.util.List;

import com.redbox.pkdm.entities.DeliveryTownship;
import com.redbox.pkdm.models.DeliveryTownshipModel;

public interface DeliveryTownshipService {
	
	void save(DeliveryTownship deliveryTownship, long region_id);
	
	void delete(DeliveryTownship deliveryTownship);
	
	DeliveryTownship findByID(long id);
	
	List<DeliveryTownship> findAll();
	
	List<DeliveryTownship> findByErase(boolean erase);
	
	List<DeliveryTownshipModel> findAllDeliveryTownShipModel();
	
	List<DeliveryTownship> findByNameAndErase(String name, boolean erase);
	
	long findCountByDeliveryTownship();

}
