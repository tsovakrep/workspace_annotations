package framework.ioc;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;
import framework.core.ClassHelper;
import framework.ioc.annotation.Impl;
import framework.ioc.annotation.Inject;
import framework.mvc.exception.InitializationException;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class IocHelper {
    static {
        try {
            Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
            if (MapUtils.isNotEmpty(beanMap)) {
                for (Map.Entry<Class<?>, Object> entry : beanMap.entrySet()) {
                    Class<?> clz = entry.getKey();
                    Object beanInstance = entry.getValue();

                    Field[] declaredFields = clz.getDeclaredFields();
                    if (!ArrayUtils.isEmpty(declaredFields)) {
                        for (Field field : declaredFields) {
                            if (field.isAnnotationPresent(Inject.class)) {
                                Class<?> interfaceType = field.getType();
                                Class<?> implementsClass = findImplementsClass(interfaceType);
                                if (implementsClass != null) {
                                    Object implementsInstance = beanMap.get(implementsClass);
                                    if (implementsInstance != null) {
                                        field.setAccessible(true);
                                        field.set(beanInstance, implementsInstance);
                                    } else {
                                        throw new InitializationException("������������������������������: " + clz + ", ���������������: " + interfaceType.getSimpleName() + "������������:" + field.getName());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new InitializationException("IOC ���������������������", e);
        }
    }

    private static Class<?> findImplementsClass(Class<?> interfaceType) {
        Class<?> implementClass = interfaceType;
        if (interfaceType.isAnnotationPresent(Impl.class)) {
            implementClass = interfaceType.getAnnotation(Impl.class).value();
        } else {
            List<Class<?>> superClassList = ClassHelper.getClassListBySuper(implementClass);
            if (CollectionUtils.isNotEmpty(superClassList)) {
                implementClass = superClassList.get(0);
            }
        }
        return implementClass;
    }
}

