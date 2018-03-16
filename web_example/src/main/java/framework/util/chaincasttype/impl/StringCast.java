package framework.util.chaincasttype.impl;

import framework.util.chaincasttype.Cast;

public class StringCast implements Cast {

	private Cast castChain;
	private static final String STRING = "java.lang.String";
	
	@Override
	public void setNextChain(Cast nextChain) {
		this.castChain = nextChain;
	}

	@Override
	public Object getValue(Class<?> type, Object obj) {
		
		if (STRING.equals(type.getName())) {
			return obj.toString();
		}
		
		return this.castChain.getValue(type, obj);
	}

}
