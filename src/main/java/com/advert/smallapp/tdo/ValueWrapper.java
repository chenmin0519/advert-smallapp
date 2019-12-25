package com.advert.smallapp.tdo;

public class ValueWrapper {
    private Object value;

    public ValueWrapper(Object value) {
        this.value = value;
    }

    @SuppressWarnings("unchecked")
    public <T> T getValue() {
        return (T) value;
    }

    public boolean isNull() {
        return value == null;
    }
}
