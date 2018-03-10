package framework.ioc;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import framework.core.ClassHelper;
import framework.ioc.annotation.Component;
import framework.mvc.exception.InitializationException;
import framework.mvc.annotation.Controller;
import framework.mvc.annotation.Service;

import java.util.*;

public class BeanHelper {
    private static final Logger logger = LoggerFactory.getLogger(BeanHelper.class);

    private static final Map<Class<?>, Object> beanMap = new HashMap<Class<?>, Object>();

    static {
        try {
            List<Class<?>> basePackageClassList = ClassHelper.getBasePackageClassList();
            List<Class<?>> beanClassList = new ArrayList<Class<?>>();
            if (CollectionUtils.isNotEmpty(basePackageClassList)) {
                for (int i = 0; i < basePackageClassList.size(); i++) {
                    Class<?> cls = basePackageClassList.get(i);
                    if (cls.isAnnotationPresent(Controller.class) || cls.isAnnotationPresent(Service.class)
                            || cls.isAnnotationPresent(Component.class)){
                        beanClassList.add(cls);
                        Object instance = cls.newInstance();
                        beanMap.put(cls, instance);
                    }
                }
            }
            printAllBeans(beanClassList);
        } catch (Exception e) {
            throw new InitializationException("���������BeanHelper������", e);
        }
    }

    private static void printAllBeans(List<Class<?>> beanClassList) {
        StringBuilder builder = new StringBuilder();
        if (CollectionUtils.isNotEmpty(beanClassList)) {
            for (int i = 0; i < beanClassList.size(); i++) {
                Class<?> beanClass = beanClassList.get(i);
                builder.append(beanClass.getSimpleName());
                if (i < beanClassList.size() - 1) {
                    builder.append("���");
                }
            }
        }
        logger.debug("bean name: [{}]", builder.toString());
    }

    public static Map<Class<?>, Object> getBeanMap() {
        return beanMap;
    }

    public static <T> T getBean(Class<T> cls) {
        if (!beanMap.containsKey(cls)) {
            throw new RuntimeException("������������bean ������");
        }
        return (T) beanMap.get(cls);
    }
}

