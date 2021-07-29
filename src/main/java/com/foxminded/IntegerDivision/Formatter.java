package com.foxminded.IntegerDivision;

public class Formatter implements Counter {
    private static final String SPACE = "\u0020";
    private static final String SEPARATING_LINE = System.lineSeparator();
    private static final String MIDDLE_MACRON = "\u002d";
    private static final String MINUS = "\u005F";
    private static final String HIGH_MACRON = "\u02C9";
    private static final int DEFAULT_STEP = 1;

    public String getForm(DataIntegerDivision data) {
	if (data == null) {
	    throw new IllegalArgumentException("data is null");
	}

	String form = formatterHead(data).concat(formatterBody(data));
	return form;
    }

    private String formatterHead(DataIntegerDivision data) {
	long divider = data.getDivider();
	long divisor = data.getDivisor();

	String head = String.format("%s%d" + "|" + "%d%s" + "%s%d%s" + "|" + "%s%s" + "%s%s%s" + "|" + "%d%s", MINUS,
		divider, divisor, SEPARATING_LINE, SPACE.repeat(data.getOffsets().get(0) + DEFAULT_STEP),
		data.getMinuendAndSubtrahend().get(0), SPACE.repeat(countSpaceForHead(data)),
		MIDDLE_MACRON.repeat(countAmountOfSymbolQuantityDependingOnSign(data)), SEPARATING_LINE,
		SPACE.repeat(data.getOffsets().get(0) + DEFAULT_STEP),
		HIGH_MACRON.repeat(getCountSymbols(data.getMinuendAndSubtrahend().get(0))),
		SPACE.repeat(countSpaceForHead(data)), data.getQuantity(), SEPARATING_LINE);

	return head;
    }

    private String formatterBody(DataIntegerDivision data) {
	StringBuilder body = new StringBuilder();
	int maxStep = data.getOffsets().size() - 1;

	for (int count = 1; data.getOffsets().size() > count; count++) {
	    long currentNumber = data.getMinuendAndSubtrahend().get(count);
	    int currentStep = data.getOffsets().get(count) + DEFAULT_STEP;

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

    private int countSpaceForHead(DataIntegerDivision data) {
	return getCountSymbols(data.getDivider())
		- (getCountSymbols(data.getMinuendAndSubtrahend().get(0)) + data.getOffsets().get(0));
    }

    private int countAmountOfSymbolQuantityDependingOnSign(DataIntegerDivision data) {
	if (!(data.getQuantity() < 0)) {
	    return getCountSymbols(data.getQuantity());
	}
	return getCountSymbols(data.getQuantity()) + DEFAULT_STEP;
    }
}
