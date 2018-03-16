package framework.util.chaincasttype;

public interface Cast {
	
	void setNextChain(Cast nextChain);
	
	Object getValue(Class<?> type, Object obj);
	
}
