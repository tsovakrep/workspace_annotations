package framework.webcore.coder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import static framework.util.FrameworkConstant.*;

public abstract class CipherMachine {

	protected static Cipher encipher;
	protected static Cipher decipher;
	
	public static void init() throws Exception {
		encipher = Cipher.getInstance(ADVANCED_ENCRYPTION_STANDARD);
		KeyGenerator keygen = KeyGenerator.getInstance(ADVANCED_ENCRYPTION_STANDARD);
		keygen.init(128);
		SecretKey key = keygen.generateKey();
		encipher.init(Cipher.ENCRYPT_MODE, key);
		
		decipher = Cipher.getInstance(ADVANCED_ENCRYPTION_STANDARD);
		decipher.init(Cipher.DECRYPT_MODE, key);
	}
}
