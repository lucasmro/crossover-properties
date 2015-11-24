package com.crossover.properties.type.matcher;

import java.util.regex.Pattern;

public abstract class TypeMatcher {

    private TypeMatcher nextMatcher;

    public void setNextMatcher(TypeMatcher next) {
        if (null == this.nextMatcher) {
            this.nextMatcher = next;
        } else {
            this.nextMatcher.setNextMatcher(next);
        }
    }

    public String getType(String input) {
        if (matches(input)) {
            return getName();
        }

        return nextMatcher.getType(input);
    }

    protected abstract Pattern getPattern();

    protected abstract String getName();

    private boolean matches(String input) {
        return getPattern().matcher(input).matches();
    }
}
