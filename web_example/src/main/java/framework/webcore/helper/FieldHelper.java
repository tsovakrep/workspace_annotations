package framework.webcore.helper;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import framework.classcore.helper.ClassHelper;
import framework.util.ObjectUtils;
import framework.webcore.annotation.initialization.AutoInit;
import framework.webcore.exception.InitializationException;

public class FieldHelper {

	static {
		try {
			Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
			if (ObjectUtils.isNotEmptyMap(beanMap)) {
				for (Map.Entry<Class<?>, Object> entry : beanMap.entrySet()) {
					Class<?> clz = entry.getKey();
					Object beanInstance = entry.getValue();

					Field[] declaredFields = clz.getDeclaredFields();
					if (ObjectUtils.isNotEmptyArray(declaredFields)) {
						for (Field field : declaredFields) {
							if (field.isAnnotationPresent(AutoInit.class)) {
								Class<?> interfaceType = field.getType();
								Class<?> implementsClass = findImplementsClass(interfaceType);
								if (ObjectUtils.isNotNullClass(implementsClass)) {
									Object implementsInstance = beanMap.get(implementsClass);
									if (ObjectUtils.isNotNullObject(implementsInstance)) {
										field.setAccessible(true);
										field.set(beanInstance, implementsInstance);
									} else {
										throw new InitializationException("The field " + field.getName() + " was not initialized");
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			throw new InitializationException("FiedlHelper error's");
		}
	}

	private static Class<?> findImplementsClass(Class<?> interfaceType) {
		Class<?> implementClass = interfaceType;
		List<Class<?>> superClassList = ClassHelper.getClassListBySuper(implementClass);
		if (ObjectUtils.isNotEmptyCollection(superClassList)) {
			implementClass = superClassList.get(0);
		}
		return implementClass;
	}
}
