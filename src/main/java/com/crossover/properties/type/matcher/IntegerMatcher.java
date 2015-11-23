package com.crossover.properties.type.matcher;

import java.util.regex.Pattern;

public class IntegerMatcher extends TypeMatcher {

    @Override
    protected Pattern getPattern() {
        return Pattern.compile("^-?\\d+(\\d+)?$");
    }

    @Override
    protected String getName() {
        return Integer.class.getName();
    }
}
