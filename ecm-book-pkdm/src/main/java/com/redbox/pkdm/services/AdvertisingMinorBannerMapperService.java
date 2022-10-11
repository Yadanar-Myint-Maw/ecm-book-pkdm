package com.redbox.pkdm.services;

import java.time.LocalDate;
import java.util.List;


import com.redbox.pkdm.entities.AdvertisingMinorBannerMapper;

public interface AdvertisingMinorBannerMapperService {
	
	void save(AdvertisingMinorBannerMapper advertisingMinorBannerMapper,long bannerId);
	
	void delete(AdvertisingMinorBannerMapper advertisingMinorBannerMapper);
	
	AdvertisingMinorBannerMapper findByID(long id);
	
	List<AdvertisingMinorBannerMapper> findAll();
	
	List<AdvertisingMinorBannerMapper> findByErase(boolean erase);

	List<AdvertisingMinorBannerMapper> getAdvMinorBannerListForTodayUse(LocalDate date);


}
