package org.newpackage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class CalculatorTest {
    @Test
    public void testAdd() {
        Calculator calculator = new Calculator();
        double result = calculator.add(10, 50);
        assertEquals(60, result, 0);
    }

    @Test
    public void testSubstract() {
        Calculator calculator = new Calculator();
        double result = calculator.substract(60, 50);
        assertEquals(20, result, 0);
    }

}