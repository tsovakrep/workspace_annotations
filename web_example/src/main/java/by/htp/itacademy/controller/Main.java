package by.htp.itacademy.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.security.DigestInputStream;
import java.security.DigestOutputStream;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import framework.webcore.coder.CipherMachine;
import framework.webcore.coder.Decoder;
import framework.webcore.coder.Encoder;

public class Main {
	public static void main(String[] args) throws Exception {
		// String requestUrl = "/user/getUserInfo/{uid}/{aid}/{adf}";
		// System.out.println(requestPath.matches(".+\\{\\w+}.*"));
		// requestUrl = requestUrl.replaceAll("\\{([a-z])\\w+\\}", "(\\\\w+\\)");
		// System.out.println(requestUrl);
		//
		// requestPath = replaceAll(requestPath, "\\{\\w+\\}", "(\\\\w+)");
		// System.out.println(requestPath);
		//
		// String requestPath = "/user/getUserInfo/333/a5d/adf";
		// Matcher matcher = Pattern.compile(requestUrl).matcher(requestPath);
		// System.out.println(matcher.matches());
		//
		// for (int i = 1; i <= matcher.groupCount(); i++) {
		// // System.out.println("matcher counter :" + matcher.groupCount());
		// String group = matcher.group(i);
		// System.out.println(group);
		// }
		//
		// String group1 = matcher.group(1);
		// System.out.println(group1);
		//
		// String group2 = matcher.group(2);
		// System.out.println(group2);
		//
		// String group3 = matcher.group(3);
		// System.out.println(group3);
		// try {
		// System.out.println(Encoder.encrypt(new String("5")));
		// } catch (NoSuchAlgorithmException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// System.out.println(Encoder.encrypt(new User("Tsovak")));
		
//		for (int i = 32; i < 127; i++) {
//			char symbol = (char) i;
//			System.out.println("i: " + i + "; symbol: " + symbol);
//		}
		
//		Encoder.encrypt(new Tsovak());
//		MyCipher mc = new MyCipher();
//		mc.encoder();
//		boolean is = A.class.isAssignableFrom(B.class);
//		System.out.println(is);
		
	}
}

//interface A{}
//class B implements A{}