package com.crossover.trial.properties.type.matcher;

public class TypeMatcherBuilder {

    public TypeMatcher buildDefault() {
        TypeMatcher matcher = new BooleanMatcher();
        matcher.setNextMatcher(new IntegerMatcher());
        matcher.setNextMatcher(new DoubleMatcher());
        matcher.setNextMatcher(new StringMatcher());

        return matcher;
    }
}
