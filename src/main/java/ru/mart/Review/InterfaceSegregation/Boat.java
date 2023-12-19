package ru.mart.Review.InterfaceSegregation;

public class Boat implements Vehicle{

    @Override
    public void drive() {

    }

    @Override
    public void fly() {

    }

    @Override
    public void swim() {
        System.out.println("Swim boat");
    }
}
