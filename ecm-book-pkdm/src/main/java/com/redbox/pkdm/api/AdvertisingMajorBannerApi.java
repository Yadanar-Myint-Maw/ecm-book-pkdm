package com.redbox.pkdm.api;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.redbox.pkdm.entities.AdvertisingMajorBanner;
import com.redbox.pkdm.entities.AdvertisingMajorBannerMapper;
import com.redbox.pkdm.models.AdvertisingMajorBannerMapperModel;
import com.redbox.pkdm.models.DeliveryTownshipModel;
import com.redbox.pkdm.services.AdvertisingMajorBannerMapperService;
import com.redbox.pkdm.services.AdvertisingMajorBannerService;

@RestController
@RequestMapping("admin/advertising/api")
public class AdvertisingMajorBannerApi {
	
	@Autowired
	 private AdvertisingMajorBannerService advertisingMajorBannerService;
	
	@Autowired
	private AdvertisingMajorBannerMapperService advertisingMajorBannerMapperService;
	
	@GetMapping("/advertisingmajorbanners")
	public List<AdvertisingMajorBanner> findAll(){
		return advertisingMajorBannerService.findAll();
	}
	
	@GetMapping("/advlistfortodayuse")
	public List<AdvertisingMajorBannerMapper> getAdvMajorBannerListForTodayUse(@RequestParam(name = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
		return advertisingMajorBannerMapperService.getAdvMajorBannerListForTodayUse(date);
	}
	
}
