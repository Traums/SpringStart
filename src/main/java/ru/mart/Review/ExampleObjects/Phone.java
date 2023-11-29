package ru.mart.Review.ExampleObjects;

public class Phone{

    private String name;
    private int price;

    public Phone(String name, int price){
        this.name=name;
        this.price=price;
    }
    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
    public void call(){
        System.out.println("Call...");
    }
    public void charge(){
        System.out.println("Charging...");
    }
}