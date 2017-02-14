package cn.byhieg.annotationstutorial;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by byhieg on 17/2/14.
 * Mail to byhieg@gmail.com
 */

@Target({ElementType.FIELD,ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
public @interface APTAnnotation {
    String author() default "byhieg";

    String date();

    String version() default "1";
}
