package ru.mart.Practice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class StringLengthTest {

    private String input;
    private int expectedLength;

    public StringLengthTest(String input, int expectedLength) {
        this.input = input;
        this.expectedLength = expectedLength;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { "hello", 5 },
                { "world", 5 },
                { "junit", 5 }
        });
    }

    @Test
    public void testLength() {
        assertEquals(expectedLength, input.length());
    }
}