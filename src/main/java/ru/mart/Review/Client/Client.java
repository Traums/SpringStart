package ru.mart.Review.Client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Client implements Comparable<Client>{
    private Integer id;
    private String name;
    private Integer gold;
    private boolean isActive;
    private ArrayList<String> orders;
    public String getData(){
        return getId() + " " + getName() + " " + getGold() + " " + isActive() + "\n";
    }

    public ArrayList<String> getOrders(){
        return orders;
    }
    @Override
    public int compareTo(Client c) {
        //От большего к меньшему
        if (gold < c.getGold())
            return -1;
        if (gold.equals(c.getGold()))
            return 0;
        return 1;
    }
}
