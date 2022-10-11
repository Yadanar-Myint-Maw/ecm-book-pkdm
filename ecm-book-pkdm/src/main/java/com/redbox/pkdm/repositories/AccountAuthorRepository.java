package com.redbox.pkdm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.redbox.pkdm.entities.AccountAdmin;
import com.redbox.pkdm.entities.AccountAuthor;

public interface AccountAuthorRepository extends JpaRepository<AccountAuthor, String>{
	
	@Query(value = "select max(a.id) from AccountAuthor as a")
	String findByLast();
	

	@Query(value = "select a from AccountAuthor as a where a.erase = :erase") 
	List<AccountAuthor> findByErase(@Param("erase") Boolean erase);
	
	@Query(value = "select a from AccountAuthor as a where a.username = :username and a.password = :password and a.erase = false")
	AccountAuthor findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

	@Query(value = "select count(a) from AccountAuthor as a where a.erase = false")
	long findCountByAuthor();

}
