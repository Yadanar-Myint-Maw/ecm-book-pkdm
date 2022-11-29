package com.redbox.pkdm.api;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redbox.pkdm.models.DeliveryTownshipModel;
import com.redbox.pkdm.services.DeliveryTownshipService;

@RestController
@RequestMapping("api/township")
public class DeliveryTownshipApi {
	
	@Autowired
	private DeliveryTownshipService deliveryTownshipService;
	
	@GetMapping("/findall")
	List<DeliveryTownshipModel> findAll(){
		return deliveryTownshipService.findAllDeliveryTownShipModel();
		
	}
	
	
	
	

}
