package by.htp.itacademy.controller.context;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ThreadContext<T> {
	
	private final Map<ThreadContext<T>, T> map = new ConcurrentHashMap<>();
	
	public ThreadContext() {}
	
	public T get() {
		return map.get(this);
	}
	
	public boolean set(T t) {
		
		if (t != null) {
			map.put(this, t);
			return true;
		}
		
		return false;
	}
	
	public boolean remove() {
		if (map.containsKey(this)) {
			map.remove(this);
			return true;
		}
		
		return false;
	}
	
}
