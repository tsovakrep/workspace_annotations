package by.htp.itacademy.framework.webcore.util.chaincasttype;

public class DoubleCast implements CastChain {

	private CastChain nextChain;
	private static final String DOUBLE = "java.lang.Double";
	
	@Override
	public void setNextChain(CastChain nextChain) {
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
