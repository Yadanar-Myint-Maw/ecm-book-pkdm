package com.redbox.pkdm.services.impls;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redbox.pkdm.entities.AdvertisingMajorBanner;
import com.redbox.pkdm.entities.AdvertisingMinorBanner;
import com.redbox.pkdm.entities.AdvertisingMinorBannerMapper;
import com.redbox.pkdm.repositories.AdvertisingMajorBannerRepository;
import com.redbox.pkdm.repositories.AdvertisingMinorBannerMapperRepository;
import com.redbox.pkdm.repositories.AdvertisingMinorBannerRepository;
import com.redbox.pkdm.services.AdvertisingMinorBannerService;

@Service
public class AdvertisingMinorBannerServiceImpl implements AdvertisingMinorBannerService{
	
	@Autowired
	private AdvertisingMinorBannerRepository advertisingMinorBannerRepository;

	@Override
	public void save(AdvertisingMinorBanner advertisingMajorBanner) {
		advertisingMinorBannerRepository.save(advertisingMajorBanner);
		
	}

	@Override
	public void delete(AdvertisingMinorBanner advertisingMajorBanner) {
		advertisingMajorBanner.setErase(true);
		advertisingMinorBannerRepository.save(advertisingMajorBanner);
		
	}

	@Override
	public AdvertisingMinorBanner findByID(long id) {
		return advertisingMinorBannerRepository.findById(id).get();
	}

	@Override
	public List<AdvertisingMinorBanner> findAll() {
		return advertisingMinorBannerRepository.findAll();
	}

	@Override
	public List<AdvertisingMinorBanner> findByErase(boolean erase) {
		return advertisingMinorBannerRepository.findByErase(false);
	}

}
