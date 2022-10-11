package com.redbox.pkdm.models;

import java.time.LocalDate;

import javax.persistence.Lob;

import org.springframework.format.annotation.DateTimeFormat;

public class DiscountCoupons {
	
	private long id;
	private String name;
	private String description;
	private LocalDate startDate;
	private LocalDate endDate;
	
	public DiscountCoupons() {
		
	}

	public DiscountCoupons(long id, String name, String description, LocalDate startDate, LocalDate endDate) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	@Override
	public String toString() {
		return "DiscountCoupons [id=" + id + ", name=" + name + ", description=" + description + ", startDate="
				+ startDate + ", endDate=" + endDate + "]";
	}
	
	
	
	

}
