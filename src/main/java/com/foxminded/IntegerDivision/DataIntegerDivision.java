package com.foxminded.IntegerDivision;

import java.util.ArrayList;

public class DataIntegerDivision {
    private long divider;
    private long divisor;
    private long quantity;
    private long remainder;
    private ArrayList<Long> minuendAndSubtrahend = new ArrayList<>();
    private ArrayList<Integer> offsets = new ArrayList<>();

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

    public long getQuantity() {
	return quantity;
    }

    public void setQuantity(long quantity) {
	this.quantity = quantity;
    }

    public ArrayList<Long> getMinuendAndSubtrahend() {
	return minuendAndSubtrahend;
    }

    public void setMinuendAndSubtrahend(ArrayList<Long> minuendAndSubtrahend) {
	this.minuendAndSubtrahend = minuendAndSubtrahend;
    }

    public void addMinuendAndSubtrahend(long number) {
	this.minuendAndSubtrahend.add(number);
    }

    public ArrayList<Integer> getOffsets() {
	return offsets;
    }

    public void addOffsets(int offset) {
	this.offsets.add(offset);
    }

    public void setOffsets(ArrayList<Integer> offsets) {
	this.offsets = offsets;
    }

    @Override
    public String toString() {
	return "DataIntegerDivision{" + "divider=" + divider + ", divisor=" + divisor + ", quantity=" + quantity
		+ ", minuendAndSubtrahend=" + minuendAndSubtrahend + ", offsets=" + offsets + '}';
    }

}
