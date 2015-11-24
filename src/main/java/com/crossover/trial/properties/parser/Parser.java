package com.crossover.trial.properties.parser;

import java.util.Map;

import com.crossover.trial.properties.Property;
import com.crossover.trial.properties.PropertyFile;

public interface Parser {

    Map<String, Property> parse(PropertyFile propertyFile);
}
