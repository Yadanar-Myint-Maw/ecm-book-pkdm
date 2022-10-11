package com.redbox.pkdm.utilities;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class CookieUtility {
	
	public static void saveLoginUserIDCookie (String value, HttpServletResponse response) {
		Cookie cookie = getCookie("login_user_id", value);
		response.addCookie(cookie);
	}
	
	public static void removeLoginUserIDCookie (HttpServletResponse response) {
		Cookie cookie = removeCookie("login_user_id", null);
		response.addCookie(cookie);
	}
	
	public static Cookie getCookie(String name, String value) {
		Cookie cookie = new Cookie(name, value);
		cookie.setHttpOnly(true);
		cookie.setPath("/");
		return cookie;	
	}
	
	public static Cookie removeCookie(String name, String value) {
		Cookie cookie = new Cookie(name, null);
		cookie.setMaxAge(0);
		cookie.setSecure(true);
		cookie.setHttpOnly(true);
		cookie.setPath("/");
		return cookie;	
	}
	
}
