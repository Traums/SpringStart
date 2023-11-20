package ru.mart.Review.FunctionalInterface.Exception;
@FunctionalInterface
public interface MyExceptionFunction<T, R, E extends Exception> {
    R apply(T t) throws E;
}
