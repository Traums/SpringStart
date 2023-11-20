package ru.mart.Review.FunctionalInterface;
//4.5.3 Расскажите про встроенные функциональные интерфейсы Predicate, Consumer, Function, Supplier, UnaryOperator, BinaryOperator
public interface MyConsumer<T> {
    void accept(T t);
}
