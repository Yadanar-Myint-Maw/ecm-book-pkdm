package com.redbox.pkdm.services;

import java.time.LocalDate;
import java.util.List;


import com.redbox.pkdm.entities.AdvertisingMinorBanner;
import com.redbox.pkdm.entities.AdvertisingMinorBannerMapper;

public interface AdvertisingMinorBannerService {
	
	void save(AdvertisingMinorBanner advertisingMinorBanner);
	
	void delete(AdvertisingMinorBanner AdvertisingMinorBanner);
	
	AdvertisingMinorBanner findByID(long id);
	
	List<AdvertisingMinorBanner> findAll();
	
	List<AdvertisingMinorBanner> findByErase(boolean erase);
	
	long findCountByMinorBanner();

}
