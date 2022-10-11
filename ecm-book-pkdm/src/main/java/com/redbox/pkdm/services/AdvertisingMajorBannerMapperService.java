package com.redbox.pkdm.services;

import java.time.LocalDate;
import java.util.List;


import com.redbox.pkdm.entities.AdvertisingMajorBannerMapper;
import com.redbox.pkdm.models.AdvertisingMajorBannerMapperModel;

public interface AdvertisingMajorBannerMapperService {
	
	void save(AdvertisingMajorBannerMapper advertisingMajorBannerMapper, long bannerId);
	
	void delete(AdvertisingMajorBannerMapper advertisingMajorBannerMapper);
	
	AdvertisingMajorBannerMapper findByID(long id);
	
	List<AdvertisingMajorBannerMapper> findAll();
	
	List<AdvertisingMajorBannerMapper> findByErase(boolean erase);

	List<AdvertisingMajorBannerMapper> getAdvMajorBannerListForTodayUse(LocalDate date);
}
