package com.redbox.pkdm.utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import org.springframework.web.multipart.MultipartFile;

public class ImageUploadUtility {
	
	
	

	public static String upload (MultipartFile file) {
		if (file != null) {
			String extension = file.getContentType();
			if (extension.substring(extension.length() -3, extension.length()).equals("png") ||
					extension.substring(extension.length() -3, extension.length() - 1).equals("jpg") ||
					extension.substring(extension.length() -4, extension.length()).equals("jpeg")) {
				String imageFolder = "C:\\inetpub\\wwwroot\\image\\";
				String imageFile =  generateImageName();
				File image = new File(imageFolder+'/'+imageFile);
				try(InputStream in = file.getInputStream();
						OutputStream out = new FileOutputStream(image)) {
					int data = -1;
					while ((data = in.read()) != -1) {
						out.write(data);
					}
				} catch (Exception e) {
					
				}
				return "http://phoenixkingdom.org/image/"+imageFile;
				//return "http://cgmsmm.com/image/"+imageFile;
			}
		}
		return "http://phoenixkingdom.org/image/no-image.png";
	}
	
	public static String generateImageName () {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyyhhmmss");
		Random random = new Random();
		String id = LocalDateTime.now().format(formatter) + random.nextInt(999);
		return "img-"+id+".png";
	}
	
}
