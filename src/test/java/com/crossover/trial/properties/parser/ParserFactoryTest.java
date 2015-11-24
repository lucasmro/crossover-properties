package com.crossover.trial.properties.parser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.crossover.trial.properties.FileFormat;
import com.crossover.trial.properties.parser.JsonParser;
import com.crossover.trial.properties.parser.Parser;
import com.crossover.trial.properties.parser.ParserFactory;
import com.crossover.trial.properties.parser.PropertiesParser;
import com.crossover.trial.properties.type.matcher.BooleanMatcher;
import com.crossover.trial.properties.type.matcher.TypeMatcher;

public class ParserFactoryTest {

    private ParserFactory factory;

    @Before
    public void setUp() {
        TypeMatcher matcher = new BooleanMatcher();
        factory = new ParserFactory(matcher);
    }

    @Test
    public void testShouldReturnAnInstanceOfPropertiesParserWhenFileFormatIsProperties() {
        Parser parser = factory.getParser(FileFormat.PROPERTIES);
        Assert.assertTrue(parser instanceof PropertiesParser);
    }

    @Test
    public void testShouldReturnAnInstanceOfJsonParserWhenFileFormatIsJson() {
        Parser parser = factory.getParser(FileFormat.JSON);
        Assert.assertTrue(parser instanceof JsonParser);
    }
}
