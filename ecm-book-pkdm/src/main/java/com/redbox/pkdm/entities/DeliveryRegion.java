package com.redbox.pkdm.entities;

import java.util.Objects;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DeliveryRegion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	
	private boolean erase;
	
	@Embedded
	private SecurityInfo securityInfo;
	
	public DeliveryRegion() {
		
	}

	public DeliveryRegion(long id, String name, boolean erase, SecurityInfo securityInfo) {
		super();
		this.id = id;
		this.name = name;
		this.erase = erase;
		this.securityInfo = securityInfo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	public int hashCode() {
		return Objects.hash(erase, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DeliveryRegion other = (DeliveryRegion) obj;
		return erase == other.erase && id == other.id && Objects.equals(name, other.name);
	}
	
}
