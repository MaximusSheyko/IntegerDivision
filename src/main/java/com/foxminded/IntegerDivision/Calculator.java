package com.foxminded.IntegerDivision;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Calculator {
    public DataIntegerDivision getMathData(long divider, long divisor) {
	if (divisor == 0) {
	    throw new IllegalArgumentException("Try divide by zero!");
	}
	
	List<Integer> digitsQuotient = new ArrayList<>();
	var data = new DataIntegerDivision();
	long minuend = 0;
	long subtrahend = 0;
	int stepMinuend = 0;
	int stepSubtrahend = 0;

	data.setQuantient(divider / divisor);
	data.setDivider(Math.abs(divider));
	data.setDivisor(Math.abs(divisor));
	digitsQuotient = splitToDigits(Math.abs(data.getQuantient())).stream()
		.filter(number -> number != 0)
		.toList();
	data.setDigitsDivider(splitToDigits(data.getDivider()));

	if (data.getDivider() < data.getDivisor()) {
	    data.setQuantient(0);
	    data.addOffsets(0);
	    data.addMinuendAndSubtrahend(0);
	} else {
	    minuend = countFirstMinuend(data.getDivisor(), data);

	    for (int digit : digitsQuotient) {
		subtrahend = data.getDivisor() * digit;
		data.addMinuendAndSubtrahend(subtrahend);
		stepSubtrahend = countStepOfSubtrahend(subtrahend, minuend, stepMinuend);
		data.addOffsets(stepSubtrahend);

		stepMinuend += countStepOfMinuend(subtrahend, minuend, data);
		data.addOffsets(stepMinuend);
		minuend = countMinuend(minuend, data.getDivisor(), data);
		data.addMinuendAndSubtrahend(minuend);
	    }
	}

	return data;
    }

    private List<Integer> splitToDigits(long number) {
	number = Math.abs(number);
	List<Integer> digits = new ArrayList<>();

	if (number == 0) {
	    digits.add((int) number);
	} else {
	    while (number != 0) {
		int digit = (int) (number % 10);
		digits.add(digit);
		number /= 10;
	    }
	}

	Collections.reverse(digits);
	return digits;
    }

    private long countFirstMinuend(long divisor, DataIntegerDivision data) {
	long minuend = data.getDigitsDivider().get(0);
	data.getDigitsDivider().remove(0);

	while (!data.getDigitsDivider().isEmpty()) {
	    if (divisor <= minuend) {
		return minuend;
	    } else {
		minuend = (minuend * 10) + data.getDigitsDivider().get(0);
		data.getDigitsDivider().remove(0);
	    }
	}

	return minuend;
    }

    private int countStepOfSubtrahend(long subtrahend, long minuend, int stepOfMinuend) {
	int step = countAmountOfDigits(minuend) - countAmountOfDigits(subtrahend);
	boolean isLengthSame = countAmountOfDigits(subtrahend) == countAmountOfDigits(minuend);

	if ((subtrahend - minuend == 0) || isLengthSame) {
	    return stepOfMinuend;
	}
	if (step != 0) {
	    return step + stepOfMinuend;
	} else {
	    return step;
	}
    }

    private int countStepOfMinuend(long subtrahend, long minuend, DataIntegerDivision data) {
	int countZero = 0;
	long nextMinuend = minuend - subtrahend;
	boolean isDividerZero = data.getDigitsDivider().stream()
		.allMatch(number -> number.equals(0));
	int step;

	if (nextMinuend != 0 || isDividerZero || data.getDigitsDivider().isEmpty()) {
	    step = Math.abs(countAmountOfDigits(minuend) - (countAmountOfDigits(nextMinuend)));
	} else {
	    for (int number : data.getDigitsDivider()) {
		if (number != 0) {
		    break;
		}
		++countZero;
	    }
	    step = countZero + countAmountOfDigits(minuend);
	}

	return step;
    }

    private long countMinuend(long minuend, long divisor, DataIntegerDivision data) {
	minuend = (minuend % divisor);

	if (minuend < divisor) {
	    while (!data.getDigitsDivider().isEmpty() && minuend < divisor) {
		minuend = (minuend * 10) + data.getDigitsDivider().get(0);
		data.getDigitsDivider().remove(0);
	    }
	    return minuend;
	}
	return minuend;
    }

    private int countAmountOfDigits(long digit) {
	int count = (digit == 0) ? 1 : 0;

	while (digit != 0) {
	    count++;
	    digit /= 10;
	}
	return count;
    }
}
