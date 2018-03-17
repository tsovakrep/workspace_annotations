package framework.webcore.coder;

abstract class CipherMachine {
	
	private static final String KEY = "fiDeliO";
	
	protected int dataLength;
	
	protected int[] converterDataIntoAnArrayOfIntegers(String data) {
		char[] dataArrayChar = data.toCharArray();
		dataLength = dataArrayChar.length;
		
		int[] dataArrayInt = new int[dataLength];
		
		for(int i = 0; i < dataLength; i++) {
			dataArrayInt[i] = dataArrayChar[i];
		}
		return dataArrayInt;
	}
	
	protected int[] converterKeyIntoAnArrayOfIntegers() {
		char[] keyArray = KEY.toCharArray();
		int lengthArrayKey = keyArray.length;
		
		char[] keyArrayChar = new char[dataLength];
		
		boolean compare = lengthArrayKey < dataLength;
		int j = compare ? dataLength : lengthArrayKey - 1;
		int m = compare ? dataLength : lengthArrayKey - 1;
		
		for(int i = 0; i < dataLength - 1; i++) {
			for(int k = 0; k + i < j; k++) {
				keyArrayChar[i + k] = keyArray[k];
				if(k == m) {
					i = k + i;
					k = 0;
					break;
				}
			}
		}
		
		int[] keyArrayInt = new int[dataLength];
		
		for(int i = 0, l = dataLength; i < l; i++) {
			keyArrayInt[i] = keyArrayChar[i];
		}
		return keyArrayInt;
	}
}
