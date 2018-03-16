package framework.webcore.helper;

import framework.util.ClassUtil;

public class HelperLoader {

    public static void init() {
        Class<?>[] classList = {
            ActionHelper.class,
            BeanHelper.class,
            FieldHelper.class
        };

        for(Class<?> clz : classList) {
            ClassUtil.loadClass(clz.getName());
        }
    }
}
