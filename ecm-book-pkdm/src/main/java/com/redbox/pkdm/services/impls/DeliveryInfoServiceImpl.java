package com.redbox.pkdm.services.impls;

import com.redbox.pkdm.entities.DeliveryInfo;
import com.redbox.pkdm.repositories.DeliveryInfoRepository;
import com.redbox.pkdm.services.DeliveryInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryInfoServiceImpl implements DeliveryInfoService {
	
	@Autowired
	private DeliveryInfoRepository deliveryInfoRepository;
	
	public void save (DeliveryInfo deliveryInfo) {
		deliveryInfoRepository.save(deliveryInfo);
	}

}
