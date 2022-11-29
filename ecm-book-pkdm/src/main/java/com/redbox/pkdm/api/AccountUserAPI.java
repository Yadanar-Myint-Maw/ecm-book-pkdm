package com.redbox.pkdm.api;

import java.time.LocalDate;

import com.redbox.pkdm.entities.AccountUser;
import com.redbox.pkdm.entities.SecurityInfo;
import com.redbox.pkdm.models.AccountUserModel;
import com.redbox.pkdm.services.AccountUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/accountuser")
public class AccountUserAPI {
	
	@Autowired
	private AccountUserService accountUserService;
	
	@GetMapping("/checkbyphone/{phone}")
	public boolean checkByPhone (@PathVariable String phone) {
		return accountUserService.checkByPhone(phone);
	}
	
	@GetMapping("/checkbyemail/{email}")
	public boolean checkByEmail (@PathVariable String email) {
		return accountUserService.checkByPhone(email);
	}
	
	@GetMapping("/findbyid/{id}")
	public AccountUserModel findById(@PathVariable String id) {
		try {
			AccountUser user = accountUserService.findByID(id);
			return new AccountUserModel(
					user.getId(), user.getImage(), user.getName(), 
					user.getAge(), user.getGender(), user.getDob().toString(), 
					user.getPhone(), user.getEmail(), user.getPassword(), 
					user.getLevel(), user.isErase());
		} catch (Exception e) {
			return new AccountUserModel();
		}
		 
	}
	
	@GetMapping("/findbyphoneandpassword/{phone}/{password}")
	public AccountUserModel findByPhoneAndPassword(@PathVariable String phone, @PathVariable String password) {
		try {
			AccountUser user = accountUserService.findByPhoneAndPassword(phone, password);
			return new AccountUserModel( 
					user.getId(), user.getImage(), user.getName(), 
					user.getAge(), user.getGender(), user.getDob().toString(), 
					user.getPhone(), user.getEmail(), user.getPassword(), 
					user.getLevel(), user.isErase());
		} catch (Exception e) {
			e.printStackTrace();
			return new AccountUserModel();
		}
	}
	
	@GetMapping("/findbyemailandpassword/{email}/{password}")
	public AccountUserModel findByEmailAndPassword(@PathVariable String email, @PathVariable String password) {
		try {
			AccountUser user = accountUserService.findByEmailAndPassword(email, password);
			return new AccountUserModel(
					user.getId(), user.getImage(), user.getName(), 
					user.getAge(), user.getGender(), user.getDob().toString(), 
					user.getPhone(), user.getEmail(), user.getPassword(), 
					user.getLevel(), user.isErase());
		} catch (Exception e) {
			e.printStackTrace();
			return new AccountUserModel();
		} 
	}
	
	@GetMapping("/savebyphone/{name}/{phone}/{password}")
	public AccountUserModel saveByPhone (@PathVariable String name, @PathVariable String phone, @PathVariable String password) {
		
			AccountUser user = new AccountUser();
			user.setName(name);
			user.setPhone(phone);
			user.setEmail("-");
			user.setPassword(password);
			user.setGender("Male");
			user.setLevel("Level-1");
			user.setDob(LocalDate.now());
			user.setErase(false);
			user.setSecurityInfo(new SecurityInfo("own"));
			accountUserService.save(user);
			user = accountUserService.findByPhoneAndPassword(phone, password);
			return new AccountUserModel(
					user.getId(), user.getImage(), user.getName(), 
					user.getAge(), user.getGender(), user.getDob().toString(), 
					user.getPhone(), user.getEmail(), user.getPassword(), 
					user.getLevel(), user.isErase());

	}
	
	@GetMapping("/savebyemail/{name}/{email}/{password}")
	public AccountUserModel saveByEmail (@PathVariable String name, @PathVariable String email, @PathVariable String password) {
		try {
			AccountUser user = new AccountUser();
			user.setName(name);
			user.setPhone("-");
			user.setEmail(email);
			user.setPassword(password);
			user.setGender("Male");
			user.setLevel("Level-1");
			user.setDob(LocalDate.now());
			user.setErase(false);
			accountUserService.save(user);
			user = accountUserService.findByEmailAndPassword(email, password);
			return new AccountUserModel(
					user.getId(), user.getImage(), user.getName(), 
					user.getAge(), user.getGender(), user.getDob().toString(), 
					user.getPhone(), user.getEmail(), user.getPassword(), 
					user.getLevel(), user.isErase());
		} catch (Exception e) {
			e.printStackTrace();
			return new AccountUserModel();
		} 
	}
	
	@GetMapping("/update/{id}/{name}/{email}/{password}")
	public AccountUserModel update (@PathVariable String id, @PathVariable String name, @PathVariable String email, @PathVariable String password) {
		try {
			AccountUser user = accountUserService.findByID(id);
			user.setName(name);
			user.setPhone(email);
			user.setPassword(password);
			user.setLevel("Level-1");
			user.setErase(false);
			accountUserService.save(user);
			user = accountUserService.findByEmailAndPassword(email, password);
			return new AccountUserModel(
					user.getId(), user.getImage(), user.getName(), 
					user.getAge(), user.getGender(), user.getDob().toString(), 
					user.getPhone(), user.getEmail(), user.getPassword(), 
					user.getLevel(), user.isErase());
		} catch (Exception e) {
			return new AccountUserModel();
		} 
	}
	
	@GetMapping("/updatepassword/{loginID}/{password}")
	public boolean updatePassword (@PathVariable String loginID, @PathVariable String password) {
		try {
			AccountUser user = accountUserService.findByPhone(loginID);
			user.setPassword(password);
			accountUserService.save(user);
			return true;
		} catch (Exception e) {
			return false;
		} 
	}
	
}






