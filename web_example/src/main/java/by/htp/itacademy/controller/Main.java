package by.htp.itacademy.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.net.URL;
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
//		System.out.println(B.class.getConstructor().isAccessible());

//		Package[] p = Package.getPackages();
//		for (Package pa : p) {
//			System.out.println(pa);
//		}
		Method method = B.class.getMethod("getInstance", null);
		Object obj = method.invoke(null, null);
		System.out.println(B.getInstance());
		System.out.println(obj);
		System.out.println(B.class.newInstance());
		
	}
}

interface A{}
class B implements A{
	public B() {}
	private static class Singletone {
		private static final B INSTANCE = new B();
	}
	public static B getInstance() {
		return Singletone.INSTANCE;
	}
}

/*private AccessoryDaoImpl() {
		super(Accessory.class);
	}

	private static class Singletone {
		private static final AccessoryDaoImpl INSTANCE = new AccessoryDaoImpl();
	}

	public static AccessoryDaoImpl getInstance() {
		return Singletone.INSTANCE;
	}*/