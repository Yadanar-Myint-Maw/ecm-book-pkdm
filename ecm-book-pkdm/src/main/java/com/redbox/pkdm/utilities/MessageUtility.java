package com.redbox.pkdm.utilities;

public class MessageUtility {
	
	public static String getSaveSuccessMessage (String usage) {
		return usage + " save success";
	}
	
	public static String getUpdateSuccessMessage (String usage) {
		return usage + " update success";
	}
	
	public static String getDeleteSuccesMessage (String usage) {
		return usage + " delete success";
	}
	
	public static String getDataMissingMessage () {
		return "Process fail, data is missing!";
	}
	
	// General
	public static String getDataIsMissingMessage () {
		return "အချက်အလက်များ ပြည့်စုံစွာ ဖြည့်သွင်းပါ";
	}
	
	// Wallet Text
	public static String getTopUpMessage () {
		return "ငွေဖြည့်သွင်းလိုက်ပါပြီ။";
	}
	
	public static String getTopUpDescription (String amount) {
		return "လူကြီးမင်း ပိုက်ဆံအိတ် အတွင်းသို့ " + amount + " MMK ငွေ ဖြည့်သွင်းလိုက်ပါသည်။";
	}

}
