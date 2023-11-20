package ru.mart.Review.Client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.mart.Review.ClientStorage.ClientsStorage;

@SpringBootTest
public class StorageWorkerTest {
    ClientsStorage clientsStorage;
    @BeforeEach
    void init(){
        //clientsStorage = new ClientsStorage(5);
        clientsStorage.getClient(3);
    }
    @Test
    void storageIsNotEmpty(){
        for(Client client : clientsStorage.clients){
            Assertions.assertNotNull(client);
        }
    }
    @Test
    void storageIsNotEmptyUsingStream(){
        for(Client client : clientsStorage.clients){
            Assertions.assertNotNull(client);
        }
        clientsStorage.clients.stream().map(client -> {
            client.setId(1);
            return null;
        });
    }
}
