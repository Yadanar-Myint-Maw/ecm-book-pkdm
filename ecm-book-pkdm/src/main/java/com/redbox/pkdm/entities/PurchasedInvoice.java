package com.redbox.pkdm.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class PurchasedInvoice {
	
	@Id
	private String id;
	
	private double total;
	
	@ManyToOne
	private AccountUser accountUser;
	
	@OneToMany(mappedBy = "purchasedInvoice", orphanRemoval = true, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<PurchasedInvoiceDetail> purchasedInvoiceDetails;
	
	private boolean erase;
	
	@Embedded
	private SecurityInfo securityInfo;
	
	public PurchasedInvoice() {
		
	}

	public PurchasedInvoice(String id, double total, AccountUser accountUser,
			List<PurchasedInvoiceDetail> purchasedInvoiceDetails, boolean erase, SecurityInfo securityInfo) {
		super();
		this.id = id;
		this.total = total;
		this.accountUser = accountUser;
		this.purchasedInvoiceDetails = purchasedInvoiceDetails;
		this.erase = erase;
		this.securityInfo = securityInfo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public AccountUser getAccountUser() {
		return accountUser;
	}

	public void setAccountUser(AccountUser accountUser) {
		this.accountUser = accountUser;
	}

	public List<PurchasedInvoiceDetail> getPurchasedInvoiceDetails() {
		return purchasedInvoiceDetails;
	}

	public void setPurchasedInvoiceDetails(List<PurchasedInvoiceDetail> purchasedInvoiceDetails) {
		this.purchasedInvoiceDetails = purchasedInvoiceDetails;
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
