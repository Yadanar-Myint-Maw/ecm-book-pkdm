package com.redbox.pkdm.entities;

import java.util.Objects;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class DeliveryTownship {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	private DeliveryRegion deliveryRegion;
	
	private String name;
	
	private double fee;
	
	private boolean erase;
	
	@Embedded
	private SecurityInfo securityInfo;
	
	public DeliveryTownship() {
		
	}

	public DeliveryTownship(long id, DeliveryRegion deliveryRegion, String name, double fee, boolean erase,
			SecurityInfo securityInfo) {
		super();
		this.id = id;
		this.deliveryRegion = deliveryRegion;
		this.name = name;
		this.fee = fee;
		this.erase = erase;
		this.securityInfo = securityInfo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public DeliveryRegion getDeliveryRegion() {
		return deliveryRegion;
	}

	public void setDeliveryRegion(DeliveryRegion deliveryRegion) {
		this.deliveryRegion = deliveryRegion;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DeliveryTownship other = (DeliveryTownship) obj;
		return erase == other.erase && Double.doubleToLongBits(fee) == Double.doubleToLongBits(other.fee)
				&& id == other.id && Objects.equals(name, other.name);
	}
	
	
	
}
