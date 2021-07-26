package com.foxminded.IntegerDivision;

public class IntegerBar {
    private long divider;
    private long divisor;

    public IntegerBar(long divider, long divisor) {
        setDivider(divider);
        setDivisor(divisor);
    }

    public void setDivider(long divider) {
        this.divider = divider;
    }

    public void setDivisor(long divisor) {
        if ( divisor == 0 ){
            throw new IllegalArgumentException("Divisor is 0");
        }
        this.divisor = divisor;
    }

    public String showIntegerDivision (){
        MathDataInteger data;
        VisualDivisionBar form;

        data = new MathDataInteger(divider,divisor);
        form = new VisualDivisionBar(data.getAllOffsets(),data.getAllMinuendAndSubtrahend(),
                data.getDivider(), data.getDivisor(), data.getQuantity());

        return form.getForm();
    }

}
