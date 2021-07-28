package com.foxminded.IntegerDivision;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FormatterTest {

        Calculator dataInteger;
        Formatter form;

        private final String SEPARATOR_LINE = System.lineSeparator();
        private final long DIVISOR_IS_ZERO = 0;
        private final long DEFAULT_DIVISOR = 4;
        private final long DIVIDER_IS_GREATER = 144;
        private final long DIVIDER_IS_GREATER_IS_NEGATIVE = -144;
        private final String TABLE_DIVIDER_IS_ZERO_EXPECTED = String.format("_0|%d%2$s" + " 0|-%2$s" + " ˉ|0%2$s",
                        DEFAULT_DIVISOR, SEPARATOR_LINE);
        private final String TABLE_DIVIDER_IS_ZERO_ACTUAL = getForm(DIVISOR_IS_ZERO, DEFAULT_DIVISOR);
        private final String TABLE_DIVIDER_IS_GREATER_EXPECTED = String.format("_144|%d%2$s" + " 12 |--%2$s"
                        + " ˉˉ |36%2$s" + " _24%2$s" + "  24%2$s" +"  ˉˉ%2$s" + "   0",
                        DEFAULT_DIVISOR, SEPARATOR_LINE);
        private final String TABLE_DIVIDER_IS_GREATER_ACTUAL = getForm( DIVIDER_IS_GREATER, DEFAULT_DIVISOR);
        private final String TABLE_DIVIDER_OR_DIVISOR_HAVE_NEGATIVE_SIGN_EXPECTED = String.format("_144|%d%2$s" +
                        " 12 |---%2$s" + " ˉˉ |-36%2$s" + " _24%2$s" + "  24%2$s" +"  ˉˉ%2$s" + "   0",
                        DEFAULT_DIVISOR, SEPARATOR_LINE);
        private final String TABLE_DIVIDER_OR_DIVISOR_HAVE_NEGATIVE_SIGN_ACTUAL = getForm( DIVIDER_IS_GREATER_IS_NEGATIVE,
                        DEFAULT_DIVISOR);




    private String getForm(long divider, long divisor){
        dataInteger = new Calculator(divider,divisor);
        form = new Formatter(dataInteger.getAllOffsets(),dataInteger.getAllMinuendAndSubtrahend(),
                dataInteger.getDivider(), dataInteger.getDivisor(), dataInteger.getQuantity());
        return form.getForm();
    }

    @Test
    void getForm_whenDividerIsLessDivisor() {
        assertEquals(TABLE_DIVIDER_IS_ZERO_EXPECTED,TABLE_DIVIDER_IS_ZERO_ACTUAL);
    }

    @Test
    void getForm_whenDividerIsGreaterDivisor() {
        assertEquals(TABLE_DIVIDER_IS_GREATER_EXPECTED,TABLE_DIVIDER_IS_GREATER_ACTUAL);
    }

    @Test
    void getForm_whenDividerOrDivisorHaveNegativeSign() {
        assertEquals(TABLE_DIVIDER_OR_DIVISOR_HAVE_NEGATIVE_SIGN_EXPECTED,
                TABLE_DIVIDER_OR_DIVISOR_HAVE_NEGATIVE_SIGN_ACTUAL);
    }

    @Test
    void getForm_NO_NULL() {
        assertNotNull(getForm(DIVIDER_IS_GREATER, DEFAULT_DIVISOR));
    }
}
