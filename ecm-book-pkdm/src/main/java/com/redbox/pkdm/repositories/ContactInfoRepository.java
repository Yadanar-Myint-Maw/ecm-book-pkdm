package com.redbox.pkdm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.redbox.pkdm.entities.ContactInfo;

public interface ContactInfoRepository extends JpaRepository<ContactInfo, Long>{
	
}
