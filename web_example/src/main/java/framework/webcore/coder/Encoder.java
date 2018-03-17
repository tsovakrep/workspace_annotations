package framework.webcore.coder;

public class Encoder extends CipherMachine {
	
	private String getEncryptData(int[] dataArrayInt, int[] keyArrayInt) {
		char[] dataEncryptArrayChar = new char[dataLength];
		for(int i = 0; i < dataLength; i++) {
			dataEncryptArrayChar[i] = (char) (dataArrayInt[i] + keyArrayInt[i]);
		}
		return new String(dataEncryptArrayChar);
	}
	
	public String encrypt(String data) {
		return getEncryptData(converterDataIntoAnArrayOfIntegers(data), 
				converterKeyIntoAnArrayOfIntegers());
	}
}
