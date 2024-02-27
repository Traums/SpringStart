package ru.mart.Review.Utill;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.mart.Review.Exceptions.FactorialException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class FactorialTest {
    @Test
    public void FactorialPositiveTest() throws FactorialException {
        assertEquals(120,Factorial.getFactorial(5));
    }
    @Test
    public void FactorialExceptionTest() {
        assertThrows(FactorialException.class,
                ()->{Factorial.getFactorial(-4);
        });
    }

}
