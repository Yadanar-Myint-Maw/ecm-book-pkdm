package com.redbox.pkdm.models;

import java.util.List;

import com.redbox.pkdm.entities.AccountUser;
import com.redbox.pkdm.entities.DeliveryInfo;
import com.redbox.pkdm.entities.DiscountCoupon;
import com.redbox.pkdm.entities.PurchasedTransition;

public class PurchaseByInvoiceModel {
	
	private AccountUser accountUser;
	
	private String invoiceNo;
	
	private List<PurchasedTransition> purchaseTransitions;
	
	private List<DiscountCoupon> discountCoupons;
		
	private double total;
	
	private String deliveryInfo;
	
	private double deliveryFee;
	
	public PurchaseByInvoiceModel() {
		
	}
	
	public PurchaseByInvoiceModel(AccountUser accountUser, String invoiceNo,
			List<PurchasedTransition> purchaseTransitions, List<DiscountCoupon> discountCoupons, double total,
			String deliveryInfo, double deliveryFee) {
		super();
		this.accountUser = accountUser;
		this.invoiceNo = invoiceNo;
		this.purchaseTransitions = purchaseTransitions;
		this.discountCoupons = discountCoupons;
		this.total = total;
		this.deliveryInfo = deliveryInfo;
		this.deliveryFee = deliveryFee;
	}

	public String getDeliveryInfo() {
		return deliveryInfo;
	}


	public void setDeliveryInfo(String deliveryInfo) {
		this.deliveryInfo = deliveryInfo;
	}


	public double getDeliveryFee() {
		return deliveryFee;
	}

	public void setDeliveryFee(double deliveryFee) {
		this.deliveryFee = deliveryFee;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public List<PurchasedTransition> getPurchaseTransitions() {
		return purchaseTransitions;
	}

	public void setPurchaseTransitions(List<PurchasedTransition> purchaseTransitions) {
		this.purchaseTransitions = purchaseTransitions;
	}

	public AccountUser getAccountUser() {
		return accountUser;
	}

	public void setAccountUser(AccountUser accountUser) {
		this.accountUser = accountUser;
	}


	public List<DiscountCoupon> getDiscountCoupons() {
		return discountCoupons;
	}


	public void setDiscountCoupons(List<DiscountCoupon> discountCoupons) {
		this.discountCoupons = discountCoupons;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	
	
	
	
	
	

}
