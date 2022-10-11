package com.redbox.pkdm.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redbox.pkdm.entities.DeliveryRegion;
import com.redbox.pkdm.services.DeliveryRegionService;

@RestController
@RequestMapping("admin/regionApi")
public class DeliveryRegionApi {
	
	
	@Autowired
	private DeliveryRegionService deliveryRegionService;
	
	List<DeliveryRegion> getAllDeliveryRegion(){
		return deliveryRegionService.findAll();
	}

}
