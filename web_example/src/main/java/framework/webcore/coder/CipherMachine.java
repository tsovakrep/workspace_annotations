package framework.webcore.coder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

abstract class CipherMachine {

	private static final String KEY = "fiDeliO";

	protected int dataLength;

	protected <T> int[] converterDataIntoAnArrayOfIntegers(T data) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(baos);
			oos.writeObject(data);
		} catch (IOException e) {
			e.printStackTrace();
		}

		byte[] dataArrayChar = baos.toByteArray();
		dataLength = dataArrayChar.length;

		int[] dataArrayInt = new int[dataLength];

		for (int i = 0; i < dataLength; i++) {
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

		for (int i = 0; i < dataLength - 1; i++) {
			for (int k = 0; k + i < j; k++) {
				keyArrayChar[i + k] = keyArray[k];
				if (k == m) {
					i = k + i;
					k = 0;
					break;
				}
			}
		}

		int[] keyArrayInt = new int[dataLength];

		for (int i = 0, l = dataLength; i < l; i++) {
			keyArrayInt[i] = keyArrayChar[i];
		}
		return keyArrayInt;
	}
}
