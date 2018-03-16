package framework.util.chaincasttype.impl;

import framework.util.chaincasttype.Cast;

public class IntegerCast implements Cast {

	private Cast castChain;
	private static final String INTEGER = "java.lang.Integer";
	
	@Override
	public void setNextChain(Cast nextChain) {
		this.castChain = nextChain;
	}

	@Override
	public Object getValue(Class<?> type, Object obj) {
		
		if (INTEGER.equals(type.getName())) {
			return Integer.valueOf(obj.toString());
		}
		
		return this.castChain.getValue(type, obj);
	}

}