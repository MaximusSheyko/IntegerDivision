package com.foxminded.IntegerDivision;

public class DivideFacade {
    private Calculator calculator = new Calculator();
    private Formatter form = new Formatter();
    
    public String DivideTable(long divider, long divisor) {
	return form.getForm(calculator.getMathData(divider, divisor));
    }
}
