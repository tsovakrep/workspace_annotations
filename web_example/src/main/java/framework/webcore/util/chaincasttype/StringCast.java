package framework.webcore.util.chaincasttype;

public class StringCast implements CastChain {

	private CastChain castChain;
	private static final String STRING = "java.lang.String";
	
	@Override
	public void setNextChain(CastChain nextChain) {
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
