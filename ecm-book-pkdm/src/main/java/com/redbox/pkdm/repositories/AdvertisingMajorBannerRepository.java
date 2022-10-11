package com.redbox.pkdm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.redbox.pkdm.entities.AdvertisingMajorBanner;


public interface AdvertisingMajorBannerRepository extends JpaRepository<AdvertisingMajorBanner, Long>{
	
	@Query(value = "select a from AdvertisingMajorBanner as a where a.erase = :erase") 
	List<AdvertisingMajorBanner> findByErase(@Param("erase") Boolean erase);
	
	@Query(value = "select count(a) from AdvertisingMajorBanner as a where a.erase = false")
	long findCountByMajorBanner();

}
