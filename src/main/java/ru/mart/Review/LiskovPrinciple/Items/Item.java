package ru.mart.Review.LiskovPrinciple.Items;

public abstract class Item {
    Item(int id,String name,int price){
        this.id = id;
        this.name = name;
        this.price = price;
    }
    protected int id;
    protected String name;
    protected int price;
    public abstract String getDescription();
    public abstract void use();
}
