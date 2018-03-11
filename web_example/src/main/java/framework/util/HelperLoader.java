package framework.util;

import framework.webcore.ActionHelper;
import framework.webcore.BeanHelper;

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
