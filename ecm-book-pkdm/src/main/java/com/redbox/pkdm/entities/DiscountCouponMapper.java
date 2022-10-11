package com.redbox.pkdm.entities;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class DiscountCouponMapper {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	private DiscountCoupon discountCoupon;

	@ManyToOne
	private AccountUser accountUser;
	
	private boolean active;
	
	private boolean erase;
	
	@Embedded
	private SecurityInfo securityInfo;
	
	public DiscountCouponMapper() {
		
	}

	public DiscountCouponMapper(long id, DiscountCoupon discountCoupon, AccountUser accountUser, boolean active,
			boolean erase, SecurityInfo securityInfo) {
		super();
		this.id = id;
		this.discountCoupon = discountCoupon;
		this.accountUser = accountUser;
		this.active = active;
		this.erase = erase;
		this.securityInfo = securityInfo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public DiscountCoupon getDiscountCoupon() {
		return discountCoupon;
	}

	public void setDiscountCoupon(DiscountCoupon discountCoupon) {
		this.discountCoupon = discountCoupon;
	}

	public AccountUser getAccountUser() {
		return accountUser;
	}

	public void setAccountUser(AccountUser accountUser) {
		this.accountUser = accountUser;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isErase() {
		return erase;
	}

	public void setErase(boolean erase) {
		this.erase = erase;
	}

	public SecurityInfo getSecurityInfo() {
		return securityInfo;
	}

	public void setSecurityInfo(SecurityInfo securityInfo) {
		this.securityInfo = securityInfo;
	}
	
}
