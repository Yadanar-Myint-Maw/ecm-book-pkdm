package com.redbox.pkdm.services;

import com.redbox.pkdm.entities.ContactInfo;

public interface ContactInfoService {
	
	void save(ContactInfo contactInfo);
	
	ContactInfo findById(long id);
	


}
