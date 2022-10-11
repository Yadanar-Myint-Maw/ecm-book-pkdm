package com.redbox.pkdm.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redbox.pkdm.entities.ContactInfo;
import com.redbox.pkdm.repositories.ContactInfoRepository;
import com.redbox.pkdm.services.ContactInfoService;

@Service
public class ContactInfoServiceImpl implements ContactInfoService{
	
	@Autowired
	private ContactInfoRepository repository;

	@Override
	public void save(ContactInfo contactInfo) {
		repository.save(contactInfo);		
	}

	@Override
	public ContactInfo findById(long id) {
		return repository.findById(id).get();
	}

	
	
	
	

}
