package com.hybris.task.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value= ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CommandParam {
    String regex() default "";
    String description() default "";
    String example() default "";
    boolean required() default false;
}
