package edu.wpi.cs.dss.model;

public enum EdgeSequence {
    FIRST(1),
    SECOND(2),
    THIRD(3);

    private final int value;

    EdgeSequence(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
