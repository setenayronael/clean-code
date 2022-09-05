package com.b.simple.design.business.student;

public enum Grade {
    A("A", 91, 100),
    B("B", 51, 90),
    C("C", 0, 50);

    private final String value;
    private final int upperLimit;
    private final int lowerLimit;

    public String getValue() {
        return value;
    }

    public int getUpperLimit() {
        return upperLimit;
    }

    public int getLowerLimit() {
        return lowerLimit;
    }

    Grade(String value, Integer lowerLimit, Integer upperLimit) {
        this.value = value;
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
    }
}