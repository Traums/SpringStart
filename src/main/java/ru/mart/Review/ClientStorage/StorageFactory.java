package ru.mart.Review.ClientStorage;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import ru.mart.Review.ClientStorage.Object.InjectStorageConfigurator;

@Service
public class StorageFactory {
    //2.2 O - Open-Closed Principle
    //1.4 Рассказать про отношения между классами?
    private static final StorageFactory factoryInstance = new StorageFactory();
    private final InjectStorageConfigurator configurator = new InjectStorageConfigurator();

    public static StorageFactory getInstance(){
        return factoryInstance;
    }
    public ClientsStorage createObject(int storageSize) {
        ClientsStorage clientsStorage = new ClientsStorage();
        configurator.configure(clientsStorage,storageSize);
        return clientsStorage;
    }
    public ClientsStorage createObject(){
        ClientsStorage clientsStorage = new ClientsStorage();
        configurator.configure(clientsStorage,10);
        return clientsStorage;
    }
    //1.2 Рассказать про наследование, инкапсуляцию, полиморфизм
    @SneakyThrows
    public <T extends ClientsStorage> T createObject(Class<T> implClass){
        T t = create(implClass);
        configurator.configure(t,10); //2.1 S - Single Responsibility Principle
        return t;
    }
    private <T> T create(Class<T> implClass) throws InstantiationException, IllegalAccessException, java.lang.reflect.InvocationTargetException, NoSuchMethodException {
        return implClass.getDeclaredConstructor().newInstance();
    }
}
