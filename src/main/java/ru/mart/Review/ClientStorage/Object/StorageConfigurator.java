package ru.mart.Review.ClientStorage.Object;

import ru.mart.Review.ClientStorage.ClientsStorage;

public interface StorageConfigurator {
    void configure(ClientsStorage clientsStorage, int storageSize);
}
