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
    void testDivideTable_verifyCallMethodeGetMathData() {
	facade.DivideTable(4, 2);
	
	verify(calculator).getMathData(4, 2);
	verifyNoMoreInteractions(calculator);
    }
    
    @Test
    void testDivideTable_verifyCallMethodeGetForm() {
	when(calculator.getMathData(0, 2)).thenReturn(data);
	when(form.getForm(data)).thenReturn("return");
	
	assertEquals("return",facade.DivideTable(0, 2));
   	
   	verify(calculator).getMathData(0, 2);
   	verify(form).getForm(data);
   	verifyNoMoreInteractions(form);
       }
    
    @Test
    void testDivideTable_whenCallMethodeGetMathData_divizorIsZero() {
	when(calculator.getMathData(2, 0)).thenThrow(new IllegalArgumentException("Try divide by zero!"));
	
	assertThrows(Exception.class, () -> {facade.DivideTable(2, 0);}, "Try divide by zero!");   
	    
   	verify(calculator).getMathData(2, 0);
   	verifyNoMoreInteractions(calculator);
       }
    
    @Test
    void testDivideTable_whenCallMethodeGetForm_inputDataIsNull() {
	when(form.getForm(null)).thenThrow(new IllegalArgumentException("data is null"));
	
	assertThrows(Exception.class, () -> {facade.DivideTable(4, 2);}, "data is null");   
	    
   	verify(form).getForm(null);
   	verifyNoMoreInteractions(form);
       }
}

