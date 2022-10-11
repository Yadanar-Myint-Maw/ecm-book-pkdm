package com.redbox.pkdm.services.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redbox.pkdm.entities.AdvertisingMajorBanner;
import com.redbox.pkdm.repositories.AdvertisingMajorBannerRepository;
import com.redbox.pkdm.services.AdvertisingMajorBannerService;

@Service
public class AdvertisingMajorBannerServiceImpl implements AdvertisingMajorBannerService{
	
	@Autowired
	private AdvertisingMajorBannerRepository advertisingMajorBannerRepository;
	

	@Override
	public void save(AdvertisingMajorBanner advertisingMajorBanner) {
		advertisingMajorBannerRepository.save(advertisingMajorBanner);
		
	}

	@Override
	public void delete(AdvertisingMajorBanner advertisingMajorBanner) {
		advertisingMajorBanner.setErase(true);
		advertisingMajorBannerRepository.save(advertisingMajorBanner);
		
	}

	@Override
	public AdvertisingMajorBanner findByID(long id) {
		return advertisingMajorBannerRepository.findById(id).get();
	}

	@Override
	public List<AdvertisingMajorBanner> findAll() {
		return advertisingMajorBannerRepository.findAll();
	}

	@Override
	public List<AdvertisingMajorBanner> findByErase(boolean erase) {
		return advertisingMajorBannerRepository.findByErase(false);
	}

	@Override
	public long findCountByMajorBanner() {
		return advertisingMajorBannerRepository.findCountByMajorBanner();
	}
	
	

}
