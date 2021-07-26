package com.foxminded.IntegerDivision;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntegerBarTest {
    IntegerBar integerBar;

    private final long DIVISOR_IS_ZERO = 0;
    private final long DEFAULT_DIVISOR = 4;
    private final long DEFAULT_DIVIDER = 144;
    private final String FORM_TABLE = init(DEFAULT_DIVIDER, DEFAULT_DIVISOR).showIntegerDivision();
    private final String THROWABLE_MESSAGE_DIVISOR_IS_ZERO= assertThrows(IllegalArgumentException.class, () -> {
        init(DEFAULT_DIVIDER,DIVISOR_IS_ZERO).showIntegerDivision();}).getMessage();

    private IntegerBar init(long divider, long divisor){
        return integerBar = new IntegerBar(divider,divisor);
    }

    @Test
    void showIntegerDivision_NO_NULL() {
        assertNotNull(FORM_TABLE);
    }

    @Test
    void showIntegerDivision_whenDivisorInZero() {
        assertNotNull(THROWABLE_MESSAGE_DIVISOR_IS_ZERO);
    }

}
