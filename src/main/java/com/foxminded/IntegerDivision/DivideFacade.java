package com.foxminded.IntegerDivision;

public class DivideFacade {
    private Calculator calculator;
    private Formatter form;
    
    public DivideFacade(Calculator calculator, Formatter form) {
	this.calculator = calculator;
	this.form = form;
    }
    
    public String DivideTable(long divider, long divisor) {
	return form.getForm(calculator.getMathData(divider, divisor));
    }
}
