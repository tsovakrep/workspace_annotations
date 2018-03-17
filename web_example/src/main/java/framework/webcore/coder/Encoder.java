package framework.webcore.coder;

public class Encoder extends CipherMachine {
	
	public static <T> T encrypt(T data) {
		return (T) getEncryptData(converterDataIntoAnArrayOfIntegers(data), 
				converterKeyIntoAnArrayOfIntegers());
	}
	
	private static <T> T getEncryptData(int[] dataArrayInt, int[] keyArrayInt) {
		char[] dataEncryptArrayChar = new char[dataLength];
		for(int i = 0; i < dataLength; i++) {
			dataEncryptArrayChar[i] = (char) (dataArrayInt[i] + keyArrayInt[i]);
		}
		return (T) dataEncryptArrayChar;
	}
}
