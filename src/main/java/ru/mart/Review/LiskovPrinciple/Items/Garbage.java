package ru.mart.Review.LiskovPrinciple.Items;

import org.apache.commons.lang3.NotImplementedException;

public class Garbage extends Item {

    public Garbage(int id, String name, int price) {
        super(id, name, price);
    }

    @Override
    public String getDescription() {
        return "Бесполезный предмет";
    }

    @Override
    public void use() {
        throw new NotImplementedException();
    }
}
