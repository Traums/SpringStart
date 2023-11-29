package ru.mart.Review.LiskovPrinciple.Items;

public class Shield extends Item implements Usable{

    public Shield(int id, String name, int price) {
        super(id, name, price);
    }

    @Override
    public String getDescription() {
        return "Новый щит";
    }

    @Override
    public void use() {
        System.out.println("Оборонительная стойка");
    }
}
