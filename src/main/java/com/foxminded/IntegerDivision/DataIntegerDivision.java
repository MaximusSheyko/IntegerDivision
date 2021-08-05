package com.foxminded.IntegerDivision;

import java.util.ArrayList;
import java.util.List;

public class DataIntegerDivision {
    private long divider;
    private long divisor;
    private long quotient;
    private long remainder;
    private List<Long> minuendAndSubtrahend = new ArrayList<>();
    private List<Integer> offsets = new ArrayList<>();

    public long getDivider() {
	return divider;
    }

    public void setDivider(long divider) {
	this.divider = divider;
    }

    public long getDivisor() {
	return divisor;
    }

    public void setDivisor(long divisor) {
	this.divisor = divisor;
    }

    public long getRemainder() {
	return remainder;
    }

    public void setRemainder(long remainder) {
	this.remainder = remainder;
    }

    public long getQuantient() {
	return quotient;
    }

    public void setQuantient(long quantity) {
	this.quotient = quantity;
    }

    public List<Long> getMinuendAndSubtrahend() {
	return minuendAndSubtrahend;
    }

    public void setMinuendAndSubtrahend(List<Long> minuendAndSubtrahend) {
	this.minuendAndSubtrahend = minuendAndSubtrahend;
    }

    public void addMinuendAndSubtrahend(long number) {
	this.minuendAndSubtrahend.add(number);
    }

    public List<Integer> getOffsets() {
	return offsets;
    }

    public void addOffsets(int offset) {
	this.offsets.add(offset);
    }

    public void setOffsets(List<Integer> offsets) {
	this.offsets = offsets;
    }

    @Override
    public String toString() {
	return "DataIntegerDivision{" + "divider=" + divider + ", divisor=" + divisor + ", quotient=" + quotient
		+ ", minuendAndSubtrahend=" + minuendAndSubtrahend + ", offsets=" + offsets + '}';
    }
}
