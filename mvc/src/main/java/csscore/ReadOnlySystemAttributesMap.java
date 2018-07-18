package framework.csscore;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

abstract class ReadOnlySystemAttributesMap implements Map<String, String> {

	@Override
	public boolean containsKey(Object key) {
		return (get(key) != null);
	}

	/**
	 * @param key the name of the system attribute to retrieve
	 * @throws IllegalArgumentException if given key is non-String
	 */
	@Override
	public String get(Object key) {
		if (!(key instanceof String)) {
			throw new IllegalArgumentException(
					"Type of key [" + (key != null ? key.getClass().getName() : "null") +
					"] must be java.lang.String.");
		}
		return this.getSystemAttribute((String) key);
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	/**
	 * Template method that returns the underlying system attribute.
	 * <p>Implementations typically call {@link System#getProperty(String)} or {@link System#getenv(String)} here.
	 */
	protected abstract String getSystemAttribute(String attributeName);


	// Unsupported

	@Override
	public int size() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String put(String key, String value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsValue(Object value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String remove(Object key) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<String> keySet() {
		return Collections.emptySet();
	}

	@Override
	public void putAll(Map<? extends String, ? extends String> map) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<String> values() {
		return Collections.emptySet();
	}

	@Override
	public Set<Entry<String, String>> entrySet() {
		return Collections.emptySet();
	}

}
