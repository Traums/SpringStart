package ru.mart.Review.FunctionalInterface;
//4.5.1 Что такое функциональные интерфейсы? В каких случаях вы можете подставить лямбда выражение вместо интерфейса?
public interface MyFunction<T, R> {
    R apply(T t);
}
