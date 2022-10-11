package com.redbox.pkdm.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.redbox.pkdm.entities.AdvertisingMajorBannerMapper;
import com.redbox.pkdm.models.AdvertisingMajorBannerMapperModel;

public interface AdvertisingMajorBannerMapperRepository extends JpaRepository<AdvertisingMajorBannerMapper, Long>{
	
	@Query(value = "select adv from AdvertisingMajorBannerMapper as adv where adv.erase = :erase ORDER BY adv.id ") 
	List<AdvertisingMajorBannerMapper> findByErase(@Param("erase") Boolean erase);

	@Query("select adv from AdvertisingMajorBannerMapper as adv where :date between adv.startDate and adv.endDate")
	List<AdvertisingMajorBannerMapper> getAdvMajorBannerListForTodayUse(LocalDate date);
}
