package com.crossover.trial.properties.type.matcher;

import java.util.regex.Pattern;

public class DoubleMatcher extends TypeMatcher {

    @Override
    protected Pattern getPattern() {
        return Pattern.compile("[-+]?[0-9]*\\.[0-9]+([eE][-+]?[0-9]+)?");
    }

    @Override
    protected String getName() {
        return Double.class.getName();
    }
}
