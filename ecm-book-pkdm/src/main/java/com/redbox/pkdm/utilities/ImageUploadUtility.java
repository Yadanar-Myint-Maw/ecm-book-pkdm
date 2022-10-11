package com.redbox.pkdm.utilities;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class ImageUploadUtility {
	
	public static String DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/img";
	

	public static String upload (MultipartFile file) {
		String filename = IDGeneratorUtility.generateImageID();
		try {
	        Path fileNameAndPath = Paths.get(DIRECTORY, filename);
	        Files.write(fileNameAndPath, file.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return "http://localhost:8080/img/" + filename;
	}
	
}
