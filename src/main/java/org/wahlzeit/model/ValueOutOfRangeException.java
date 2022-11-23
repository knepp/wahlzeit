package org.wahlzeit.model;

public class ValueOutOfRangeException extends Exception {
    public ValueOutOfRangeException(String errMessage) {
        super(errMessage);
    }
}
