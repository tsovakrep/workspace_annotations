package framework.csscore;

import java.util.Locale;

public class SimpleLocaleContext implements LocaleContext {

	private final Locale locale;


	/**
	 * Create a new SimpleLocaleContext that exposes the specified Locale.
	 * Every {@link #getLocale()} call will return this Locale.
	 * @param locale the Locale to expose
	 */
	public SimpleLocaleContext(Locale locale) {
		this.locale = locale;
	}

	@Override
	public Locale getLocale() {
		return this.locale;
	}

	@Override
	public String toString() {
		return (this.locale != null ? this.locale.toString() : "-");
	}

}

