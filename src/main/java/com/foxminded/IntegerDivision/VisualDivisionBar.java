package com.foxminded.IntegerDivision;

import java.util.ArrayList;
import java.util.Objects;

public class VisualDivisionBar implements ICounter {
    private ArrayList<Integer> steps;
    private ArrayList<Long> digits;
    private long divider;
    private long divisor;
    private long quantity;
    private final String SPACE = "\u0020";
    private final String SEPARATING_LINE = System.lineSeparator();
    private final String MIDDLE_MACRON = "\u002d";
    private final String MINUS = "\u005F";
    private final String HIGH_MACRON = "\u02C9";
    private final int DEFAULT_STEP = 1;

    public VisualDivisionBar(ArrayList<Integer> steps, ArrayList<Long> digits, long divider, long divisor,
	    long quantity) {
	setSteps(steps);
	setDigits(digits);
	setDivider(divider);
	setDivisor(divisor);
	setQuantity(quantity);
    }

    public void setSteps(ArrayList<Integer> steps) {
	if (Objects.isNull(steps) || steps.isEmpty()) {
	    throw new IllegalArgumentException("List steps offset not be null or empty");
	}
	this.steps = steps;
    }

    public void setDigits(ArrayList<Long> digits) {
	if (Objects.isNull(digits) || digits.isEmpty()) {
	    throw new IllegalArgumentException("List digits is null or empty");
	}
	this.digits = digits;
    }

    public void setDivider(long divider) {
	this.divider = divider;
    }

    public void setDivisor(long divisor) {
	if (divisor == 0) {
	    throw new IllegalArgumentException("Divisor can not be 0!");
	}
	this.divisor = divisor;
    }

    public void setQuantity(long quantity) {
	this.quantity = quantity;
    }

    public String getForm() {
	String form = formatterHead().concat(formatterBody());
	return form;
    }

    private String formatterHead() {
	String head = String.format("%s%d" + "|" + "%d%s" + "%s%d%s" + "|" + "%s%s" + "%s%s%s" + "|" + "%d%s",
		MINUS, divider, divisor, SEPARATING_LINE, SPACE.repeat(steps.get(0) + DEFAULT_STEP), digits.get(0),
		SPACE.repeat(countSpaceForHead()), MIDDLE_MACRON.repeat(countAmountOfSymbolQuantityDependingOnSign()),
		SEPARATING_LINE, SPACE.repeat(steps.get(0) + DEFAULT_STEP),
		HIGH_MACRON.repeat(getCountSymbols(digits.get(0))), SPACE.repeat(countSpaceForHead()), quantity,
		SEPARATING_LINE);

	return head;
    }

    private String formatterBody() {
	StringBuilder body = new StringBuilder();
	int maxStep = steps.size() - 1;

	for (int count = 1; steps.size() > count; count++) {
	    long currentNumber = digits.get(count);
	    int currentStep = steps.get(count) + DEFAULT_STEP;

	    if (count % 2 == 0 && count != maxStep) {
		String MinuendLine = String.format("%s%d%s" + "%s%s" + "%s", SPACE.repeat(currentStep), currentNumber,
			SEPARATING_LINE, SPACE.repeat(currentStep), HIGH_MACRON.repeat(getCountSymbols(currentNumber)),
			SEPARATING_LINE);
		body.append(MinuendLine);
	    }
	    if (count != maxStep && count % 2 != 0) {
		String SubtrahendLine = String.format("%s%s%d%s", SPACE.repeat(currentStep - 1), MINUS, currentNumber,
			SEPARATING_LINE);
		body.append(SubtrahendLine);
	    }
	    if (count == maxStep) {
		String SubtrahendLine = String.format("%s%d", SPACE.repeat(currentStep), currentNumber);
		body.append(SubtrahendLine);
	    }

	}

	return body.toString();
    }

    private int countSpaceForHead() {
	return getCountSymbols(divider) - (getCountSymbols(digits.get(0)) + steps.get(0));
    }

    private int countAmountOfSymbolQuantityDependingOnSign() {
	if (!(quantity < 0)) {
	    return getCountSymbols(quantity);
	}
	return getCountSymbols(quantity) + DEFAULT_STEP;
    }

}
