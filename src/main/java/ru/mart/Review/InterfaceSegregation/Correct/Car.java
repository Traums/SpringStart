package ru.mart.Review.InterfaceSegregation.Correct;

public class Car implements Drive{
    @Override
    public void drive() {
        System.out.println("Car drive");
    }
}
