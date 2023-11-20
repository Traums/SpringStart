package ru.mart.Practice.Annotations.Custom;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE,ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
//@Repeatable(CustomAnnotations.class)
public @interface CustomAnnotation {
   boolean run() default true;
   int miles() default 3;
   int i() default 4;

   String value();
}
