package com.redbox.pkdm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.redbox.pkdm.entities.AccountAdmin;

public interface AccountAdminRepository extends JpaRepository<AccountAdmin, String> {
	
	@Query(value = "select max(a.id) from AccountAdmin as a")
	String findByLast();
	
	@Query(value = "select count(a) from AccountAdmin as a where a.erase = false")
	long findCountByErase();

	@Query(value = "select a from AccountAdmin as a where a.erase = :erase")
	List<AccountAdmin> findByErase(@Param("erase") Boolean erase);
	
	@Query(value = "select a from AccountAdmin as a where a.name like :name and a.erase = :erase")
	List<AccountAdmin> findByNameAndErase(@Param("name") String name, @Param("erase") Boolean erase);

	@Query(value = "select a from AccountAdmin as a where a.username = :username and a.password = :password and a.erase = false")
	AccountAdmin findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
	
	@Query(value = "select count(a) from AccountAdmin as a where a.erase = false")
	long findCountByAdmin();
	
}
