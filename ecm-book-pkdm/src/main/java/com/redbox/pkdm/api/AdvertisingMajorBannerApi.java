package com.redbox.pkdm.api;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.redbox.pkdm.entities.AdvertisingMajorBanner;
import com.redbox.pkdm.entities.AdvertisingMajorBannerMapper;
import com.redbox.pkdm.services.AdvertisingMajorBannerMapperService;
import com.redbox.pkdm.services.AdvertisingMajorBannerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/advmjr")
public class AdvertisingMajorBannerApi {
	
	@Autowired
	 private AdvertisingMajorBannerService advertisingMajorBannerService;
	
	@Autowired
	private AdvertisingMajorBannerMapperService advertisingMajorBannerMapperService;
	
	@GetMapping("/banners")
	public List<String> findAll(){
		List<AdvertisingMajorBanner> banners = advertisingMajorBannerService.findAll();
		List<String> images = new ArrayList<>();
		for (AdvertisingMajorBanner banner : banners) {
			images.add(banner.getImage());
		}
		return images;
	}
	
	@GetMapping("/advlistfortodayuse")
	public List<AdvertisingMajorBannerMapper> getAdvMajorBannerListForTodayUse(@RequestParam(name = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
		return advertisingMajorBannerMapperService.getAdvMajorBannerListForTodayUse(date);
	}
	
}
