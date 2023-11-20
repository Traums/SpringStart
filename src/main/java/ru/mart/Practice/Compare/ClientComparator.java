package ru.mart.Practice.Compare;

import ru.mart.Review.Client.Client;

import java.util.Comparator;

public class ClientComparator implements Comparator<Client> {
    //От меньшего к большему
    @Override
    public int compare(Client o1, Client o2) {
        if(o1.getGold() < o2.getGold()){
            return -1;
        }
        if(o1.getGold().equals(o2.getGold())){
            return 0;
        }
        return 1;
    }
}
