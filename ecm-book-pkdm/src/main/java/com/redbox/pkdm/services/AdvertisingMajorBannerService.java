package com.redbox.pkdm.services;

import java.util.List;

import com.redbox.pkdm.entities.AdvertisingMajorBanner;


public interface AdvertisingMajorBannerService {
	
	void save(AdvertisingMajorBanner advertisingMajorBanner);
	
	void delete(AdvertisingMajorBanner advertisingMajorBanner);
	
	AdvertisingMajorBanner findByID(long id);
	
	List<AdvertisingMajorBanner> findAll();
	
	List<AdvertisingMajorBanner> findByErase(boolean erase);
	
	long findCountByMajorBanner();
}
