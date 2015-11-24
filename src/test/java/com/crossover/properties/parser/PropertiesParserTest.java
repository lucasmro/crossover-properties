package com.crossover.properties.parser;

import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.crossover.properties.FileFormat;
import com.crossover.properties.Property;
import com.crossover.properties.PropertyFile;
import com.crossover.properties.type.matcher.BooleanMatcher;
import com.crossover.properties.type.matcher.DoubleMatcher;
import com.crossover.properties.type.matcher.IntegerMatcher;
import com.crossover.properties.type.matcher.StringMatcher;
import com.crossover.properties.type.matcher.TypeMatcher;

public class PropertiesParserTest {

    private Parser parser;

    @Before
    public void setUp() {
        TypeMatcher matcher = createMatcher();
        parser = new PropertiesParser(matcher);
    }

    @Test
    public void testShouldParseValidPropertiesFile() {
        String filename = "jdbc.properties";
        String content = "username=foo\ntimeout=3600\nactive=true\nfactor=.75\n";
        FileFormat format = FileFormat.PROPERTIES;

        PropertyFile propertyFile = new PropertyFile(filename, content, format);

        Map<String, Property> properties = parser.parse(propertyFile);

        Assert.assertTrue(properties.size() == 4);

        Assert.assertEquals(properties.get("username").getValue(), "foo");
        Assert.assertEquals(properties.get("timeout").getValue(), "3600");
        Assert.assertEquals(properties.get("active").getValue(), "true");
        Assert.assertEquals(properties.get("factor").getValue(), ".75");

        Assert.assertEquals(properties.get("username").getType(), "java.lang.String");
        Assert.assertEquals(properties.get("timeout").getType(), "java.lang.Integer");
        Assert.assertEquals(properties.get("active").getType(), "java.lang.Boolean");
        Assert.assertEquals(properties.get("factor").getType(), "java.lang.Double");
    }

    @Test
    public void testShouldNotConsiderAnyEmptyKeyOrAnyEmptyValue() {
        String filename = "jdbc.properties";
        String content = "username=\n=3600\n=\nfactor=.75\n";
        FileFormat format = FileFormat.PROPERTIES;

        PropertyFile propertyFile = new PropertyFile(filename, content, format);

        Map<String, Property> properties = parser.parse(propertyFile);

        Assert.assertTrue(properties.size() == 1);
    }

    private TypeMatcher createMatcher() {
        TypeMatcher matcher = new BooleanMatcher();
        matcher.setNextMatcher(new IntegerMatcher());
        matcher.setNextMatcher(new DoubleMatcher());
        matcher.setNextMatcher(new StringMatcher());

        return matcher;
    }
}
