package com.redbox.pkdm.models;

import java.time.LocalDate;

import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.redbox.pkdm.entities.AdvertisingMajorBanner;

public class AdvertisingMajorBannerMapperModel {
	
	private long id;
	@ManyToOne
	private AdvertisingMajorBanner advertisingMajorBanner;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDate;
	private double fee;
	private boolean erase;
	
	public AdvertisingMajorBannerMapperModel() {
		
	}
	
	public AdvertisingMajorBannerMapperModel(long id, AdvertisingMajorBanner advertisingMajorBanner,
			LocalDate startDate, LocalDate endDate, double fee, boolean erase) {
		super();
		this.id = id;
		this.advertisingMajorBanner = advertisingMajorBanner;
		this.startDate = startDate;
		this.endDate = endDate;
		this.fee = fee;
		this.erase = erase;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public AdvertisingMajorBanner getAdvertisingMajorBanner() {
		return advertisingMajorBanner;
	}

	public void setAdvertisingMajorBanner(AdvertisingMajorBanner advertisingMajorBanner) {
		this.advertisingMajorBanner = advertisingMajorBanner;
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

	@Override
	public String toString() {
		return "AdvertisingMajorBannerMapperModel [id=" + id + ", advertisingMajorBanner=" + advertisingMajorBanner
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", fee=" + fee + ", erase=" + erase + "]";
	}
	
}
