package com.foxminded.IntegerDivision;

public class Calculator implements Counter {
    private DataIntegerDivision data;
    private StringBuilder changedDivided;
    private static final int FIRST_NUMBER = 0;

    private DataIntegerDivision getData() {
	return data;
    }

    public DataIntegerDivision getMathData(long divider, long divisor) throws IllegalArgumentException {
	if (divisor == 0) {
	    throw new IllegalArgumentException("You can not divided by zero!");
	}
	long result;
	long remainder;

	result = divider / divisor;
	data = new DataIntegerDivision();
	data.setDivider(Math.abs(divider));
	data.setDivisor(Math.abs(divisor));

	if (data.getDivider() < data.getDivisor()) {
	    data.addMinuendAndSubtrahend(0);
	    data.addOffsets(0);
	} else {
	    data.setQuantity(result);
	    countMinuendAndSubtrahend();
	}
	remainder = data.getMinuendAndSubtrahend().get(data.getMinuendAndSubtrahend().size() - 1);
	data.setRemainder(remainder);
	return getData();
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
		minuend = data.getDivisor() * quantity[count];
		data.addMinuendAndSubtrahend(minuend);
		oldSubtrahend = newSubtrahend;
		stepMinuend = countOffsetForMinuend(minuend, newSubtrahend, stepSubtrahend);
		data.addOffsets(stepMinuend);
		newSubtrahend -= minuend;
		stepSubtrahend += countOffsetForSubtrahend(newSubtrahend, oldSubtrahend);
		newSubtrahend = calcSubtrahend(newSubtrahend);
		data.addOffsets(stepSubtrahend);
		data.addMinuendAndSubtrahend(newSubtrahend);
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
	    return backStep;
	}
	if (step != 0) {
	    return step + backStep;
	} else {
	    return step;
	}
    }

    private long calcSubtrahend(long subtrahend) {
	if (subtrahend > data.getDivisor()) {
	    return subtrahend;
	} else {
	    while (subtrahend < data.getDivisor() && !changedDivided.isEmpty()) {
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
	int sizeMinuend = getCountSymbols(data.getDivisor());
	changedDivided = new StringBuilder(String.valueOf(data.getDivider()));
	int firstIndex = 0;

	subtrahend = Long.parseLong(changedDivided.substring(firstIndex, sizeMinuend));
	changedDivided.delete(firstIndex, sizeMinuend);

	subtrahend = calcSubtrahend(subtrahend);
	return subtrahend;
    }

    private int[] toConvertQuantityToArray() {
	long tempQuantity = Math.abs(data.getQuantity());
	int amountOfNumbers = getCountSymbols(data.getQuantity());
	var numberQuantity = new int[amountOfNumbers];

	for (var count = 0; amountOfNumbers > count; count++) {
	    int i = (int) (tempQuantity % 10);
	    numberQuantity[count] = i;
	    tempQuantity /= 10;
	}
	return numberQuantity;
    }

    @Override
    public String toString() {
	return "Calculator{" + "data=" + this.data + '}';
    }
}
