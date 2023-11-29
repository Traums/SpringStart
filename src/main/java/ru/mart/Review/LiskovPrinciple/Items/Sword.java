package ru.mart.Review.LiskovPrinciple.Items;

public class Sword extends Item implements Usable{
    public Sword(int id, String name, int price){
        super(id,name,price);
    }
    @Override
    public String getDescription() {
        return "Новый меч";
    }

    @Override
    public void use() {
        System.out.println("Взмах мечом");
    }
}
