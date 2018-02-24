package by.htp.itacademy.music;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

/**
 * @author Ivan Khalopik
 * @since 1.0
 */
public abstract class SPIUtils {
	private static final boolean OLD_API = isOld();

	private static boolean isOld() {
		try {
			Class.forName("java.util.ServiceLoader");
			return false;
		} catch (ClassNotFoundException e) {
			return true;
		}
	}

	public static <T> T resolve(final Class<T> serviceClass) {
		assert serviceClass != null;

		if (OLD_API) {
			return resolveBySunService(serviceClass);
		}
		return resolveByServiceLoader(serviceClass);
	}

	public static <T> List<T> resolveAll(final Class<T> serviceClass) {
		assert serviceClass != null;

		if (OLD_API) {
			return resolveAllBySunService(serviceClass);
		}
		return resolveAllByServiceLoader(serviceClass);
	}

	private static <T> T resolveByServiceLoader(final Class<T> serviceClass) {
		final Iterator<T> providers = ServiceLoader.load(serviceClass).iterator();
		if (providers.hasNext()) {
			return providers.next();
		}

		return null;
	}

	private static <T> T resolveBySunService(final Class<T> serviceClass) {
		final Iterator providers = java.util.ServiceLoader.load(serviceClass).iterator();
		if (providers.hasNext()) {
			return serviceClass.cast(providers.next());
		}

		return null;
	}

	private static <T> List<T> resolveAllByServiceLoader(final Class<T> serviceClass) {
		final List<T> services = new ArrayList<T>();
		for (final T service : ServiceLoader.load(serviceClass)) {
			services.add(service);
		}

		return services;
	}

	private static <T> List<T> resolveAllBySunService(final Class<T> serviceClass) {
		final List<T> services = new ArrayList<T>();
		final Iterator providers = java.util.ServiceLoader.load(serviceClass).iterator();
		while (providers.hasNext()) {
			services.add(serviceClass.cast(providers.next()));
		}

		return services;
	}
}
