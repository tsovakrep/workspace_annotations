package by.htp.itacademy.framework.webcore.util.chaincasttype;

public class IntegerCast implements CastChain {

	private CastChain castChain;
	private static final String INTEGER = "java.lang.Integer";
	
	@Override
	public void setNextChain(CastChain nextChain) {
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