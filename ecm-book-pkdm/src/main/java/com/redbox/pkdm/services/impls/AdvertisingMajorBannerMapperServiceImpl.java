package com.redbox.pkdm.services.impls;

import java.time.LocalDate;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.redbox.pkdm.entities.AdvertisingMajorBannerMapper;
import com.redbox.pkdm.models.AdvertisingMajorBannerMapperModel;
import com.redbox.pkdm.repositories.AdvertisingMajorBannerMapperRepository;
import com.redbox.pkdm.repositories.AdvertisingMajorBannerRepository;
import com.redbox.pkdm.services.AdvertisingMajorBannerMapperService;
import com.redbox.pkdm.services.AdvertisingMajorBannerService;

@Service
public class AdvertisingMajorBannerMapperServiceImpl implements AdvertisingMajorBannerMapperService{
	
	@Autowired
	private AdvertisingMajorBannerMapperRepository advertisingMajorBannerMapperRepository;
	
	@Autowired
	private AdvertisingMajorBannerRepository advertisingMajorBannerRepository;

	@Override
	public void save(AdvertisingMajorBannerMapper advertisingMajorBannerMapper, long bannerId) {
		
		advertisingMajorBannerMapper.setAdvertisingMajorBanner(advertisingMajorBannerRepository.findById(bannerId).get());
		advertisingMajorBannerMapperRepository.save(advertisingMajorBannerMapper);
		
	}

	@Override
	public void delete(AdvertisingMajorBannerMapper advertisingMajorBannerMapper) {
		advertisingMajorBannerMapper.setErase(true);
		advertisingMajorBannerMapperRepository.save(advertisingMajorBannerMapper);
		
	}

	@Override
	public AdvertisingMajorBannerMapper findByID(long id) {
		return advertisingMajorBannerMapperRepository.findById(id).get();
	}

	@Override
	public List<AdvertisingMajorBannerMapper> findAll() {
		return advertisingMajorBannerMapperRepository.findAll();
	}

	@Override
	public List<AdvertisingMajorBannerMapper> findByErase(boolean erase) {
		return advertisingMajorBannerMapperRepository.findByErase(false);
	}

	@Override
	public List<AdvertisingMajorBannerMapper> getAdvMajorBannerListForTodayUse(LocalDate date) {
		return advertisingMajorBannerMapperRepository.getAdvMajorBannerListForTodayUse(date);
	}

	
}
