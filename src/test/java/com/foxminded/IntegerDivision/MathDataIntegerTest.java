package com.foxminded.IntegerDivision;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MathDataIntegerTest {

    MathDataInteger dataInteger;
    ArrayList offsets_actual;
    ArrayList offsets_expected;
    ArrayList digits_actual;
    ArrayList digits_expected;
    final private long DIVIDER = 10440;
    final private long DIVISOR = 11;
    final private long DIVISOR_IS_ZERO = 0;
    private final String THROWABLE_MESSAGE_IS_DIVISOR_IS_ZERO = assertThrows(IllegalArgumentException.class, () -> {
                            getDataInteger(2,DIVISOR_IS_ZERO);}).getMessage();


    private MathDataInteger getDataInteger(long divider, long divisor){
        return dataInteger = new MathDataInteger(divider,divisor);
    }

    @BeforeEach
    void init(){
        offsets_actual = new ArrayList();
        offsets_expected = new ArrayList();
        digits_expected = new ArrayList();
        digits_actual = new ArrayList();
    }

    @Test
    void initClass_whenDivisor_isZero() {
        assertNotNull(THROWABLE_MESSAGE_IS_DIVISOR_IS_ZERO);
    }

    @Test
    void getAllOffsets() {
        offsets_actual = getDataInteger(DIVIDER,DIVISOR).getAllOffsets();
        offsets_expected.add(1);
        offsets_expected.add(2);
        offsets_expected.add(2);
        offsets_expected.add(2);
        offsets_expected.add(3);
        offsets_expected.add(4);

        assertEquals(offsets_expected,offsets_actual);
    }

    @Test
    void getAllMinuendAndSubtrahend() {
        digits_actual = getDataInteger(DIVIDER,DIVISOR).getAllMinuendAndSubtrahend();
        digits_expected.add(99);
        digits_expected.add(54);
        digits_expected.add(44);
        digits_expected.add(100);
        digits_expected.add(99);
        digits_expected.add(1);

        assertEquals(offsets_expected,offsets_actual);
    }

    @Test
    void getAllMinuendAndSubtrahend_NO_NULL() {
        digits_expected = getDataInteger(DIVIDER,DIVISOR).getAllMinuendAndSubtrahend();

        assertNotNull(digits_expected);
    }

    @Test
    void getAllOffsets_NO_NULL() {
        offsets_expected = getDataInteger(DIVIDER,DIVISOR).getAllOffsets();

        assertNotNull(offsets_expected);
    }
}
