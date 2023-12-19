package ru.mart.Review.InterfaceSegregation.Correct;

public class Boat implements Swim{
    @Override
    public void swim() {
        System.out.println("Boat swimming");
    }
}
