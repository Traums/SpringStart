package ru.mart.Review.LiskovPrinciple;

import ru.mart.Review.LiskovPrinciple.Exception.NotUsableItem;
import ru.mart.Review.LiskovPrinciple.Items.*;
import ru.mart.Review.LiskovPrinciple.Utill.ItemListWorker;

import java.util.ArrayList;
import java.util.List;

public class LiskovMain {
    public static void main(String[] args){
        //Неправильный вариант
        incorrectExample();
        //Соблюдение принципа
        correctExample();
    }

    private static void correctExample() {
        Item sword = new Sword(1,"Экскалибур",40);
        Item shield = new Shield(1,"Дублёный щит",30);
        Item rock = new Garbage(1,"Камень",1);

        List<Item> usableItems = new ArrayList<>();
        try{
            ItemListWorker.addUsableItem(usableItems,sword);
            ItemListWorker.addUsableItem(usableItems,shield);
            ItemListWorker.addUsableItem(usableItems,rock);
        } catch (NotUsableItem e) {
            System.out.println("Ошибка создания списка предметов для использования" + e.getMessage());
        }
        usableItems.add(sword);
        usableItems.add(shield);
        usableItems.add(rock);

        usableItems.stream().filter(a->a instanceof Usable).forEach(Item::use);
    }

    private static void incorrectExample() {
        Item sword = new Sword(1,"Экскалибур",40);
        Item shield = new Shield(1,"Дублёный щит",30);
        Item rock = new Garbage(1,"Камень",1);
        List<Item> itemList = new ArrayList<>();
        itemList.add(sword);
        itemList.add(shield);
        itemList.add(rock);
        //Ошибка
        for(Item item:itemList){
            item.use();
        }
    }
}
