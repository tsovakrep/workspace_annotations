package framework.webcore.util.chaincasttype.impl;

import framework.webcore.util.chaincasttype.Cast;

public class DoubleCast implements Cast {

	private Cast nextChain;
	private static final String DOUBLE = "java.lang.Double";
	
	@Override
	public void setNextChain(Cast nextChain) {
		this.nextChain = nextChain;
	}

	@Override
	public Object getValue(Class<?> type, Object obj) {
		
		if (DOUBLE.equals(type.getName())) {
			return Double.valueOf(obj.toString());
		}
		
		return this.nextChain.getValue(type, obj);
	}

}
