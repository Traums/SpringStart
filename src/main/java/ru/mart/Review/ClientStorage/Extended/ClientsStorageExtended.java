package ru.mart.Review.ClientStorage.Extended;

import lombok.Data;
import lombok.Getter;
import ru.mart.Practice.Annotations.InjectProperty;
import ru.mart.Review.Client.Client;
import ru.mart.Review.ClientStorage.ClientsStorage;
@Getter
public class ClientsStorageExtended extends ClientsStorage {
    private String extendedField = "Test";
    public String annotationValue;

    @Override
    public Client getClient(int ind2) {
        return super.getClient(ind2);
    }
}
