package ru.mart.Practice.Annotations.Custom;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//@Target({ElementType.METHOD,ElementType.CONSTRUCTOR})
//@Retention(RetentionPolicy.RUNTIME)
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomAnnotations {
    CustomAnnotation[] value();
}
