package com.foxminded.IntegerDivision;

import java.util.ArrayList;

public class Calculator implements Counter {
    private long divider;
    private long divisor;
    private boolean isNegativeSign = false;
    private final ArrayList<Integer> allOffsets = new ArrayList<>();
    private final ArrayList<Long> allMinuendAndSubtrahend = new ArrayList<>();
    private StringBuilder changedDivided;
    private final static int FIRST_NUMBER = 0;

    public Calculator(long divider, long divisor) {
	if (divisor < 0 || divider < 0) {
	    setNegativeSign(true);
	}
	setDivider(divider);
	setDivisor(divisor);
	initMinuendAndSubtrahend();
    }

    public void setDivider(long divider) {
	this.divider = Math.abs(divider);
    }

    public long getDivider() {
	return divider;
    }

    public long getDivisor() {
	return divisor;
    }

    public void setDivisor(long divisor) {
	if (divisor == 0) {
	    throw new IllegalArgumentException("Error binary operation. Divide by zero!");
	}
	this.divisor = Math.abs(divisor);
    }

    private void setNegativeSign(boolean negativeSign) {
	isNegativeSign = negativeSign;
    }

    public long getQuantity() {
	if (!isNegativeSign) {
	    return getDivider() / getDivisor();
	}
	return getDivider() / getDivisor() * -1;
    }

    public ArrayList<Integer> getAllOffsets() {
	if (allOffsets.isEmpty()) {
	    throw new IllegalArgumentException("offsets can not be null or empty");
	}
	return allOffsets;
    }

    public ArrayList<Long> getAllMinuendAndSubtrahend() {
	if (allMinuendAndSubtrahend.isEmpty()) {
	    throw new IllegalArgumentException("list minuend and subtrahend can not be null or empty");
	}
	return allMinuendAndSubtrahend;
    }

    private void initMinuendAndSubtrahend() {
	if (getDivider() < getDivisor()) {
	    allMinuendAndSubtrahend.add(0L);
	    allMinuendAndSubtrahend.add(0L);
	    allOffsets.add(0);
	} else {
	    countMinuendAndSubtrahend();
	}
    }

    private void countMinuendAndSubtrahend() {
	int stepSubtrahend = 0;
	int stepMinuend;
	long oldSubtrahend;
	int[] quantity = toConvertQuantityToArray();
	final int sizeQuantity = quantity.length - 1;
	long newSubtrahend = calcFirstSubtrahend();
	long minuend;

	for (int count = sizeQuantity; count >= 0; count--) {
	    if (quantity[count] != 0) {
		minuend = getDivisor() * quantity[count];
		allMinuendAndSubtrahend.add(minuend);
		oldSubtrahend = newSubtrahend;
		stepMinuend = countOffsetForMinuend(minuend, newSubtrahend, stepSubtrahend);
		allOffsets.add(stepMinuend);
		newSubtrahend -= minuend;
		stepSubtrahend += countOffsetForSubtrahend(newSubtrahend, oldSubtrahend);
		newSubtrahend = calcSubtrahend(newSubtrahend);
		allOffsets.add(stepSubtrahend);
		allMinuendAndSubtrahend.add(newSubtrahend);
	    }
	}
    }

    private int countOffsetForSubtrahend(long subtrahend, long oldSubtrahend) {
	int step = Math.abs(getCountSymbols(oldSubtrahend) - (getCountSymbols(subtrahend)));

	if (changedDivided.isEmpty()) {
	    return step;
	}
	if (subtrahend == 0 && !changedDivided.isEmpty()) {
	    while (!changedDivided.isEmpty()) {
		assert changedDivided != null;
		if (changedDivided.charAt(FIRST_NUMBER) != '0') {
		    break;
		}
		++step;
		changedDivided.deleteCharAt(FIRST_NUMBER);
	    }
	    if (!changedDivided.isEmpty()) {
		return step + getCountSymbols(oldSubtrahend);
	    } else {
		return step;
	    }
	}
	return step;
    }

    private int countOffsetForMinuend(long minuend, long subtrahend, int backStep) {
	int step = getCountSymbols(subtrahend) - getCountSymbols(minuend);
	boolean isLengthSame = getCountSymbols(subtrahend) == getCountSymbols(minuend);

	if ((subtrahend - minuend == 0) || isLengthSame) {
	    return step = backStep;
	}
	if (step != 0) {
	    return step + backStep;
	} else {
	    return step;
	}
    }

    private long calcSubtrahend(long subtrahend) {
	if (subtrahend > getDivisor()) {
	    return subtrahend;
	} else {
	    while (subtrahend < getDivisor() && !changedDivided.isEmpty()) {
		subtrahend = (subtrahend * 10) + getFirstNumberDivider();
		changedDivided.deleteCharAt(FIRST_NUMBER);
	    }
	}
	return subtrahend;
    }

    private int getFirstNumberDivider() {
	final var converterToDigit = '0';
	return changedDivided.charAt(FIRST_NUMBER) - converterToDigit;
    }

    private long calcFirstSubtrahend() {
	long subtrahend;
	int sizeMinuend = getCountSymbols(getDivisor());
	changedDivided = new StringBuilder(String.valueOf(getDivider()));
	int firstIndex = 0;

	subtrahend = Long.parseLong(changedDivided.substring(firstIndex, sizeMinuend));
	changedDivided.delete(firstIndex, sizeMinuend);

	subtrahend = calcSubtrahend(subtrahend);
	return subtrahend;
    }

    public int[] toConvertQuantityToArray() {
	long tempQuantity = Math.abs(getQuantity());
	int amountOfNumbers = getCountSymbols(getQuantity());
	var numberQuantity = new int[amountOfNumbers];

	for (int count = 0; amountOfNumbers > count; count++) {
	    int i = (int) (tempQuantity % 10);
	    numberQuantity[count] = i;
	    tempQuantity /= 10;
	}
	return numberQuantity;
    }

}
