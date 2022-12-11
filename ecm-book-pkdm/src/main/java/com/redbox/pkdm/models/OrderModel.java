package com.redbox.pkdm.models;

import java.time.LocalDate;
import java.util.List;

import com.redbox.pkdm.entities.AccountUser;
import com.redbox.pkdm.entities.DeliveryInfo;
import com.redbox.pkdm.entities.PurchasedTransition;

public class OrderModel {
	
	//private AccountUser accountUser;
	
	private String invoiceNo;
	
	private List<PurchasedTransition> purchasedTransitions;
	
	private double totalPrice;
	
	//private LocalDate date;
	
	//private String addressDetail;
	
	//private double deliveryFee;
	
	//private String paymentType;
	
	//private String deliveryStatus;
	
	public OrderModel() {
		
	}
	
	public OrderModel(String invoiceNo, List<PurchasedTransition> purchasedTransitions, double totalPrice) {
		super();
		this.invoiceNo = invoiceNo;
		this.purchasedTransitions = purchasedTransitions;
		this.totalPrice = totalPrice;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public List<PurchasedTransition> getPurchasedTransitions() {
		return purchasedTransitions;
	}

	public void setPurchasedTransitions(List<PurchasedTransition> purchasedTransitions) {
		this.purchasedTransitions = purchasedTransitions;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
	

}
