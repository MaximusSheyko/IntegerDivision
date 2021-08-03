package com.foxminded.IntegerDivision;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    
    Calculator calculator;
    final static long DIVISOR_IS_ZERO = 0;
    final static long DEFAULT_DIVIDER = 46778;
    final static long DEFAULT_DIVIDER_NEGATIVE = -46778;
    final static long DEFAULT_DIVISOR = 47;
    final static String ALL_MINUEND_AND_SUBTRAHEND = "[423, 447, 423, 248, 235, 13]";
    final static String ALL_OFFSETS = "[0, 1, 1, 2, 2, 3]";
    final static long QUANTITY = 995;
    final static long QUANTITY_NEGATIVE = -995;


    @BeforeEach
    void init(){
        calculator = new Calculator();
    }

    @Test
    void getMathData_whenDivisorIsZero() {
        assertThrows(IllegalArgumentException.class, () -> calculator.getMathData(DEFAULT_DIVIDER,DIVISOR_IS_ZERO));
    }

    @Test
    void getMathData_NO_NULL() {
        assertNotNull(calculator.getMathData(DEFAULT_DIVIDER,DEFAULT_DIVISOR));
    }

    @Test
    void getMathData_allMinuendAndSubtrahendCheck() {
        assertEquals(ALL_MINUEND_AND_SUBTRAHEND,
                calculator.getMathData(DEFAULT_DIVIDER,DEFAULT_DIVISOR).getMinuendAndSubtrahend().toString());
    }

    @Test
    void getMathData_allOffsetsCheck() {
        assertEquals(ALL_OFFSETS,
                calculator.getMathData(DEFAULT_DIVIDER,DEFAULT_DIVISOR).getOffsets().toString());
    }

    @Test
    void getMathData_QuantityPositiveCheck() {
        assertEquals(QUANTITY, calculator.getMathData(DEFAULT_DIVIDER,DEFAULT_DIVISOR).getQuantient());
    }

    @Test
    void getMathData_QuantityNegativeCheck() {
        assertEquals(QUANTITY_NEGATIVE,
                calculator.getMathData(DEFAULT_DIVIDER_NEGATIVE,DEFAULT_DIVISOR).getQuantient());
    }
}