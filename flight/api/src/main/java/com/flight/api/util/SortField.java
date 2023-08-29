package com.flight.api.util;

public enum SortField {

    PRICE("price"),
    DURATION("duration"),
    ASCENDING("asc"),
    DESCENDING("desc");

    private String value;

    private SortField(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
