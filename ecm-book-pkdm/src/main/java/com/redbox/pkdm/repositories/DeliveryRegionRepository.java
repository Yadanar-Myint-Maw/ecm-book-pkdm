package com.redbox.pkdm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.redbox.pkdm.entities.AccountAdmin;
import com.redbox.pkdm.entities.DeliveryRegion;

public interface DeliveryRegionRepository extends JpaRepository<DeliveryRegion, Long> {
	
	@Query(value = "select d from DeliveryRegion as d where d.erase = :erase ORDER BY d.id DESC") 
	List<DeliveryRegion> findByErase(@Param("erase") Boolean erase);
	
	@Query(value = "select n from DeliveryRegion as n where n.name = :name ORDER BY n.name")
	List<DeliveryRegion> findNameByAlphabetically(@Param("name")String name);

	@Query(value = "select de from DeliveryRegion as de where de.name like :name and de.erase = :erase")
	List<DeliveryRegion> findByNameAndErase(@Param("name") String name, @Param("erase") Boolean erase);
}
