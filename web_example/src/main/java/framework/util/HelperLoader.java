package framework.util;

import framework.webcore.helper.ActionHelper;
import framework.webcore.helper.BeanHelper;

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
