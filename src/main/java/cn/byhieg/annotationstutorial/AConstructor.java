package cn.byhieg.annotationstutorial;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by byhieg on 17/2/14.
 * Mail to byhieg@gmail.com
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.CONSTRUCTOR)
public @interface AConstructor {

    public String initName() default "byhieg";

    public int initAge() default 24;

}
