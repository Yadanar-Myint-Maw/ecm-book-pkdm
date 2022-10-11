package com.redbox.pkdm.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.redbox.pkdm.entities.AdvertisingMajorBannerMapper;
import com.redbox.pkdm.entities.AdvertisingMinorBannerMapper;
import com.redbox.pkdm.entities.DiscountCoupon;

public interface AdvertisingMinorBannerMapperRepository extends JpaRepository<AdvertisingMinorBannerMapper, Long>{
	
	@Query(value = "select adv from AdvertisingMinorBannerMapper as adv where adv.erase = :erase ORDER BY adv.id ") 
	List<AdvertisingMinorBannerMapper> findByErase(@Param("erase") Boolean erase);
	
	@Query("select adv from AdvertisingMinorBannerMapper as adv where :date between adv.startDate and adv.endDate")
	List<AdvertisingMinorBannerMapper> getAdvMinorBannerListForTodayUse(LocalDate date);

}
