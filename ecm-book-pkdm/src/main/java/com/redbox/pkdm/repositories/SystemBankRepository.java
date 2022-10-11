package com.redbox.pkdm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.redbox.pkdm.entities.ShelfAuthor;
import com.redbox.pkdm.entities.SystemBank;

public interface SystemBankRepository extends JpaRepository<SystemBank, Long>{
	@Query(value = "select max(b.id) from AccountUser as b")
	String findByLast();

	@Query(value = "select b from SystemBank as b where b.erase = :erase ORDER BY b.id DESC")
	List<SystemBank> findByErase(@Param("erase") boolean erase);
	
	@Query(value = "select b from SystemBank as b where b.name = :name and b.erase = :erase")
	List<SystemBank> findByNameAndErase(@Param("name") String name,@Param("erase") boolean erase);
	
	@Query(value = "select b from SystemBank as b where b.name LIKE %:name% and b.erase = false")
	List<SystemBank> findByNameLike(@Param("name") String name);

	@Query(value = "select b from ShelfAuthor as b where b.erase = :erase ORDER BY b.id DESC")
	List<SystemBank> findByEraseAndOrderId(@Param("erase") boolean erase);
}
