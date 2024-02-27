package ru.mart.Review.ClientStorage;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.mart.Practice.Annotations.InjectProperty;
import ru.mart.Review.Client.Client;

import java.util.ArrayList;
import java.util.Iterator;
@Component
@Scope("prototype")
public class ClientsStorage  implements Iterable<Client> {
    public ArrayList<Client> clients;
    @Setter
    @Getter
    private int storageSize;
    @InjectProperty(value = "whiskey")
    public String usage = "";
    public ClientsStorage(){
    }

    public Client getClient(int ind){
        return clients.get(ind);
    }

    @Override
    public Iterator iterator() {
        int currentIndex = 0;
        return new Iterator<Client>() {
            @Override
            public boolean hasNext() {
                return currentIndex < storageSize && clients.get(currentIndex + 1) != null;
            }

            @Override
            public Client next() {
                return clients.get(currentIndex + 1);
            }
        };
    }
}
