package com.redbox.pkdm.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class PurchasedInvoiceDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Lob
	private String description;
	
	private int quantity;
	
	private double fee;
	
	@ManyToOne
	private PurchasedInvoice purchasedInvoice;
	
	private boolean erase;
	
	public PurchasedInvoiceDetail() {
		
	}
	
	public PurchasedInvoiceDetail(PurchasedInvoice purchasedInvoice) {
		this.purchasedInvoice = purchasedInvoice;
	}

	public PurchasedInvoiceDetail(long id, String description, int quantity, double fee,
			PurchasedInvoice purchasedInvoice, boolean erase) {
		super();
		this.id = id;
		this.description = description;
		this.quantity = quantity;
		this.fee = fee;
		this.purchasedInvoice = purchasedInvoice;
		this.erase = erase;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	public PurchasedInvoice getPurchasedInvoice() {
		return purchasedInvoice;
	}

	public void setPurchasedInvoice(PurchasedInvoice purchasedInvoice) {
		this.purchasedInvoice = purchasedInvoice;
	}

	public boolean isErase() {
		return erase;
	}

	public void setErase(boolean erase) {
		this.erase = erase;
	}
	
}
