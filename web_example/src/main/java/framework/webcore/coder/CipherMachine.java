package framework.webcore.coder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import static framework.util.FrameworkConstant.*;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public abstract class CipherMachine {

	protected Cipher encipher;
	protected Cipher decipher;
	
	public void init() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException  {
		encipher = Cipher.getInstance(ADVANCED_ENCRYPTION_STANDARD);
		KeyGenerator keygen = KeyGenerator.getInstance(ADVANCED_ENCRYPTION_STANDARD);
		keygen.init(128);
		SecretKey key = keygen.generateKey();
		encipher.init(Cipher.ENCRYPT_MODE, key);
		
		decipher = Cipher.getInstance(ADVANCED_ENCRYPTION_STANDARD);
		decipher.init(Cipher.DECRYPT_MODE, key);
	}
	
	public abstract String encrypt(String data);
	public abstract String decrypt(String encryptedString);
}
