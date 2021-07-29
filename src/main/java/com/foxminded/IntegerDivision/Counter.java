package com.foxminded.IntegerDivision;

public interface Counter {
    default int getCountSymbols(long digit) {
	int count = (digit == 0) ? 1 : 0;

	while (digit != 0) {
	    count++;
	    digit /= 10;
	}
	return count;
    }
}
