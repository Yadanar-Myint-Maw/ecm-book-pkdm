package com.redbox.pkdm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.redbox.pkdm.entities.DeliveryRegion;
import com.redbox.pkdm.entities.RuleAndRegulation;
import com.redbox.pkdm.entities.ShelfAuthor;

public interface RuleAndRegulationRepository extends JpaRepository<RuleAndRegulation, Long>{

	@Query(value = "select r from RuleAndRegulation as r where r.erase = :erase ORDER BY r.id DESC") 
	List<RuleAndRegulation> findByErase(@Param("erase") Boolean erase);
}
