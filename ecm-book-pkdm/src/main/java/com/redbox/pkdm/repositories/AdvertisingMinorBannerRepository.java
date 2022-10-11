package com.redbox.pkdm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.redbox.pkdm.entities.AdvertisingMinorBanner;

public interface AdvertisingMinorBannerRepository extends JpaRepository<AdvertisingMinorBanner, Long> {
	
	@Query(value = "select ad from AdvertisingMinorBanner as ad where ad.erase = :erase ORDER BY ad.id ") 
	List<AdvertisingMinorBanner> findByErase(@Param("erase") Boolean erase);

}
