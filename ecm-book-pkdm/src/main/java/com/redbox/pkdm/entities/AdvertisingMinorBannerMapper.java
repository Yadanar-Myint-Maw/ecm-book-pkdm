package com.redbox.pkdm.entities;

import java.time.LocalDate;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class AdvertisingMinorBannerMapper {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	private AdvertisingMinorBanner advertisingMinorBanner;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDate;
	
	private double fee;
	
	private boolean erase;
	
	@Embedded
	private SecurityInfo securityInfo;
	
	public AdvertisingMinorBannerMapper () {
		
	}

	public AdvertisingMinorBannerMapper(long id, AdvertisingMinorBanner advertisingMinorBanner, LocalDate startDate,
			LocalDate endDate, double fee, boolean erase, SecurityInfo securityInfo) {
		super();
		this.id = id;
		this.advertisingMinorBanner = advertisingMinorBanner;
		this.startDate = startDate;
		this.endDate = endDate;
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

	public AdvertisingMinorBanner getAdvertisingMinorBanner() {
		return advertisingMinorBanner;
	}

	public void setAdvertisingMinorBanner(AdvertisingMinorBanner advertisingMinorBanner) {
		this.advertisingMinorBanner = advertisingMinorBanner;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
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
	
}
