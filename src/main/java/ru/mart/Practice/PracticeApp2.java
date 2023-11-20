package ru.mart.Practice;

import lombok.extern.slf4j.Slf4j;
import ru.mart.Review.ClientStorage.ClientsStorage;
import ru.mart.Review.ClientStorage.Extended.ClientsStorageExtended;
import ru.mart.Review.ClientStorage.Extended.InjectByType;
import ru.mart.Review.ClientStorage.Object.InjectStorageConfigurator;
import ru.mart.Review.ClientStorage.StorageFactory;
@Slf4j
public class PracticeApp2 {
    public void run(){
        demoHeritation();
    }

    private void demoHeritation() {
        ClientsStorageExtended clientsStorageExtended = StorageFactory.getInstance().createObject(ClientsStorageExtended.class);
        clientsStorageExtended.clients.forEach(System.out::println);
        log.warn("\n\n");
        ClientsStorage clientsStorage = StorageFactory.getInstance().createObject(ClientsStorage.class);
        clientsStorage.clients.forEach(System.out::println);
        clientsStorage.getClient(1);
    }
}
