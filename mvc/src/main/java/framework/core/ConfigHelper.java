package framework.core;

import framework.FrameworkConstant;
import framework.util.PropsUtil;

import java.util.Properties;

public class ConfigHelper {

    private static final Properties configProps = PropsUtil.loadProps(FrameworkConstant.CONFIG_PROPS);

    public static String getString(String key) {
        return PropsUtil.getString(configProps, key);
    }

    public static String getString(String key, String defaultValue) {
        return PropsUtil.getString(configProps, key, defaultValue);
    }
}

