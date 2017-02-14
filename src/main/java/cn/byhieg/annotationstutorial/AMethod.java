package cn.byhieg.annotationstutorial;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by byhieg on 17/2/14.
 * Mail to byhieg@gmail.com
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AMethod {
    public String method();

    public String value();
}
