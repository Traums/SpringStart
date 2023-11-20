package ru.mart.Practice.Annotations.Execution;


import ru.mart.Practice.Annotations.Custom.CustomClass;

import java.lang.annotation.Annotation;
import java.util.Arrays;

public class CustomMain {
    public static void main(String[] args) {
        CustomClass customClass = new CustomClass();
        Annotation[] testAnnotation = customClass.getClass().getAnnotations();
        customClass.getClass().isAnnotation();
        System.out.println(Arrays.toString(testAnnotation));
    }
}