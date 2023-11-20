package ru.mart.Review.ClientStorage.Object;

import lombok.extern.slf4j.Slf4j;
import ru.mart.Practice.Annotations.InjectProperty;
import ru.mart.Review.ClientStorage.ClientsStorage;
import ru.mart.Review.ClientStorage.StorageWorker;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@Slf4j
public class InjectStorageConfigurator implements StorageConfigurator {
    StorageWorker storageWorker = new StorageWorker();

    @Override
    public void configure(ClientsStorage clientsStorage, int storageSize) {
        clientsStorage.clients = storageWorker.randomFill(storageSize);
        clientsStorage.setStorageSize(storageSize);
        for (Field field : clientsStorage.getClass().getDeclaredFields()){
            //4.11.3 Как проверить наличие аннотации над классом/методом/полем класса?
            InjectProperty annotation = field.getAnnotation(InjectProperty.class);
            String path = ClassLoader.getSystemClassLoader().getResource("application.properties").getPath();
            try{
                Stream<String> lines = new BufferedReader(new FileReader(path)).lines();
                Map<String,String> propertiesMap = lines.map(line->line.split("=")).collect(Collectors.toMap(arr->arr[0], arr->arr[1]));
                if(annotation != null){
                    String value;
                    if(annotation.value().isEmpty()){
                        value = propertiesMap.get(field.getName());
                    }else{
                        value = propertiesMap.get(annotation.value());
                    }
                    field.setAccessible(true);
                    field.set(clientsStorage,value);
                }
            } catch (FileNotFoundException | IllegalAccessException e) {
                log.error("Ошибка создания аннотации");
            }
        }
    }
}
