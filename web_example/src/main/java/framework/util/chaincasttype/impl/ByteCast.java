package framework.util.chaincasttype.impl;

import framework.util.chaincasttype.Cast;

public class ByteCast implements Cast {

	private Cast castChain;
	private static final String BYTE = "java.lang.Byte";
	
	@Override
	public void setNextChain(Cast nextChain) {
		this.castChain = nextChain;
	}

	@Override
	public Object getValue(Class<?> type, Object obj) {
		
		if (BYTE.equals(type.getName())) {
			return Byte.valueOf(obj.toString());
		}
		
		return this.castChain.getValue(type, obj);
	}

}
