package ru.mart.Practice.Annotations;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
//4.11.2 Как написать свою аннотацию?
@Retention(RUNTIME)
public @interface InjectProperty {
    String value() default "";
}