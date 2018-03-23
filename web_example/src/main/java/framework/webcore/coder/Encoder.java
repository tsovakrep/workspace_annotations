package framework.webcore.coder;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

import static framework.util.FrameworkConstant.*;

public class Encoder extends CipherMachine {
	private byte[] dataArray;
	
	@Override
	public String encrypt(String data) {
		try {
			dataArray = data.getBytes(ENCODING);
		} catch (UnsupportedEncodingException e) {
		}
		return encrypt();
	}
	
	private String encrypt() {
		String encryptedString = null;
		try {
			byte[] encryptedText = encipher.doFinal(dataArray);
			encryptedString = new String(Base64.encodeBase64(encryptedText));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encryptedString;
	}

	@Override
	public String decrypt(String encryptedString) {
		return null;
	}
}
