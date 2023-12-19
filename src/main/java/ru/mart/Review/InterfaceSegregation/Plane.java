package ru.mart.Review.InterfaceSegregation;

import org.apache.commons.lang3.NotImplementedException;

public class Plane implements Vehicle{

    @Override
    public void drive() {
        throw new NotImplementedException();
    }

    @Override
    public void fly() {
        System.out.println("Plane flies");
    }

    @Override
    public void swim() {
        throw new NotImplementedException();
    }
}
