package com.redbox.pkdm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.redbox.pkdm.entities.DeliveryRegion;
import com.redbox.pkdm.entities.DeliveryTownship;

public interface DeliveryTownshipRepository extends JpaRepository<DeliveryTownship, Long> {
	
	@Query(value = "select t from DeliveryTownship as t where t.erase = :erase ORDER BY t.id DESC") 
	List<DeliveryTownship> findByErase(@Param("erase") Boolean erase);
	
	@Query(value = "select dt from DeliveryTownship as dt where dt.name like :name and dt.erase = :erase")
	List<DeliveryTownship> findByNameAndErase(@Param("name") String name, @Param("erase") Boolean erase);
	
	@Query(value = "select count(dt) from DeliveryTownship as dt where dt.erase = false")
	long findCountByDeliveryTownship();
	
}
