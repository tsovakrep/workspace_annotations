package chaincasttype;

public class ControllerCast implements CastChain {

	private CastChain nextChain;
	private static final String CONTROLLER = "annotationapi.annotation.Controller";
	
	@Override
	public void setNextChain(CastChain nextChain) {
		this.nextChain = nextChain;
	}

	@Override
	public Object getValue(Class<?> type, Object obj) {
		
		if (CONTROLLER.equals(type.getName())) {
			return CONTROLLER.valueOf(obj.toString());
		}
		
		return this.nextChain.getValue(type, obj);
	}

}
