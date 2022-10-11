package com.redbox.pkdm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.redbox.pkdm.entities.RuleAndRegulation;
import com.redbox.pkdm.entities.TermAndCondition;

public interface TermAndConditionRepository extends JpaRepository<TermAndCondition, Long>{

	@Query(value = "select t from TermAndCondition as t where t.erase = :erase ORDER BY t.sortNumber") 
	List<TermAndCondition> findByErase(@Param("erase") Boolean erase);
	
}
