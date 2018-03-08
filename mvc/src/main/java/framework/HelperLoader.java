package framework;

import framework.ioc.BeanHelper;
import framework.ioc.IocHelper;
import framework.mvc.ActionHelper;
import framework.util.ClassUtil;

public class HelperLoader {

    public static void init() {
        Class<?>[] classList = {
            ActionHelper.class,
            BeanHelper.class,
            IocHelper.class
        };

        for(Class<?> clz : classList) {
            ClassUtil.loadClass(clz.getName());
        }
    }
}
