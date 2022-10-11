package com.redbox.pkdm.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.redbox.pkdm.entities.ContactInfo;
import com.redbox.pkdm.services.ContactInfoService;

@RestController
@RequestMapping("/admin/contact/api")
public class ContactApi {
	
	@Autowired
	private ContactInfoService contactInfoService;
	
	@GetMapping
	ContactInfo getAllContactInfos(){
		return contactInfoService.findById(1);
	}

}
