package com.redbox.pkdm.utilities;

import java.time.LocalDate;
import java.util.Random;

public class IDGeneratorUtility {
	
	public static String generate (String id) {
		return id.substring(0, 4) + (Long.parseLong(id.substring(4, id.length())) + 1);
	}
	
	public static String generateImageID () {
		return "img-" + LocalDate.now().toString() + new Random().nextInt(9999)+".png";
	}

	

}
