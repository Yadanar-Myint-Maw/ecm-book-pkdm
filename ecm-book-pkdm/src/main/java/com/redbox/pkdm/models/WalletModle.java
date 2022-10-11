package com.redbox.pkdm.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.redbox.pkdm.entities.AccountUser;

public class WalletModle {
	
	private long id;
	private String walletType;
	private String description;	
	private double amount;
	
	public WalletModle() {
		
	}

	public WalletModle(long id, String walletType, String description, double amount) {
		super();
		this.id = id;
		this.walletType = walletType;
		this.description = description;
		this.amount = amount;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getWalletType() {
		return walletType;
	}

	public void setWalletType(String walletType) {
		this.walletType = walletType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "WalletModle [id=" + id + ", walletType=" + walletType + ", description=" + description + ", amount="
				+ amount + "]";
	}

}
