package com.zeygame.javamvvm.model;

public class RatingModel {
    private String Source;
    private String Value;

    public RatingModel(String source, String value) {
        Source = source;
        Value = value;
    }

    public String getSource() {
        return Source;
    }

    public String getValue() {
        return Value;
    }
}
