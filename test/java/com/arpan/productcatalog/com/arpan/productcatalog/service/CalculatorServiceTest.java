package com.arpan.productcatalog.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorServiceTest {

    CalculatorService calculator;

    @BeforeEach
    void setup() {
        calculator = new CalculatorService();
    }

    @Test
    void testSum() {
        int expectedResult = 5;
        int actualResult = calculator.doSum(3, 2);
        assertEquals(expectedResult, actualResult);
    }

}