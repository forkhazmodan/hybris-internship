package com.hybris.task.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value= ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Command {
    String regex();
    String example() default "";
    String description() default "";
}
