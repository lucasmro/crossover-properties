package com.crossover.trial.properties.parser;

import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.crossover.trial.properties.FileFormat;
import com.crossover.trial.properties.Property;
import com.crossover.trial.properties.PropertyFile;
import com.crossover.trial.properties.type.matcher.TypeMatcher;
import com.crossover.trial.properties.type.matcher.TypeMatcherBuilder;

public class PropertiesParserTest {

    private static String FILENAME_JDBC_PROPERTIES = "jdbc.properties";

    private Parser parser;

    @Before
    public void setUp() {
        TypeMatcher matcher = new TypeMatcherBuilder().buildDefault();
        parser = new PropertiesParser(matcher);
    }

    @Test
    public void testShouldParseValidPropertiesFile() {
        String content = "username=foo\ntimeout=3600\nactive=true\nfactor=.75\n";
        FileFormat format = FileFormat.PROPERTIES;

        PropertyFile propertyFile = new PropertyFile(FILENAME_JDBC_PROPERTIES, content, format);

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
        String content = "username=\n=3600\n=\nfactor=.75\n";
        FileFormat format = FileFormat.PROPERTIES;

        PropertyFile propertyFile = new PropertyFile(FILENAME_JDBC_PROPERTIES, content, format);

        Map<String, Property> properties = parser.parse(propertyFile);

        Assert.assertTrue(properties.size() == 1);
    }

    @Test
    public void testShouldNotThrowAnExceptionWhenContentOfFileIsNull() {
        String content = null;
        FileFormat format = FileFormat.PROPERTIES;

        PropertyFile propertyFile = new PropertyFile(FILENAME_JDBC_PROPERTIES, content, format);

        Map<String, Property> properties = parser.parse(propertyFile);

        Assert.assertTrue(properties.isEmpty());
    }

    @Test
    public void testShouldNotThrowAnExceptionWhenContentOfFileIsEmpty() {
        String content = "";
        FileFormat format = FileFormat.PROPERTIES;

        PropertyFile propertyFile = new PropertyFile(FILENAME_JDBC_PROPERTIES, content, format);

        Map<String, Property> properties = parser.parse(propertyFile);

        Assert.assertTrue(properties.isEmpty());
    }
}
