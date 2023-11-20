package ru.mart.Review.ClientStorage;

import com.github.javafaker.Faker;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.mart.Review.Client.Client;
import ru.mart.Review.Utill.Factorial;

import java.util.ArrayList;
import java.util.List;
@Component
@Scope("prototype")
public class StorageWorker {
    private void addClient(List<Client> clientList, Client client){
        clientList.add(client);
    }
    public ArrayList<Client> randomFill(int clientAmount) {
        ArrayList<Client> clientList = new ArrayList<>();
        for (int i = 0; i < clientAmount; i++) {
            new Client();
            ArrayList<String> orders = new ArrayList<>();
            orders.add(new Faker().company().name());
            addClient(clientList, Client.builder().id(i+1)
                    .name(new Faker().name().firstName())
                    .gold((int) (Math.random() * 300))
                    .isActive(new Faker().bool().bool())
                    .orders(orders)
                    .build());
        }
        return clientList;
    }
}
