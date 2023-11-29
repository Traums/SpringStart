package ru.mart.Review.LiskovPrinciple.Utill;

import ru.mart.Review.LiskovPrinciple.Exception.NotUsableItem;
import ru.mart.Review.LiskovPrinciple.Items.Item;
import ru.mart.Review.LiskovPrinciple.Items.Usable;

import java.util.List;

public class ItemListWorker{
    public static void addUsableItem(List<Item> usableItems,Item item) throws NotUsableItem {
        if(item instanceof Usable){
            usableItems.add(item);
        }else{
            throw new NotUsableItem("Объект " + item.getClass() + " не может быть добавлен!");
        }
    }
}
