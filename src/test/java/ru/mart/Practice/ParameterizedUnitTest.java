package ru.mart.Practice;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.EnumSet;
import java.util.stream.Stream;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ParameterizedUnitTest {
    @ParameterizedTest
    @ValueSource(strings = { "Hello", "World" })
    void givenString_TestNullOrNot(String word) {
        assertNotNull(word);
    }

    @ParameterizedTest
    @EnumSource(value = PizzaDeliveryStrategy.class, names = {"EXPRESS", "NORMAL"})
    void givenEnum_TestContainsOrNot(PizzaDeliveryStrategy timeUnit) {
        assertTrue(EnumSet.of(PizzaDeliveryStrategy.EXPRESS, PizzaDeliveryStrategy.NORMAL).contains(timeUnit));
    }

    @ParameterizedTest
    @MethodSource("wordDataProvider")
    void givenMethodSource_TestInputStream(String argument) {
        assertNotNull(argument);
    }

    static Stream<String> wordDataProvider() {
        return Stream.of("foo", "bar");
    }

    @ParameterizedTest
    @CsvSource({ "1, Car", "2, House", "3, Train" })
    void givenCSVSource_TestContent(int id, String word) {
        assertNotNull(id);
        assertNotNull(word);
    }
    public enum PizzaDeliveryStrategy{
        EXPRESS {
            @Override
            public void deliver(Pizza pz) {
                System.out.println("Pizza will be delivered in express mode");
            }
        },
        NORMAL {
            @Override
            public void deliver(Pizza pz) {
                System.out.println("Pizza will be delivered in normal mode");
            }
        };

        public abstract void deliver(Pizza pz);
    }
}
