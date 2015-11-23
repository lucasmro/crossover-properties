package com.crossover.properties.type.matcher;

import java.util.regex.Pattern;

public class StringMatcher extends TypeMatcher {

    @Override
    protected Pattern getPattern() {
        return Pattern.compile(".*");
    }

    @Override
    protected String getName() {
        return String.class.getName();
    }
}
