package com.crossover.trial.properties.type.matcher;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.amazonaws.regions.Regions;

public class AwsRegionMatcher extends TypeMatcher {

    @Override
    protected Pattern getPattern() {
        String regions = Arrays.asList(Regions.values()).stream().map(s -> s.getName())
                .collect(Collectors.joining("|"));

        return Pattern.compile("^(?i)(" + regions + ")$", Pattern.CASE_INSENSITIVE);
    }

    @Override
    protected String getName() {
        return Regions.class.getName();
    }

}
