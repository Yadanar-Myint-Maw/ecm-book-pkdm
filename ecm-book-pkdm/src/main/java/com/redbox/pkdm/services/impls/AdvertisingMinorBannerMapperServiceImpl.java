package com.redbox.pkdm.services.impls;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redbox.pkdm.entities.AdvertisingMajorBannerMapper;
import com.redbox.pkdm.entities.AdvertisingMinorBannerMapper;
import com.redbox.pkdm.repositories.AdvertisingMajorBannerRepository;
import com.redbox.pkdm.repositories.AdvertisingMinorBannerMapperRepository;
import com.redbox.pkdm.repositories.AdvertisingMinorBannerRepository;
import com.redbox.pkdm.services.AdvertisingMinorBannerMapperService;

@Service
public class AdvertisingMinorBannerMapperServiceImpl implements AdvertisingMinorBannerMapperService{
	
	@Autowired
	private AdvertisingMinorBannerMapperRepository advertisingMinorBannerMapperRepository;
	
	@Autowired
	private AdvertisingMinorBannerRepository advertisingMinorBannerRepository;


	@Override
	public void save(AdvertisingMinorBannerMapper advertisingMinorBannerMapper, long bannerId) {
		advertisingMinorBannerMapper.setAdvertisingMinorBanner(advertisingMinorBannerRepository.findById(bannerId).get());
		advertisingMinorBannerMapperRepository.save(advertisingMinorBannerMapper);
		
	}

	@Override
	public void delete(AdvertisingMinorBannerMapper advertisingMinorBannerMapper) {
		advertisingMinorBannerMapper.setErase(true);
		advertisingMinorBannerMapperRepository.save(advertisingMinorBannerMapper);
		
	}

	@Override
	public AdvertisingMinorBannerMapper findByID(long id) {
		return advertisingMinorBannerMapperRepository.findById(id).get();
	}

	@Override
	public List<AdvertisingMinorBannerMapper> findAll() {
		return advertisingMinorBannerMapperRepository.findAll();
	}

	@Override
	public List<AdvertisingMinorBannerMapper> findByErase(boolean erase) {
		
		return advertisingMinorBannerMapperRepository.findByErase(false);
	}

	@Override
	public List<AdvertisingMinorBannerMapper> getAdvMinorBannerListForTodayUse(LocalDate date) {
		return advertisingMinorBannerMapperRepository.getAdvMinorBannerListForTodayUse(date);
	}

	

}
