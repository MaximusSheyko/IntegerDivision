package com.foxminded.IntegerDivision;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
class DivideFacadeTest {
    @Mock
    Calculator calculator;
    @Mock
    Formatter form;
    @InjectMocks
    DivideFacade facade;
    DataIntegerDivision data = new DataIntegerDivision();

    @BeforeEach
    void init() {
	calculator = mock(Calculator.class);
	form = mock(Formatter.class);
	facade = new DivideFacade(calculator, form);
    }

    @Test
    void testDivideTable_verifyCallMethodeGetMathDataAndGetForm() {
	facade.DivideTable(4, 2);

	verify(calculator, times(1)).getMathData(4, 2);
	verify(form, times(1)).getForm(any());
	verifyNoMoreInteractions(calculator);
	verifyNoMoreInteractions(form);
    }

    @Test
    void testDivideTable_verifyCallMethodeGetForm_ReturnString() {
	when(calculator.getMathData(0, 2)).thenReturn(data);
	when(form.getForm(data)).thenReturn("return");

	assertEquals("return", facade.DivideTable(0, 2));
    }

    @Test
    void testDivideTable_whenCallMethodeGetMathData_divizorIsZero() {
	when(calculator.getMathData(2, 0)).thenThrow(new IllegalArgumentException("Try divide by zero!"));

	assertThrows(Exception.class, () -> {
	    facade.DivideTable(2, 0);
	}, "Try divide by zero!");
    }

    @Test
    void testDivideTable_whenCallMethodeGetForm_inputDataIsNull() {
	when(form.getForm(null)).thenThrow(new IllegalArgumentException("data is null"));

	assertThrows(Exception.class, () -> {
	    facade.DivideTable(4, 2);
	}, "data is null");
    }
}