package ru.mart.Review.ClientStorage.Extended;

import ru.mart.Review.ClientStorage.StorageFactory;

import java.lang.reflect.Field;

public class InjectByTypeConfigurator {
    public void configure(Object t) throws IllegalAccessException {
        for(Field field : t.getClass().getDeclaredFields()){
            if(field.isAnnotationPresent(InjectByType.class)){
                field.setAccessible(true);
                Object object = StorageFactory.getInstance().createObject(field.getType().getModifiers());
                field.set(t,object);
            }
        }
    }
}
