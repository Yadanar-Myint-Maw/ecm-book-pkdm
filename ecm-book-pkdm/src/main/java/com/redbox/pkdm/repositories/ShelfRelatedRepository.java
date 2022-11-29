package com.redbox.pkdm.repositories;

import java.util.List;

import com.redbox.pkdm.entities.ShelfRelated;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ShelfRelatedRepository extends JpaRepository<ShelfRelated, Long>  {
	
	@Query(value = "select s from ShelfRelated as s where s.erase = :erase")
	List<ShelfRelated> findByErase(@Param("erase") Boolean erase);
	
	@Query(value = "select s from ShelfRelated as s where s.name = :name and s.erase = :erase")
	List<ShelfRelated> findByNameAndErase(@Param("name") String name, @Param("erase") Boolean erase);

}
