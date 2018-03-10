package by.htp.itacademy.framework.util;

import by.htp.itacademy.framework.webcore.ActionHelper;
import by.htp.itacademy.framework.webcore.BeanHelper;

public class HelperLoader {

    public static void init() {
        Class<?>[] classList = {
            ActionHelper.class,
            BeanHelper.class,
        };

        for(Class<?> clz : classList) {
            ClassUtil.loadClass(clz.getName());
        }
    }
}
