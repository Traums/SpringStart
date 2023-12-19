package ru.mart.Review.InterfaceSegregation;

import net.bytebuddy.implementation.bytecode.Throw;
import org.apache.commons.lang3.NotImplementedException;

public class Car implements Vehicle{

    @Override
    public void drive() {
        System.out.println("Car drive");
    }

    @Override
    public void fly() {
        throw new NotImplementedException();
    }

    @Override
    public void swim() {
        throw new NotImplementedException();
    }
}
