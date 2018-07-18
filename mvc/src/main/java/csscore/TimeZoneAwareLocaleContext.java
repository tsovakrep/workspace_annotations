package framework.csscore;

import java.util.TimeZone;

public interface TimeZoneAwareLocaleContext extends LocaleContext {

	/**
	 * Return the current TimeZone, which can be fixed or determined dynamically,
	 * depending on the implementation strategy.
	 * @return the current TimeZone, or {@code null} if no specific TimeZone associated
	 */
	TimeZone getTimeZone();

}
