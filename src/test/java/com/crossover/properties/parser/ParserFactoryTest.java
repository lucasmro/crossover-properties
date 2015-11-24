package com.crossover.properties.parser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.crossover.properties.FileFormat;
import com.crossover.properties.type.matcher.BooleanMatcher;
import com.crossover.properties.type.matcher.TypeMatcher;

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
