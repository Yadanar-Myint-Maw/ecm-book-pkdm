package com.redbox.pkdm.repositories;

import java.time.LocalDate;
import java.util.List;

import com.redbox.pkdm.entities.AccountUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface AccountUserRepository extends JpaRepository<AccountUser, String>{

	@Query(value = "select max(u.id) from AccountUser as u")
	String findByLast();

	@Query(value = "select u from AccountUser as u where u.erase = :erase")
	List<AccountUser> findByErase(@Param("erase") boolean erase);

	@Query(value = "select u from AccountUser as u where u.name = :name and u.erase = :erase")
	List<AccountUser> findByNameAndErase(@Param("name") String name,@Param("erase") boolean erase);

	@Query(value = "select u from AccountUser as u where u.email = :email and u.password = :password and u.erase = false")
	AccountUser findByEmailAndPassword(@Param("email") String email,@Param("password") String password);

	@Query(value = "select u from AccountUser as u where u.phone = :phone")
	AccountUser findByPhone(@Param("phone") String phone);
	
	@Query(value = "select count(u) from AccountUser as u where u.phone = :phone")
	long findCountByPhone(@Param("phone") String phone);
	
	@Query(value = "select count(u) from AccountUser as u where u.email = :email")
	long findCountByEmail(@Param("email") String email);

	@Query(value = "select u from AccountUser as u where u.phone = :phone and u.password = :password")
	AccountUser findByPhoneAndPassword(@Param("phone")String phone,@Param("password") String password);

	@Query(value = "select u from AccountUser as u where u.name = :name and u.email = :email and u.password = :password and u.phone = :phone and u.gender = :gender and u.dob = :dob")
	AccountUser saveAccountUser(@Param("name") String name,@Param("email") String email,@Param("password") String password,@Param("phone") String phone,@Param("gender") String gender,@Param("dob") LocalDate dob);

	@Query(value = "select u from AccountUser as u where u.id = :id and u.name = :name and u.email = :email and u.password = :password and u.phone = :phone and u.gender = :gender and u.dob = :dob")
	AccountUser updateAccountUser(@Param("id")String id,@Param("name") String name,@Param("email") String email,@Param("password") String password,@Param("phone") String phone, @Param("gender") String gender, @Param("dob") LocalDate dob);
	
	@Query(value = "select count(u) from AccountUser as u where u.level = :level and u.erase = false")
	long findByCountUser(@Param("level")String level);
	
	@Query(value = "select count(u) from AccountUser as u where u.level = :level and u.erase = false")
	long findByCountUserLevel2(@Param("level")String level);

}
