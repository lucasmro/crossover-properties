package com.crossover.properties.parser;

import java.util.Map;

import com.crossover.properties.Property;
import com.crossover.properties.PropertyFile;

public interface Parser {

    Map<String, Property> parse(PropertyFile propertyFile);
}
