package framework.webcore.util.chaincasttype;

public class ByteCast implements CastChain {

	private CastChain castChain;
	private static final String BYTE = "java.lang.Byte";
	
	@Override
	public void setNextChain(CastChain nextChain) {
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
