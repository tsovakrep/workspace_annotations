package com.logicbig.example;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

public abstract class Util {
	
	public static String getBodyString(HttpServletRequest request) {
		InputStream body = null;
		StringBuilder buf = new StringBuilder(512);
		try {
			body = request.getInputStream();

			int b;
			while ((b = body.read()) != -1) {
				buf.append((char) b);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buf.toString();
	}
}
