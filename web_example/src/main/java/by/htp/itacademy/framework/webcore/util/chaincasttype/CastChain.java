package by.htp.itacademy.framework.webcore.util.chaincasttype;

public interface CastChain {
	
	void setNextChain(CastChain nextChain);
	
	Object getValue(Class<?> type, Object obj);
	
}
