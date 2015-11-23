package com.crossover.properties.type.matcher;

import java.util.regex.Pattern;

public class BooleanMatcher extends TypeMatcher {

    @Override
    protected Pattern getPattern() {
        return Pattern.compile("^(?i)(true|false)$", Pattern.CASE_INSENSITIVE);
    }

    @Override
    protected String getName() {
        return Boolean.class.getName();
    }
}
