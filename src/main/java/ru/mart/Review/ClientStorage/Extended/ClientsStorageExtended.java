package ru.mart.Review.ClientStorage.Extended;

import lombok.Data;
import lombok.Getter;
import ru.mart.Practice.Annotations.InjectProperty;
import ru.mart.Review.ClientStorage.ClientsStorage;
@Getter
public class ClientsStorageExtended extends ClientsStorage {
    private String extendedField = "Test";
    public String annotationValue;
}
