package com.crossover.properties.parser;

import com.crossover.properties.FileFormat;
import com.crossover.properties.type.matcher.TypeMatcher;

public class ParserFactory {
    
    private final TypeMatcher matcher;
    
    public ParserFactory(TypeMatcher matcher) {
        this.matcher = matcher;
    }

    public Parser getParser(FileFormat format) {
        switch (format) {
            case PROPERTIES:
                return new PropertiesParser(matcher);
            case JSON:
                return new JsonParser(matcher);
            default:
                throw new RuntimeException("File format not implemented");
        }
    }
}
