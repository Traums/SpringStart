package ru.mart.Review.InterfaceSegregation.Correct;

public class Plane implements Fly{
    @Override
    public void fly() {
        System.out.println("Plane flies");
    }
}
