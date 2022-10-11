package com.redbox.pkdm.entities;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class DeliveryInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String phone;
	
	@ManyToOne
	private DeliveryRegion deliveryRegion;
	
	@ManyToOne
	private DeliveryTownship deliveryTownship;
	
	@ManyToOne
	private AccountUser accountUser;
	
	@Lob
	private String addressDetail;
	
	private boolean erase;
	
	@Embedded
	private SecurityInfo securityInfo;
	
	public DeliveryInfo () {
		
	}

	public DeliveryInfo(long id, String phone, DeliveryRegion deliveryRegion, DeliveryTownship deliveryTownship,
			String addressDetail, boolean erase, SecurityInfo securityInfo) {
		super();
		this.id = id;
		this.phone = phone;
		this.deliveryRegion = deliveryRegion;
		this.deliveryTownship = deliveryTownship;
		this.addressDetail = addressDetail;
		this.erase = erase;
		this.securityInfo = securityInfo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public DeliveryRegion getDeliveryRegion() {
		return deliveryRegion;
	}

	public void setDeliveryRegion(DeliveryRegion deliveryRegion) {
		this.deliveryRegion = deliveryRegion;
	}

	public DeliveryTownship getDeliveryTownship() {
		return deliveryTownship;
	}

	public void setDeliveryTownship(DeliveryTownship deliveryTownship) {
		this.deliveryTownship = deliveryTownship;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
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
