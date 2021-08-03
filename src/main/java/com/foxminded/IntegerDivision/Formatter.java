package com.foxminded.IntegerDivision;

public class Formatter {
    private final String SPACE = "\u0020";
    private final String SEPARATING_LINE = System.lineSeparator();
    private final String MIDDLE_MACRON = "\u002d";
    private final String MINUS = "\u005F";
    private final String HIGH_MACRON = "\u02C9";
    private final int DEFAULT_STEP = 1;

    public String getForm(DataIntegerDivision data) {
	if (data == null) {
	    throw new IllegalArgumentException("data is null");
	}

	String form = formatterHead(data).concat(formatterBody(data));
	return form;
    }

    private String formatterHead(DataIntegerDivision data) {
	StringBuilder head = new StringBuilder();

	head.append(String.format("%s%d" + "|" + "%d%s", MINUS, data.getDivider(), data.getDivisor(), SEPARATING_LINE))

		.append(String.format("%s%d%s" + "|" + "%s%s", 
			SPACE.repeat(data.getOffsets().get(0) + DEFAULT_STEP),
			data.getMinuendAndSubtrahend().get(0), 
			SPACE.repeat(countSpaceForHead(data)),
			MIDDLE_MACRON.repeat(countAmountOfSymbolQuantityDependingOnSign(data)), 
			SEPARATING_LINE))

		.append(String.format("%s%s%s" + "|" + "%d%s", 
			SPACE.repeat(data.getOffsets().get(0) + DEFAULT_STEP),
			HIGH_MACRON.repeat(this.countLength(data.getMinuendAndSubtrahend().get(0))),
			SPACE.repeat(countSpaceForHead(data)),
			data.getQuantient(), SEPARATING_LINE));

	return head.toString();
    }

    private String formatterBody(DataIntegerDivision data) {
	StringBuilder body = new StringBuilder();
	int maxStep = data.getOffsets().size() - 1;

	for (int count = 1; data.getOffsets().size() > count; count++) {
	    long currentNumber = data.getMinuendAndSubtrahend().get(count);
	    int currentStep = data.getOffsets().get(count) + DEFAULT_STEP;

	    if (count % 2 == 0 && count != maxStep) {
		body.append(String.format("%s%d%s" + "%s%s" + "%s", SPACE.repeat(currentStep), currentNumber,
			SEPARATING_LINE, SPACE.repeat(currentStep), HIGH_MACRON.repeat(countLength(currentNumber)),
			SEPARATING_LINE));
	    }
	    if (count != maxStep && count % 2 != 0) {
		body.append(String.format("%s%s%d%s", SPACE.repeat(currentStep - 1), MINUS, currentNumber,
			SEPARATING_LINE));
	    }
	    if (count == maxStep) {
		body.append(String.format("%s%d", SPACE.repeat(currentStep), currentNumber));
	    }

	}

	return body.toString();
    }

    private int countSpaceForHead(DataIntegerDivision data) {
	return countLength(data.getDivider())
		- (this.countLength(data.getMinuendAndSubtrahend().get(0)) + data.getOffsets().get(0));
    }

    private int countAmountOfSymbolQuantityDependingOnSign(DataIntegerDivision data) {
	return countLength(data.getQuantient());
    }

    private int countLength(Long number) {
	return number.toString().length();
    }
}