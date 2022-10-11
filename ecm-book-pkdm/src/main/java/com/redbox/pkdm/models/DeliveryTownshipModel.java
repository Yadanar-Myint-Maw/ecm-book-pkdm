package com.redbox.pkdm.models;

public class DeliveryTownshipModel {
	
	
	private long id;
	
	private String region;
	
	private String name;
	
	private double fee;
	
	private boolean erase;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
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

	public DeliveryTownshipModel(long id, String region, String name, double fee, boolean erase) {
		super();
		this.id = id;
		this.region = region;
		this.name = name;
		this.fee = fee;
		this.erase = erase;
	}
	
	
	
	

}
