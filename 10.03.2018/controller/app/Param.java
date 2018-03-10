package by.htp.itacademy.controller.app;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public  @interface Param {
    String name();
}
