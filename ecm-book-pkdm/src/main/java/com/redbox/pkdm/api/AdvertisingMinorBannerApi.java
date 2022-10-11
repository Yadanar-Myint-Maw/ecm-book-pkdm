package com.redbox.pkdm.api;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.redbox.pkdm.entities.AdvertisingMinorBanner;
import com.redbox.pkdm.entities.AdvertisingMinorBannerMapper;
import com.redbox.pkdm.services.AdvertisingMinorBannerMapperService;
import com.redbox.pkdm.services.AdvertisingMinorBannerService;

@RestController
@RequestMapping("admin/advertising/api")
public class AdvertisingMinorBannerApi {
	
	@Autowired
	private AdvertisingMinorBannerService advertisingMinorBannerService;
	
	@Autowired
	private AdvertisingMinorBannerMapperService advertisingMinorBannerMapperService;
	
	
	@GetMapping("/advertisingminorbanners")
	public List<AdvertisingMinorBanner> findAll(){
		return advertisingMinorBannerService.findAll();
	}
	
	@GetMapping("/advminorlistfortodayuse")
	public List<AdvertisingMinorBannerMapper> getAdvMinorBannerListForTodayUse(@RequestParam(name = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
		return advertisingMinorBannerMapperService.getAdvMinorBannerListForTodayUse(date);
	}

}
