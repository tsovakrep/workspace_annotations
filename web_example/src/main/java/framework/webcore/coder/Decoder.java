package framework.webcore.coder;

import org.apache.commons.codec.binary.Base64;

public class Decoder extends CipherMachine {
	
	public String decrypt(String encryptedString) {
		String text = null;
		try {
			byte[] encryptedText = Base64.decodeBase64(encryptedString);
			byte[] plainText = decipher.doFinal(encryptedText);
			text = new String(plainText);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return text;
	}

	@Override
	public String encrypt(String data) {
		return null;
	}
}
