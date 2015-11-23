package com.crossover.properties.type.matcher;

import java.util.regex.Pattern;

public abstract class TypeMatcher {

    private TypeMatcher nextMatcher;

    public void setNextMatcher(TypeMatcher nextMatcher) {
        this.nextMatcher = nextMatcher;
    }

    public String getType(String input) {
        if (matches(input)) {
            return getName();
        }

        return nextMatcher.getType(input);
    }

    abstract protected Pattern getPattern();

    abstract protected String getName();

    private boolean matches(String input) {
        return getPattern().matcher(input).matches();
    }
}
