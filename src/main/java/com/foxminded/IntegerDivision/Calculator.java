package com.foxminded.IntegerDivision;

import java.util.ArrayList;
import java.util.Collections;

public class Calculator {
    private ArrayList<Integer> digitsQuotient = new ArrayList<>();
    private ArrayList<Integer> digitsDivider = new ArrayList<>();

    public DataIntegerDivision getMathData(long divider, long divisor) {
	if (divisor == 0) {
	    throw new IllegalArgumentException("Try divide by zero!");
	}

	DataIntegerDivision data = new DataIntegerDivision();
	long minuend = 0;
	long subtrahend = 0;
	int stepMinuend = 0;
	int stepSubtrahend = 0;

	data.setQuantient(divider / divisor);
	data.setDivider(Math.abs(divider));
	data.setDivisor(Math.abs(divisor));
	digitsQuotient = splitToDigits(Math.abs(data.getQuantient()));
	digitsDivider = splitToDigits(data.getDivider());

	if (data.getDivider() < data.getDivisor()) {
	    data.setQuantient(0);
	    data.addOffsets(0);
	    data.addMinuendAndSubtrahend(0);
	} else {
	    minuend = countFirstMinuend(data.getDivisor());

	    for (int digit : digitsQuotient) {
		if (digit != 0) {
		    subtrahend = data.getDivisor() * digit;
		    data.addMinuendAndSubtrahend(subtrahend);
		    stepSubtrahend = countStepOfSubtrahend(subtrahend, minuend, stepMinuend);
		    data.addOffsets(stepSubtrahend);

		    stepMinuend += countStepOfMinuend(subtrahend, minuend);
		    data.addOffsets(stepMinuend);
		    minuend = countMinuend(minuend, data.getDivisor());
		    data.addMinuendAndSubtrahend(minuend);
		}
	    }
	}

	return data;
    }

    private ArrayList<Integer> splitToDigits(long number) {
	number = Math.abs(number);
	ArrayList<Integer> digits = new ArrayList();

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

    private long countFirstMinuend(long divisor) {
	long minuend = digitsDivider.get(0);
	digitsDivider.remove(0);

	while (!digitsDivider.isEmpty()) {
	    if (divisor <= minuend) {
		return minuend;
	    } else {
		minuend = (minuend * 10) + digitsDivider.get(0);
		digitsDivider.remove(0);
	    }
	}

	return minuend;
    }

    public int countStepOfSubtrahend(long subtrahend, long minuend, int stepOfMinuend) {
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

    public int countStepOfMinuend(long subtrahend, long minuend) {
	long nextMinuend = minuend - subtrahend;
	boolean isDividerZero = digitsDivider.stream().allMatch(number -> number.equals(0));
	int step = Math.abs(countAmountOfDigits(minuend) - (countAmountOfDigits(nextMinuend)));

	if (nextMinuend != 0) {
	    return step;
	}
	if (isDividerZero || digitsDivider.isEmpty()) {
	    return step;
	}
	if (!digitsDivider.isEmpty()) {
	    int countZero = 0;

	    for (int number : digitsDivider) {
		if (number != 0) {
		    break;
		}
		++countZero;
	    }
	    step = countZero + countAmountOfDigits(minuend);
	    return step;
	} else {
	    return 0;
	}
    }

    private long countMinuend(long minuend, long divisor) {
	minuend = (minuend % divisor);

	if (minuend < divisor) {
	    while (!digitsDivider.isEmpty() && minuend < divisor) {
		minuend = (minuend * 10) + digitsDivider.get(0);
		digitsDivider.remove(0);
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
