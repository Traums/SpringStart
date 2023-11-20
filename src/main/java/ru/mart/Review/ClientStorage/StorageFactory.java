package ru.mart.Review.ClientStorage;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import ru.mart.Review.ClientStorage.Object.InjectStorageConfigurator;

@Service
public class StorageFactory {
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
    @SneakyThrows
    public <T extends ClientsStorage> T createObject(Class<T> implClass){
        T t = create(implClass);
        configurator.configure(t,10);
        return t;
    }
    private <T> T create(Class<T> implClass) throws InstantiationException, IllegalAccessException, java.lang.reflect.InvocationTargetException, NoSuchMethodException {
        return implClass.getDeclaredConstructor().newInstance();
    }
}
